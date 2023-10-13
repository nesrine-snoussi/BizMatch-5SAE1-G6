package tn.esprit.registermicroservice.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import tn.esprit.registermicroservice.dto.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;


	public MailResponse sendEmail(MailRequest mailRequest, Map<String, Object> model) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
	
			// Set recipient address, subject, and from address
			helper.setTo(mailRequest.getTo());
			helper.setSubject(mailRequest.getSubject());
	
			// Set the "from" address from the MailRequest object
		//	helper.setFrom(mailRequest.getFrom());
			mailRequest.setFrom("amina.mhadhbi@esprit.tn");
			// Add attachment if needed
			// helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
	
			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
	
			helper.setText(html, true);
			sender.send(message);
	
			response.setStatus(Boolean.TRUE);
			response.setMessage("Mail sent successfully to: " + mailRequest.getTo());
		} catch (MessagingException | IOException | TemplateException e) {
			response.setStatus(Boolean.FALSE);
			response.setMessage("Mail Sending failure: " + e.getMessage());
		}
	
		return response;
	}}