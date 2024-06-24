#!/bin/bash

NEW_NETMASK=255.255.255.0

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

NEW_PREFIX=$(convert_netmask_to_prefix $NEW_NETMASK)

echo "NEW_PREFIX = " $NEW_PREFIX
