#!/bin/bash
pwd=$1

echo $pwd | sudo -S ./ubuntu_modify_ip.sh 192.168.2.194 255.255.255.0 192.168.2.1 netplan-ens33
