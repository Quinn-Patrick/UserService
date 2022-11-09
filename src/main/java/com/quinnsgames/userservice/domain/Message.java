package com.quinnsgames.userservice.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//The message model.
@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Message {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="MM/dd/yyyy, HH:mm:ss") //Note that the date must arrive in a particular format.
	Date date;
	
	@Column(name="CONTENT",nullable=false,length=2048) //Messages can be up to 2048 characters in length.
	private String content;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user1;//user1 is always the sender.
	@ManyToOne(fetch = FetchType.EAGER)
	private User user2;//user2 is always the receiver.
	
	
}
