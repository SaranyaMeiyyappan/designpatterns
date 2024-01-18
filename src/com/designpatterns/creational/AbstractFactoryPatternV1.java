package com.designpatterns.creational;

public class AbstractFactoryPatternV1 {

	public static void main(String[] args) {
		AbstractMobileFactory samsungMobileFactory = new SamsungMobileFactory();
		AbstractMobileFactory nokiaMobileFactory = new NokiaMobileFactory();
		
		try {
			samsungMobileFactory.getSamsungMobile("samsungNote").getSamsungMobileDetails();
			nokiaMobileFactory.getNokiaMobile("Lumia920").getNokiaMobileDetails();
			nokiaMobileFactory.getSamsungMobile("samsungGalaxy");
		} catch (Exception e) {
			System.out.println("Exception occured while creating bean:"+ e.getMessage());
		}
	}

}

interface Samsung {
	public void getSamsungMobileDetails();
}

interface Nokia {
	public void getNokiaMobileDetails();
}

class SamsungGalaryGrand implements Samsung {

	@Override
	public void getSamsungMobileDetails() {
		System.out.println("Created Samsung Galaxy Grand");
	}
	
}

class SamsungNote implements Samsung {

	@Override
	public void getSamsungMobileDetails() {
		System.out.println("Created Samsung Note");
	}
	
}

class NokiaLumia implements Nokia {

	@Override
	public void getNokiaMobileDetails() {
		System.out.println("Create Nokia Lumia");
	}
	
}

class NokiaGSeries implements Nokia {

	@Override
	public void getNokiaMobileDetails() {
		System.out.println("Created Nokia G Series");
	}
	
}

abstract class AbstractMobileFactory {
	public abstract Samsung getSamsungMobile(String mobileType) throws Exception;
	public abstract Nokia getNokiaMobile(String mobileType) throws Exception;
}

class SamsungMobileFactory extends AbstractMobileFactory {

	@Override
	public Samsung getSamsungMobile(String mobileType) throws Exception {
		if("samsungNote".equalsIgnoreCase(mobileType)){
			return new SamsungNote();
		} else if("samsungGalaxy".equalsIgnoreCase(mobileType)) {
			return new SamsungGalaryGrand();
		} else {
			throw new Exception("No Mobile Available in Samsung for type --> "+mobileType);
		}
	}

	@Override
	public Nokia getNokiaMobile(String mobileType) {
		System.out.println("Invalid Implementation");
		return null;
	}
	
}

class NokiaMobileFactory extends AbstractMobileFactory {

	@Override
	public Samsung getSamsungMobile(String mobileType) throws Exception {
		System.out.println("Invalid Implementation");
		return null;
	}

	@Override
	public Nokia getNokiaMobile(String mobileType) throws Exception {
		if(mobileType.equalsIgnoreCase("Lumia920"))
		{
			return new NokiaLumia();
		}
		else
			if(mobileType.equalsIgnoreCase("G12"))
			{
				return new NokiaGSeries();
			}
			else{
				throw new Exception("No Mobile Available in Nokia for type --> "+mobileType);
			}
	}
	
}
