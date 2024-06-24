package com.example.remote.controller;

import cn.hutool.core.util.RuntimeUtil;
import com.example.remote.model.NetWorkDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * @author iumyx
 * @description:
 * @date 2024/6/19 11:04
 */
@Slf4j
@RestController
public class NetworkController {

    private static final String SH_PATH = "/home/test/shell/ubuntu_modify_ip.sh";

    /*
    修改服务器ip地址流程
    1. 获取所有的连接名字
    2. 接收ip 子网掩码 网关地址 连接名字 执行sh脚本

    # 打包命令 mvn package spring-boot:repackage
    # 1. sudo docker build -t remote_project:1.0.0 .
    # 2. sudo docker run -d -p 8888:8888 -p 5005:5005 --privileged --pid=host aad4ff2bdd85

    不能直接通过nsenter执行cmd命令，nsenter -a -t 1 sh -c 'nmcli con show --active'，
    只能通过nsenter来执行sh脚本

    docker-compose
    1. sudo docker-compose up --build -d
     */

    private static final String SH_ROOT_DIR = "/app/sh/";

    private static final String VOLUME_DIR = "/app/file/";

    private static final String[] SH_FILES = new String[]{"changeIp.sh", "getIpInfo.sh"};

    @PostConstruct
    public void init() {
        for (String filePath : SH_FILES) {
            File file = new File(VOLUME_DIR + filePath);
            if (!file.exists()) {
                File rootFile = new File(SH_ROOT_DIR + filePath);
                if (!rootFile.exists()) {
                    log.info("rootFile = {} ，文件不存在", rootFile.getPath());
                    continue;
                }
                try {
                    // 解决文件权限问题 StandardCopyOption.COPY_ATTRIBUTES
                    FileUtils.copyFile(rootFile, file, StandardCopyOption.COPY_ATTRIBUTES);
                } catch (IOException e) {
                    log.info("文件复制失败");
                }
            }
        }
    }

    @GetMapping("/network")
    public List<NetWorkDto> list() {
        System.out.println("/network");
        List<String> strings = RuntimeUtil.execForLines("nsenter -a -t 1 sh -c 'nmcli con show --active'");
        return analysis(strings);
    }

    @GetMapping("/test4")
    public String test4(@RequestParam("path") String shPath) {
        System.out.println("/test4");
        String cmd = String.format("nsenter -t 1 -m -u -i -n -p -- " + shPath);
        System.out.println(cmd);
        List<String> strings = RuntimeUtil.execForLines(cmd);
        StringBuilder builder = new StringBuilder();
        strings.forEach(item -> builder.append(item).append("\n"));
        return builder.toString();
    }

    @GetMapping("/test3")
    public String test3(@RequestParam("cmd") String cmd) {
        System.out.println("/test3");
        List<String> strings = RuntimeUtil.execForLines(cmd);
        StringBuilder builder = new StringBuilder();
        strings.forEach(item -> builder.append(item).append("\n"));
        return builder.toString();
    }

    @GetMapping("/test2")
    public String test2(@RequestParam("cmd") String cmd) {
        System.out.println("/test2");
        String exec = String.format("nsenter -a -t 1 sh -c '%s'", cmd);
        System.out.println(exec);
        List<String> strings = RuntimeUtil.execForLines(exec);
        StringBuilder builder = new StringBuilder();
        strings.forEach(item -> builder.append(item).append("\n"));
        return builder.toString();
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("/test");
        List<String> strings = RuntimeUtil.execForLines("nsenter -a -t 1 sh -c 'ifconfig'");
        StringBuilder builder = new StringBuilder();
        strings.forEach(item -> builder.append(item).append("\n"));
        return builder.toString();
    }

    @GetMapping("/network/edit")
    public void edit(@RequestParam("ip") String ip,
                     @RequestParam("connectionName") String connectionName) {
        System.out.println("/network/edit");
        String netmask = "255.255.255.0";
        String gateway = "192.168.2.1";
        //execShell(SH_PATH, ip, netmask, gateway, connectionName);
        StringBuilder sb = new StringBuilder();
        sb.append("nsenter -t 1 -m -u -i -n -p -- ")
                .append(SH_PATH + " ").append(ip).append(" ").append(netmask).append(" ").append(gateway).append(" ").append(connectionName);
        List<String> strings = RuntimeUtil.execForLines(sb.toString());
        System.out.println(strings);
    }

    /**
     * 解决了 参数中包含 空格和脚本没有执行权限的问题
     *
     * @param scriptPath 脚本路径
     * @param para       参数数组
     */
    private void execShell(String scriptPath, String... para) {
        try {
            String[] cmd = new String[]{scriptPath};
            //为了解决参数中包含空格
            cmd = ArrayUtils.addAll(cmd, para);

            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "755", scriptPath);
            Process process = builder.start();
            process.waitFor();

            Process ps = Runtime.getRuntime().exec(cmd);
            ps.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            //执行结果
            String result = sb.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<NetWorkDto> analysis(List<String> strings) {
        if (null == strings || strings.isEmpty() || strings.size() < 2) {
            return null;
        }
        ArrayList<NetWorkDto> list = new ArrayList<>();
        for (int i = 1; i < strings.size(); i++) {
            String text = strings.get(i);
            if (StringUtils.isBlank(text)) {
                continue;
            }
            String[] s = StringUtils.split(text, " ");
            NetWorkDto dto = new NetWorkDto();
            dto.setDevice(s[s.length - 1]);
            dto.setType(s[s.length - 2]);
            dto.setUuid(s[s.length - 3]);
            int j = StringUtils.indexOf(text, dto.getUuid());
            if (j > 0) {
                String substring = text.substring(0, j).trim();
                dto.setName(substring);
            }
            list.add(dto);
        }
        return list;
    }

    public static void main(String[] args) {
        //ArrayList<String> strings = new ArrayList<>();
        //strings.add("NAME           UUID                                  TYPE      DEVICE  ");
        //strings.add("有线连接 1 2 3 14f59568-5076-387a-aef6-10adfcca2e26  ethernet  ens33   ");
        //strings.add("docker0        60c90146-2791-4f85-b221-a9c7632b0ae3  bridge    docker0 ");
        //strings.add(" ");
        //List<NetWorkDto> list = analysis(strings);
        //list.forEach(System.out::println);
        String shPath = "/home/test/shell/test.sh";
        //nsenter -t 1 -m -u -i -n -p -- /home/test/shell/test.sh
        //nsenter -t 1 -m -u -i -n -p -- /home/test/shell/test.sh
        String cmd = String.format("nsenter -t 1 -m -u -i -n -p -- %s", shPath);
        System.out.println(cmd);

    }
}
