#!/bin/bash

# 提示用户输入新的IP地址
# shellcheck disable=SC2162
#read -p "Please enter a new IP address: " NEW_IP

# 提示用户输入新的子网掩码
#read -p "Please enter a new netmask: " NEW_NETMASK

# 提示用户输入新的网关
#read -p "Please enter a new gateway: " NEW_GATEWAY

# 将子网掩码转换成PREFIX
function convert_netmask_to_prefix() {
  local IFS='.'
  local -a octets=($1)
  local binary_mask=""
  for octet in "${octets[@]}"; do
    binary_mask+="$(printf '%08d' $(bc <<<"obase=2; $octet"))"
  done
  local mask_length=$(grep -o "1" <<<"$binary_mask" | wc -l)
  echo "$mask_length"
}

NEW_IP=$1
NEW_NETMASK=$2
NEW_GATEWAY=$3
CONNECTION_NAME=$4

# 新的 DNS 服务器列表
NEW_DNS1="223.5.5.5"
NEW_DNS2="223.6.6.6"

# 检查输入是否为空
if [[ -z "$NEW_IP" || -z "$NEW_NETMASK" || -z "$NEW_GATEWAY" ]]; then
  echo "Please make sure that all fields are filled in."
  exit 1
fi

NEW_PREFIX=$(convert_netmask_to_prefix $NEW_NETMASK)

echo "NEW_PREFIX = " $NEW_PREFIX

# 获取当前连接名称
# nmcli -f NAME con show --active
#CONNECTION_NAME=$(nmcli con show --active | grep ethernet | awk '{print $1}')

# 检查是否找到了连接名称
if [[ -z "$CONNECTION_NAME" ]]; then
  echo "No Ethernet connection was found, please check your network configuration."
  exit 1
fi

# 断开连接
nmcli con down "$CONNECTION_NAME"

# 修改IP地址
nmcli con mod "$CONNECTION_NAME" ipv4.addresses "$NEW_IP"/"$NEW_PREFIX"
nmcli con mod "$CONNECTION_NAME" ipv4.gateway "$NEW_GATEWAY"
nmcli con mod "$CONNECTION_NAME" ipv4.dns "$NEW_DNS1,$NEW_DNS2"
nmcli con mod "$CONNECTION_NAME" ipv4.method manual

# 重新连接
nmcli con up "$CONNECTION_NAME"

# 重启网络服务
systemctl restart NetworkManager

echo "IP address updated successfully !"
