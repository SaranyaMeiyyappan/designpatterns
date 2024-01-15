package com.designpatterns.creational;

/**
 * Singleton is only one object per program execution environment. For a particular program or an application, creating 
 * only one instance of a class. 
 * 
 * When needed?
 * - Create a singleton when you don't want your object to maintain state
 * - Spring beans are singleton for the same reason. For eg. the JdbcTemplate is mapped to a data source, and across all the 
 * requests, sessions for the application the data source remains the same.it doesn't change. Only dependencies are expected 
 * to be created as singleton. Not the BO's which are stateful and important in the business contexts
 */
public class Singleton {

	public static void main(String[] args) {
		Database db1 = Database.getInstance();
		Database db2 = Database.getInstance();
		System.out.println(db1.getInstance());
		System.out.println(db2.getInstance());
		System.out.println(db1==db2);
	}

}

/**
 * Normal way of addressing a Singleton
 */
class Database {
	
	private static Database instance = null;
	
	private Database() {
		
	}
	
	public static Database getInstance() {
		if(instance==null) {
			instance=new Database();
		}
		return instance;
	}

}

/**
 * A thread safe Singleton. No two threads can access the getInstance method at a given time
 */
class Database_v2 {
	
	private static Database_v2 instance = null;
	
	private Database_v2() {
		
	}
	
	public synchronized Database_v2 getInstance() {
		if(instance==null) {
			instance=new Database_v2();
		}
		return instance;
	}

}

/**
 * Another version of thread safe Singleton. Even though we have synchronized the getInstance method, 
 * for no two threads can access the getInstance method at a given time. Still, with threads there is 
 * a possibility of 2 threads gaining access to the getInstance() method. If so there will exist 2 instances.
 * 
 * This code will fail on Java 1.4 environment as synchronized wasn't part of those distributions
 * 
 * To avoid it we have a double checked singleton
 */
class Database_v3 {
	private static Database_v3 instance = null;
	private Database_v3() {
		
	}
	public static Database_v3 getInstance() {
		if(instance==null) { // Check 1
			synchronized (instance) // Once its found to be null, lock and create the instance 
			{
				if(instance==null) { // Check 2. This is to ensure that if any thread already has created an instance
					instance=new Database_v3(); // Only if both the conditions pass, create the instance
				}
			}
		}
		return instance;
	}
}

/**
 * The above created singleton instances are subject to vulnerability via cloning and serializing. Here we reject the cloning 
 * and serializing
 */
class Database_v4 implements Cloneable {
	
	// Make the instance transient so that it will be excluded from Serializing
	private transient static Database_v4 instance = null;
	private Database_v4() {
		
	}
	public static Database_v4 getInstance() {
		if(instance==null) {
			synchronized (instance) {
				if(instance==null) {
					instance=new Database_v4();
				}
			}
		}
		return instance;
	}
	public Object Clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Cloning in singleton is not allowed");
	}
	
	/**
	 * By making readResolve method which is associated with Serializing, we are restricting the possibility if creating 
	 * singleton instance from de-serializing 
	 */
	public Object readResolve() {
		return instance;
	}
}

/**
 * Here the singleton is created via a static inner class only when a request is made.
 */
class Database_v5{
	private static class singleton_db_v5{
		private static singleton_db_v5 instance = new singleton_db_v5();
	}
	public static singleton_db_v5 getInstance() {
		return singleton_db_v5.instance;
	}
}
