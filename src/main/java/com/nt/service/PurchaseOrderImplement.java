package com.nt.service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service("purchaseService")
public class PurchaseOrderImplement implements I_purchase {
    private final JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    public PurchaseOrderImplement(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public String purchase(String[] items, double[] price, String[] toEmails) throws Exception {
       //calculate bill amount
        double billAmount = 0;
        for(double i:price){
            billAmount += i;
        }

        String message = Arrays.toString(items)+"with prices"+Arrays.toString(price)+"are purchased with bill amount "+billAmount;
        String status=sendMail(message,toEmails);
        return message+"---->"+status;
    }
    private String sendMail(String message,String[] toEmails) throws Exception {
        MimeMessage msg =sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg,true,"UTF-8");
        helper.setFrom(fromEmail);
        helper.setTo(toEmails);
        helper.setSubject("Order Confirmation");
        helper.setSentDate(new Date());
        helper.setText(message,true);
        sender.send(msg);
        return "mail set successfully";

    }

}
