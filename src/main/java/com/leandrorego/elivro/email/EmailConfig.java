package com.leandrorego.elivro.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * @author Matheus Pinheiro
 * Classe responsavel por envio de email agendado
 */
@Component
public class EmailConfig {

	private final long SEGUNDO = 1000;
	private final long MINUTOS = SEGUNDO * 60;
	
	@Autowired
	private JavaMailSender mailSender;

	@Scheduled(initialDelay = MINUTOS, fixedDelay = MINUTOS)
	public void submitEmail() {
/*
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setTo(usuario);
			helper.setSubject("Bem vindo ao E-livros");
			helper.setText("<p>SEja bem vindo ao E-Livros</p>", true);
			mailSender.send(mail);
			System.out.println("Email enviado!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("O email não foi enviado");
		}
		*/
	}
}
