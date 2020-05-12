package com.wenwl.my.shop.commons.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

/**
 * @author wenwl
 * @version 1.0.0
 * @className EmailUtils
 * @description 邮件工具类
 * @date 2020/5/12 15:15
 */
public class EmailUtils {

    /**
     * 使用的时候注入SimpleEmail实例
     */
    private Email email;

    public void setEmail(Email email) {
        this.email = email;
    }

    /**
     * 发送简单文本
     * @param subject 主题
     * @param msg 内容
     * @param to 收件人
     * @throws EmailException
     */
    public void sendSimpleText(String subject, String msg, String... to) throws EmailException {
        email.setSubject(subject);
        email.setMsg(msg);
        email.addTo(to);
        email.send();
    }

}
