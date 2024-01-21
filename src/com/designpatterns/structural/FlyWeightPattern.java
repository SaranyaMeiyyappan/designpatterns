package com.designpatterns.structural;

import java.util.HashMap;
import java.util.Map;

/**
 * FlyWeightPattern is a structural pattern. Improves object creation structure of the application.
 * 
 * Eg. ThreadPool, Database connection pool
 * 
 * Every time an object is requested, we need not create it. We can maintain a fixed size pool of objects and can send back an object from the pool
 * when requested. By this way we can reduce the no. of objects created within the application and thus decrease the memory footprint of the application  
 */
public class FlyWeightPattern {

	public static void main(String[] args) {
		
		for(int i=0; i<100; i++) {
			if(i%2==0) {
				createDBConnectionInstance("connectionName1");
			} else {
				createDBConnectionInstance("connectionName1");
			}
		}
	}

	private static OracleDBConnection createDBConnectionInstance(String connectionName) {
		return OracleDBConnectionPool.getConnectionObjectFromPool(connectionName);
	}
}

interface DBConnection {
	public void establishDBConnection(String[] connectionParams);
}

/**
 * This class represents an oracle db connection which is uniquely, identified by its connectionName parameter.
 * The establishConnection method just creates a connection between the application and the oracle db
 */
class OracleDBConnection implements DBConnection {

	private String connectionName;
	
	
	public String getConnectionName() {
		return connectionName;
	}


	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}


	@Override
	public void establishDBConnection(String[] connectionParams) {
		System.out.println("Establishing connection for Oracle DB...");
	}
	
}

/**
 * This class is the actual representation of FlyWeight pattern where the connection object creation is restricted.
 * We have a shared map which identifies each connection object by the connectionName parameter. Only when there 
 * is no object existing in the map for a given connection name, a new connection object can be created. Else, the 
 * existing object will be returned from the map. 
 */
class OracleDBConnectionPool {
	private static Map<String, OracleDBConnection> oracleDBConnectionPool = new HashMap<>();
	
	public static OracleDBConnection getConnectionObjectFromPool(String connectionName) {
		OracleDBConnection oracleDBConnection = null;
		if(oracleDBConnectionPool.get(connectionName)==null) {
			
			// Create a new connection
			oracleDBConnection = new OracleDBConnection();
			oracleDBConnection.setConnectionName(connectionName);
			System.out.println("Creating a new connection namely: "+connectionName);
			
			// Add the connection to the connection pool
			oracleDBConnectionPool.put(connectionName, oracleDBConnection);
		} else {
			
			// if the connection already exists, don't create a new one, just return the existing instance
			oracleDBConnection = oracleDBConnectionPool.get(connectionName);
		}
		return oracleDBConnection;
	}
}
