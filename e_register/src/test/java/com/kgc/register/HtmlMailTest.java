package com.kgc.register;

import org.apache.commons.mail.HtmlEmail;

import java.util.Date;

public class HtmlMailTest {
	public static void main(String[] args) {
		//新建可以读取html标签内容的邮件对象
		HtmlEmail email = new HtmlEmail();
		//新浪邮件服务器（用于发、收邮件的）POP3/POP是用来取邮件的
		email.setHostName("smtp.sina.com");
		//新浪邮件邮箱的登陆账号密码
		email.setAuthentication("wsm_13367130589", "36c3209492cae2f1");
		//把邮件的字符编码设置为UTF-8
		email.setCharset("UTF-8");
		try{
			//接收方的邮箱地址
			email.addTo("1509274080@qq.com");
			//必须和Authentication使用的用户相同，否则失败（邮箱名字）
			email.setFrom("wsm_13367130589@sina.com", "wsm_13367130589",  "utf-8");// 设置发件人信息
			//发送的邮件标题
			email.setSubject("你好");
			//发送邮件时间
			email.setSentDate(new Date());
			//发送的邮件内容
			email.setHtmlMsg("这是发送的邮件提示：<font size='30px' color='red'>注册成功。。</font>");
			//发送
			email.send();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
