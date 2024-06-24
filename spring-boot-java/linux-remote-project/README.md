# 实现在java中修改服务器IP

1. 修改IP脚本"changeIp.sh"

   - 子网掩码转PREFIX

2. Java调用sh的权限问题

   - /etc/sudoers 文件，修改一下两行
   - Defaults secure_path="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/snap/bin:/usr/local/bin"
   - ${your_username} ALL=(ALL:ALL) ALL
   - 修改sh文件权限，sudo chmod 777 changeIp.sh

3. 解决在docker容器内调用sh的问题
    - docker run -it --privileged --pid=host ab71dbf8dc3e /bin/bash
    - --pid=host 和宿主机共享命名空间
    - 在容器内就能执行sh脚本
    - nsenter -a -t 1 sh -c "nmcli con show --active"
    - nsenter -t 1 -m -u -i -n -p -- /home/xxx/shell/test.sh