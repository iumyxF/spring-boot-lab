package com.example.hls.converter;

/**
 * @author fzy
 * @description: 本地文件转换成HLS
 * @date 2023/12/16 16:18
 */
public class DemoLocalFile {

    private static final String FILE_PATH = "F:\\testData\\test\\testVideo.mp4";

    private static final String OUTPUT_PATH = "F:\\testData\\test\\testVideoOutput.m3u8";

    public static void main(String[] args) {
        DemoLocalFile file = new DemoLocalFile();
        file.convertHlsInWin();
    }

    public void convertHlsInWin() {
        String tsName = "F:\\testData\\test\\testVideoOutput_%03d.ts";
        String cmd = String.format("ffmpeg -i %s " +
                "-codec:v libx264 -codec:a aac " +
                "-map 0 " +
                "-f hls " +
                "-hls_time 4 " +
                "-hls_list_size 0 " +
                "-hls_segment_filename %s %s", FILE_PATH, tsName, OUTPUT_PATH);
        System.out.println("cmd => " + cmd);
        //try {
        //    if (SystemUtils.IS_OS_WINDOWS) {
        //        System.out.println("windows环境");
        //        ProcessBuilder builder = CmdUtil.exec(cmd);
        //        builder.start();
        //    } else if (SystemUtils.IS_OS_LINUX) {
        //        System.out.println("linux环境");
        //        ProcessBuilder builder = CmdUtil.exec("sh", "-c", cmd);
        //        builder.start();
        //    }
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

    }
}
