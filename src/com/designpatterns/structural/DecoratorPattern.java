package com.learn.desgin.patterns.structural;

/**
 * Decorator helps in adding a new functionality to an existing object, without altering its Structure. 
 * Since it deals with structure it belongs to the set of Structural pattern.
 * 
 * In this program we are trying to add new features on Whatsapp msg sending.
 * 1. For plain text msg's we are going to add signature along with the msg
 * 2. For picture messages we are going to use a enhanced algorithm for processing
 * 
 * We are doing this without altering the existing functionality of whatsapp
 */
public class DecoratorPattern 
{
	private static WhatsappVer1 whatsappVer1 = new WhatsappVer1();
	private static WhatsappVer2 whatsappVer2 = new WhatsappVer2(new WhatsappVer1());
	
	private static String msg = "Hi How are you?";
	private static byte[] imageBytes = new byte[10];
	
	public static void main(String args[])
	{
		// prior to adding new functionality
		System.out.println("Whatsapp version 1:");
		whatsappVer1.sendTextMsg(msg);
		whatsappVer1.sendPictureMsg(imageBytes);
		
		// after adding new functionality
		System.out.println("\nWhatsapp version 2:");
		whatsappVer2.setSignature("* Calvin - 9843398927 - calvinjose.1987@gmail.com");
		whatsappVer2.sendTextMsg(msg);
		whatsappVer2.sendPictureMsg(imageBytes);
	}
}

interface WhatsappMessage
{
	public void sendTextMsg(String msg);
	public void sendPictureMsg(byte[] imageBytes);
}

class WhatsappVer1 implements WhatsappMessage
{
	@Override
	public void sendTextMsg(String msg) {
		System.out.println("Sending whatsapp text message :\n"+ msg);
	}

	@Override
	public void sendPictureMsg(byte[] imageBytes) {
		System.out.println("Sending whatsapp picture message");
	}
}

abstract class AbstractWhatsappDecorator implements WhatsappMessage
{
	private WhatsappMessage whatsappMessage;
	
	protected AbstractWhatsappDecorator(WhatsappMessage whatsappMessage)
	{
		this.whatsappMessage = whatsappMessage;
	}
	
	@Override
	public void sendTextMsg(String msg){
		whatsappMessage.sendTextMsg(msg);
	}
	
	@Override
	public void sendPictureMsg(byte[] imageBytes){
		whatsappMessage.sendPictureMsg(imageBytes);
	}
}

class WhatsappVer2 extends AbstractWhatsappDecorator
{
	private WhatsappMessage whatsappMessage;
	private String signature;
	
	public WhatsappVer2(WhatsappMessage whatsappMessage) {
		super(whatsappMessage);
		this.whatsappMessage = whatsappMessage;
	}
	
	@Override
	public void sendTextMsg(String msg){
		String msgWithSignature = addSignature(msg);
		whatsappMessage.sendTextMsg(msgWithSignature);
	}
	
	@Override
	public void sendPictureMsg(byte[] imageBytes){
		byte[] enhancedImageBytes = getEnhancedImageBytes(imageBytes);
		whatsappMessage.sendPictureMsg(enhancedImageBytes);
	}
	
	private byte[] getEnhancedImageBytes(byte[] imageBytes) {
		System.out.println("Performing enhanced image processing");
		return imageBytes;
	}

	private String addSignature(String plainTextMsg)
	{
		System.out.println("Adding signature to the plain message");
		return plainTextMsg.concat("\n").concat(signature);
	}
	
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
}
