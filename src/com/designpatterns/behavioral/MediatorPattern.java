package com.designpatterns.behavioral;

import java.util.Date;

/**
 * Mediator pattern is a behavioural pattern.
 * 
 * Use it when you need a central store, getting information from a multiple sources  
 * eg. Chatrooms, Groupchats, Distributed Databases
 */
public class MediatorPattern {

	public static void main(String[] args) {
		GroupMember1 member1 = new GroupMember1("Saranya");
		GroupMember2 member2 = new GroupMember2("Sai");
		GroupMember3 member3 = new GroupMember3("Nithya");
		
		member1.sendMessage("Hi");
		member2.sendMessage("Hello");
		member3.sendMessage("Hey");
		member3.sendMessage("Hey".getBytes());
	}

}

/**
 * 
 * interface to represent a groups or chatrooms or a common place to send messages
 *
 */
interface WhatsappGroupChat {
	public void sendMessage(String msg);
	public void sendMessage(byte[] mediaMsg);
}

/**
 * 
 * this class will have a static method to send the message or different types of messages
 *
 */
class WhatsappChats {
	public static void sendWhatsappMessages(String memberName, Object message) {
		if(message instanceof String) {
			System.out.println(new Date()+" Text message from "+memberName+" : "+message);
		}
		if(message instanceof byte[]) {
			System.out.println(new Date()+" Media message from "+memberName+" : "+message);
		}
	}
}

/**
 * 
 * Group User/Group Member class will implement the whatsapp group chat interface to send message
 *
 */
class GroupMember1 implements WhatsappGroupChat {

	private String memberName;
	
	protected GroupMember1(String memberName) {
		super();
		this.memberName = memberName;
	}

	@Override
	public void sendMessage(String msg) {
		WhatsappChats.sendWhatsappMessages(memberName, msg);
	}

	@Override
	public void sendMessage(byte[] mediaMsg) {
		WhatsappChats.sendWhatsappMessages(memberName, mediaMsg);
	}
}

/**
 * 
 * Group User/Group Member class will implement the whatsapp group chat interface to send message
 *
 */
class GroupMember2 implements WhatsappGroupChat {

	private String memberName;
	
	protected GroupMember2(String memberName) {
		super();
		this.memberName = memberName;
	}

	@Override
	public void sendMessage(String msg) {
		WhatsappChats.sendWhatsappMessages(memberName, msg);
	}
	
	@Override
	public void sendMessage(byte[] mediaMsg) {
		WhatsappChats.sendWhatsappMessages(memberName, mediaMsg);
	}
}

/**
 * 
 * Group User/Group Member class will implement the whatsapp group chat interface to send message
 *
 */
class GroupMember3 implements WhatsappGroupChat {

	private String memberName;
	
	protected GroupMember3(String memberName) {
		super();
		this.memberName = memberName;
	}

	@Override
	public void sendMessage(String msg) {
		WhatsappChats.sendWhatsappMessages(memberName, msg);
	}
	
	@Override
	public void sendMessage(byte[] mediaMsg) {
		WhatsappChats.sendWhatsappMessages(memberName, mediaMsg);
	}
}
