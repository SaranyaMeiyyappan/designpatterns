package com.learn.desgin.patterns.structural;

/**
 * 
 * The Adapter Design Pattern is part of structural design pattern and it is a powerful and essential tool for software developers. 
 * It helps to create flexible and maintainable code by allowing objects with incompatible interfaces to work together seamlessly.
 * 
 *  It is often used to make existing classes work with others without modifying their source code. 
 *  The pattern involves creating an adapter class that bridges the gap between the interfaces, allowing them to communicate effectively.
 *
 */
public class AdapterPattern {

	public static void main(String args[])
	{
		new Laptop(true, true, true, true);
	}
}

/**
 * Separate Interface for reading Mobile Data
 */
interface MobileDataReader
{
	public void readInternalSDcard();
	public void readExternalSDCard();
}

class Mobile implements MobileDataReader
{
	public Mobile()
	{
		readInternalSDcard();
		readExternalSDCard();
	}
	
	@Override
	public void readInternalSDcard() {
		System.out.println("Reading mobile's internal SD card");
	}

	@Override
	public void readExternalSDCard() {
		System.out.println("Reading mobile's external SD card");
	}
}

/**
 * Separate Interface for reading SD Card
 */
interface SDcardReader
{
	public void readSDcard();
}

class SDCard implements SDcardReader
{
	@Override
	public void readSDcard() {
		System.out.println("Reading data from MemoryCard");
	}
}

/**
 * Separate Interface for reading USB
 */
interface USBReader
{
	public void readUSBPort();
}

class USBPort implements USBReader
{
	@Override
	public void readUSBPort() {
		System.out.println("Reading data from USB port");
	}
}

/**
 * Separate Interface for reading laptop data such as internal Hard disk, external hard disk, DVD and SD Card and USB
 */
interface LaptopDataReader
{
	public void readInternalHDD();
	public void readExternalHDD();
	public void readDiscDrive();
	public void readSDcard();
	public void  readUSB();
}

/**
 * Laptop class will implement LaptopDataReader where it needs to read internal laptop drive and also external plugin options such as SD Card, mobile and USB.
 * So, It calls LaptopAdapter instance to read those uncompatible types like SD card, mobile and USB.
 */
class Laptop implements LaptopDataReader
{
	private LaptopAdapter laptopAdapter = LaptopAdapter.getAdapterInstance();
	
	public Laptop(boolean isSDCardPlugged, boolean isExternalDrivePlugged, boolean isDvdLoaded, boolean isUSBPlugged)
	{
		readInternalHDD();
		
		if(isExternalDrivePlugged)
		{
			readExternalHDD();
		}
		
		if(isDvdLoaded)
		{
			readDiscDrive();
		}
		
		if(isSDCardPlugged)
		{
			readSDcard();
		}
		
		if(isUSBPlugged)
		{
			readUSB();
		}
	}
	
	@Override
	public void readInternalHDD() {
		System.out.println("Reading laptop's internal hard disk");
	}

	@Override
	public void readExternalHDD() {
		System.out.println("Reading laptop's external hard disk");
	}

	@Override
	public void readDiscDrive() {
		System.out.println("Reading laptop's DVD drive");
	}

	@Override
	public void readSDcard() {
		System.out.println("Reading laptop's SD card via LaptopAdapter");
		laptopAdapter.readSDcard();
	}

	@Override
	public void readUSB() {
		System.out.println("Reading laptop's USB port via LaptopAdapter");
		laptopAdapter.readUSB();
	}
}

/**
 * In this Adapter class, we are implementing the reading options for mobile, SD card and USB.
 */
class LaptopAdapter 
{
	private MobileDataReader mobileDataReader;
	private SDcardReader sdCardReader;
	private USBReader usbReader;
	
	private LaptopAdapter(){}
	
	/**
	 * No matter how many times you call the getAdapterInstance() the same object is to be returned
	 */
	public static LaptopAdapter getAdapterInstance()
	{
		return AdapterCreator.laptopAdapter;
	}
	
	/**
	 * Use a private static class to create the object. Since its static only one object will be created and shared
	 */
	private static class AdapterCreator
	{
		private static LaptopAdapter laptopAdapter = new LaptopAdapter();
	}
	
	/**
	 * This adapter method reads the data from mobile
	 */
	public void readMobile()
	{
		mobileDataReader = new Mobile();
		mobileDataReader.readInternalSDcard();
		mobileDataReader.readExternalSDCard();
	}
	
	/**
	 * This adapter method reads the data from SD card
	 */
	public void readSDcard()
	{
		sdCardReader = new SDCard();
		sdCardReader.readSDcard();
	}
	
	/**
	 * This adapter method reads the data from USB
	 */
	public void readUSB() {
		usbReader = new USBPort();
		usbReader.readUSBPort();
	}
}
