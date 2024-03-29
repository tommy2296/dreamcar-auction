package com.dreamcar.auction.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dreamcar.auction.entities.Auction;

@Service
public class EmailService {

    @Value("${mail.from}")
    private String from;

    private final JavaMailSender sender;
    
    private final String adminEmail = "robert.tomescu@gmail.com";

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendBidWinnerMail(Auction auction, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Congratulations! You have won the auction for " + auction.getName());
        message.setFrom(from);
        message.setText(email + ", you have won the auction for " + auction.getName() + "!\n\n"
        				+ "The price you won with is " + auction.findTopBid().getPrice() + "!");
        
        System.out.println("from: " + from);
        System.out.println("to: " + email);
        
        try {
        this.sender.send(message);
        }
        catch (Exception e) {
        	System.out.println(e.toString());
        }
    }

	public void sendAuctionNoBidsMail(Auction auction) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(adminEmail);
        message.setSubject("Auction for " + auction.getName() + " has expired wihout any bids");
        message.setFrom(from);
        message.setText("The auction for " + auction.getName() + " expired on " + auction.getTimeLimit() + " without any bids.");
        
        try {
        this.sender.send(message);
        }
        catch (Exception e) {
        	System.out.println(e.toString());
        }
		
	}
  
}

