package com.designpatterns.structural;

import java.util.ArrayList;
import java.util.List;

/**
 * Proxy pattern is a structural pattern.
 * 
 * It exposes functionalities from another class. Use it when you don't want the original source not to be revealed to the outside world.
 */
public class ProxyPattern {

	public static void main(String[] args) {
		ProxyService service = new ProxyService();
		service.getUsersFromService().parallelStream()
		.forEach(user -> System.out.println(user.getName() + " " +user.getAccountNumber()));
	}

}

class User {
	private String name;
	private String accountNumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}

interface ExposeUserData {
	public List<User> getUsersFromService();
}

/**
 * Imagine this class, like a secured service which contains details of high profiled user accounts.
 * This service is secured from outside world by all means, but we would need data from this service.
 * So, we have a proxy service, which knows information on how to get the details from the real secured service
 */
class RealSecuredService implements ExposeUserData {

	@Override
	public List<User> getUsersFromService() {
		List<User> userData = new ArrayList<>();
		 for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setName("user"+i);
			user.setAccountNumber("AccountNumber"+i);
			userData.add(user);
		 }
		return userData;
	}
	
}

/**
 * This is the proxy class, which implements the same ExposeUserData interface and provides definition for its getUsersFromService() method.
 * The class knows the address of the real service and it gets data from it. 
 * 
 * But for the outside world, its like the ProxyService is the actual service.
 */
class ProxyService implements ExposeUserData {
	
	private RealSecuredService securedService = new RealSecuredService();

	@Override
	public List<User> getUsersFromService() {
		return securedService.getUsersFromService();
	}
	
	
}
