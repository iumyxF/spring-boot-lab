package com.example.office.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.office.mode.*;
import org.primeframework.jwt.Signer;
import org.primeframework.jwt.domain.JWT;
import org.primeframework.jwt.hmac.HMACSigner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author iumyxF
 * @description: 当前版本只能适用word文档
 * 后期修改:
 * 1.将HTML的参数进行动态化
 * 2.适配pdf、excel等office文件
 * @date 2023/12/13 15:38
 */
@Controller
@RequestMapping("/office")
public class OfficeController {

    /**
     * 真实保存文件的路径,这里的ip和端口指的保存文件的服务器
     */
    private static final String fileUrl = "http://ip:port/files/test.docx";

    /**
     * 这里的ip和端口指的是springboot服务器
     */
    private static final String callBackUrl = "http://ip:port/test/callBack";

    @GetMapping("/test/editor")
    public String test1(ModelMap modelMap) {
        //创建token
        Document document = getDocument();
        EditorConfig editorConfig = getEditorConfig();
        HashMap<String, Object> map = new HashMap<>();
        map.put("document", document);
        map.put("editorConfig", editorConfig);
        String token = createToken(map);

        Config config = new Config();
        config.setType("desktop");
        config.setMode("review");
        config.setDocumentType("word");
        config.setDocument(document);
        config.setEditorConfig(editorConfig);
        config.setHeight("100%");
        config.setWidth("100%");
        config.setFrameEditorId("iframeEditor");
        config.setToken(token);

        modelMap.put("token", token);
        return "test1";
    }


    @PostMapping("/test/callBack")
    public void callBack(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        //这里的路径指的是文件在服务器的绝对路径
        String fileUrl = "/xxx/xxx/test.docx";
        File file = new File(fileUrl);
        if (!file.exists()) {
            throw new RuntimeException("未找到该文件");
        }
        PrintWriter writer = response.getWriter();
        Scanner scanner = new Scanner(request.getInputStream()).useDelimiter("\\A");
        String body = scanner.hasNext() ? scanner.next() : "";
        com.alibaba.fastjson.JSONObject jsonObject = JSONObject.parseObject(body);
        Integer status = (Integer) jsonObject.get("status");
        if (status == 2 || status == 6) {
            URL url = new URL((String) jsonObject.get("url"));
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            try (FileOutputStream out = new FileOutputStream(file)) {
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
            }
            connection.disconnect();
            stream.close();
        }
        scanner.close();
        writer.write("{\"error\":" + 0 + "}");
        writer.close();
    }


    //下面是一些创建测试数据的方法，数值对应HTML的config即可 可以忽略

    private static Config getConfig() {
        Document document = getDocument();
        EditorConfig editorConfig = getEditorConfig();
        HashMap<String, Object> map = new HashMap<>();
        map.put("document", document);
        map.put("editorConfig", editorConfig);
        String token = createToken(map);

        Config config = new Config();
        config.setDocument(document);
        config.setEditorConfig(editorConfig);
        config.setDocumentType("word");
        config.setHeight("100%");
        config.setWidth("100%");
        config.setToken(token);
        config.setType("desktop");
        return config;
    }

    private static Document getDocument() {
        Document document = new Document();
        document.setKey("Khirz6zTPdfd7");
        document.setUrl(fileUrl);
        document.setTitle("test");
        document.setFileType("docx");
        document.setPermissions(getPermissions());
        return document;
    }

    private static Permissions getPermissions() {
        Permissions permissions = new Permissions();
        permissions.setComment(true);
        permissions.setCopy(true);
        permissions.setDownload(true);
        permissions.setEdit(true);
        permissions.setFillForms(true);
        permissions.setModifyContentControl(true);
        permissions.setModifyFilter(true);
        permissions.setPrint(true);
        permissions.setReview(true);
        permissions.setCommentGroups(new CommentGroups());
        return permissions;
    }

    private static EditorConfig getEditorConfig() {
        EditorConfig config = new EditorConfig();
        config.setMode("edit");
        config.setCallbackUrl(callBackUrl);
        config.setLang("zh");
        config.setCreateUrl("");
        config.setTemplates(new ArrayList<>());
        User user = new User();
        //随意填写，可以结合项目真实user
        user.setId("78e1e841");
        user.setName("zoe");

        Customization customization = new Customization();
        customization.setForcesave(true);
        customization.setSubmitForm(false);
        customization.setAbout(true);
        customization.setFeedback(false);
        config.setCustomization(customization);

        config.setCanCoAuthoring(true);
        config.setCanUseHistory(true);
        config.setCanHistoryClose(true);
        config.setCanHistoryRestore(false);
        config.setCanSendEmailAddresses(false);
        config.setCanRequestEditRights(true);
        config.setCanRequestClose(false);
        config.setCanRename(false);
        config.setCanMakeActionLink(true);
        config.setCanRequestUsers(true);
        config.setCanRequestSendNotify(true);
        config.setCanRequestSaveAs(true);
        config.setCanRequestInsertImage(true);
        config.setCanRequestMailMergeRecipients(true);

        config.setUser(user);
        return config;
    }

    private static String createToken(final Map<String, Object> payloadClaims) {
        try {
            Signer signer = HMACSigner.newSHA256Signer("test");
            JWT jwt = new JWT();
            for (String key : payloadClaims.keySet()) {
                jwt.addClaim(key, payloadClaims.get(key));
            }
            return JWT.getEncoder().encode(jwt, signer);
        } catch (Exception e) {
            return "";
        }
    }
}
