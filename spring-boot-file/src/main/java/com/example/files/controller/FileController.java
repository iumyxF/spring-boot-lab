package com.example.files.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author iumyxF
 * @description: https://github.com/niumoo/down-bit
 * @date 2023/5/31 14:19
 */
@Slf4j
@RestController
public class FileController {

    private static final String FILE_PATH = "F:\\temp\\";

    // region 上传

    /**
     * 上传文件
     * 多文件 参数改成：MultipartFile[] files
     */
    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) {
        try {
            //本地文件保存位置
            File uploadDir = new File(FILE_PATH);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            log.info(uploadDir.getAbsolutePath());
            // 本地文件
            File localFile = new File(FILE_PATH + File.separator + file.getOriginalFilename());
            file.transferTo(localFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     * 临时目录
     */
    private static final String TEMP_DIR = "F:\\temp\\";

    /**
     * 大文件分片上传
     *
     * @param file        分片文件
     * @param chunkNumber 当前分片index
     * @param totalChunks 总分片
     * @param fileName    文件名
     */
    @PostMapping("/chunks")
    public void uploadChunk(MultipartFile file, Integer chunkNumber, Integer totalChunks, String fileName) {
        // 创建分片文件保存路径
        String chunkFilePath = TEMP_DIR + fileName + ".part" + chunkNumber;
        try {
            // 保存分片文件
            file.transferTo(new File(chunkFilePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save chunk file: " + chunkFilePath);
        }
        // 判断是否完成所有分片上传
        if (chunkNumber.equals(totalChunks - 1)) {
            // 所有分片上传完毕，合并文件
            mergeChunks(fileName, totalChunks);
        }
    }

    private void mergeChunks(String fileName, int totalChunks) {
        // 创建目标文件保存路径
        String destFilePath = "F:\\temp\\files\\" + fileName;
        try (FileOutputStream destFileOutputStream = new FileOutputStream(destFilePath)) {
            // 循环读取每个分片文件并写入目标文件
            for (int i = 0; i < totalChunks; i++) {
                String chunkFilePath = TEMP_DIR + fileName + ".part" + i;
                FileInputStream chunkFileInputStream = new FileInputStream(chunkFilePath);

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = chunkFileInputStream.read(buffer)) != -1) {
                    destFileOutputStream.write(buffer, 0, bytesRead);
                }
                chunkFileInputStream.close();
                // 删除已合并的分片文件
                new File(chunkFilePath).delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to merge chunk files");
        }
        // 验证合并后的文件完整性，例如计算MD5校验和

        // 移动文件到目标目录

        // 清理临时目录
    }

    //endregion

    //region 下载

    /**
     * 下载文件，下载到流中
     *
     * @param response
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = new String("001.mp4".getBytes(), "ISO8859-1");
        File file = new File(FILE_PATH + fileName);
        try (InputStream inputStream = new FileInputStream(file); OutputStream outputStream = response.getOutputStream()) {
            //设置内容类型为下载类型
            response.setContentType("application/x-download");
            //设置请求头 和 文件下载名称
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
            //用 common-io 工具 将输入流拷贝到输出流
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件，使用springboot的ResponseEntity
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping("/download2")
    public ResponseEntity<FileSystemResource> downloadFile() throws UnsupportedEncodingException {
        // 指定要下载的文件路径
        String fileName = new String("001.mp4".getBytes(), "ISO8859-1");
        File file = new File(FILE_PATH + fileName);
        // 检查文件是否存在
        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        // 将文件转为FileSystemResource
        FileSystemResource fileResource = new FileSystemResource(file);
        return new ResponseEntity<>(fileResource, headers, ResponseEntity.ok().build().getStatusCode());
    }

    /**
     * 下载不存在的文件，需要自己创建新文件并写入
     *
     * @param fileId   文件id 用于查询
     * @param request  req
     * @param response resp
     * @throws IOException
     */
    @GetMapping("/downloadById")
    public void downloadById(@RequestParam("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(fileId)) {
            return;
        }
        //这里一定要编码 不然文件名会乱码
        String fileName = new String("文件名称".getBytes(), "ISO8859-1");
        String filePath = "D:\\" + fileName;
        //创建文件 并写入内容
        File file = new File(filePath);
        file.createNewFile();
        FileUtils.writeStringToFile(file, "文本内容", "UTF-8");
        ServletContext context = request.getServletContext();
        String mimeType = context.getMimeType(filePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
        response.setHeader(headerKey, headerValue);
        InputStream myStream = Files.newInputStream(Paths.get(filePath));
        IOUtils.copy(myStream, response.getOutputStream());
        response.flushBuffer();
    }
    //endregion
}
