package com.exmaple.mail.controller;

import com.exmaple.mail.utils.MailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author fzy
 * @description:
 * @date 2023/5/3 10:03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/demo/mail")
public class MailController {

    /**
     * 发送邮件
     *
     * @param to      接收人
     * @param subject 标题
     * @param text    内容
     */
    @GetMapping("/sendSimpleMessage")
    public String sendSimpleMessage(String to, String subject, String text) {
        MailUtils.sendText(to, subject, text);
        return "send success";
    }

    /**
     * 发送邮件（带附件）此处需要配置tomcat转义字符，不然会出现参数异常问题（The valid characters are defined in RFC 7230 and RFC 3986）
     *
     * @param to       接收人
     * @param subject  标题
     * @param text     内容
     * @param filePath 附件路径
     */
    @GetMapping("/sendMessageWithAttachment")
    public String sendMessageWithAttachment(String to, String subject, String text, String filePath) {
        MailUtils.sendText(to, subject, text, new File(filePath));
        return "send success";
    }
}
