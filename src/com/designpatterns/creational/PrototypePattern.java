package com.designpatterns.creational;

import java.util.HashMap;
import java.util.Map;

/**
 * A prototype pattern is a creational pattern. 
 * 
 * It creates duplicate objects keeping performance in mind.
 *  
 * Use this pattern when creating objects directly is a costly operation. Here once the object is created 
 * directly it is added to a cache. And every time the object is requested a clone of the respective object
 * from the cache is sent back as response.
 * 
 * The idea is instead of creating a new object every time, we create the object once and for the subsequent calls 
 * clone the object 
 */
public class PrototypePattern {

	public static void main(String[] args) {
		DBConnectionPool.loadDBConnections();
		System.out.println(new DBConnectionPool().getConnection("oracleConnection"));
	}
}

/**
 * This is the base class which contains the object's behaviour and clone method.
 * We have to implement Clonable and implement its clone method to support cloning
 * 
 * This class could have been an interface if cloning is not part of Prototype pattern
 */
abstract class DBConnection implements Cloneable {
	
	public abstract void connect(String userName, String password, String configParams[]);
	
	public Object clone() {
		Object clone = null;
		try {
			clone = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return clone;
	}
}

/**
 * OracleDBConnection - Concrete implementation of the DBConnection abstract class
 */
class OracleDBConnection extends DBConnection{

	@Override
	public void connect(String userName, String password, String[] configParams) {
		System.out.println("Connecting Oracle Database");
	}
	
}

/**
 * MySqlDBConnection - Concrete implementation of the DBConnection abstract class
 */
class MySqlDBConnection extends DBConnection
{
	@Override
	public void connect(String userName, String password, String[] configParams) {
		System.out.println("Connecting to MySql datasource");
	}
}

/**
 * SqlServerDBConnection - Concrete implementation of the DBConnection abstract class
 */
class SqlServerDBConnection extends DBConnection
{
	@Override
	public void connect(String userName, String password, String[] configParams) {
		System.out.println("Connecting to SqlServer datasource");
	}
}

/**
 * The object creator and dispatcher class.
 * We create the costly objects once at start up. A map holds all the created objects.
 * 
 * The load method and the map instance are made as static to make the function execution only once 
 * and shared across the program
 */
class DBConnectionPool {
	private static Map<String, DBConnection> connectionPoolMap = new HashMap<>();
	
	public static void loadDBConnections() {
		System.out.println("Loading Database Connections...");
		connectionPoolMap.put("oracleConnection", new OracleDBConnection());
		connectionPoolMap.put("mySqlConnection", new MySqlDBConnection());
		connectionPoolMap.put("sqlServerConnection", new SqlServerDBConnection());
	}
	
	public DBConnection getConnection(String connectionName) {
		return (DBConnection) connectionPoolMap.get(connectionName).clone();
	}
	
}
