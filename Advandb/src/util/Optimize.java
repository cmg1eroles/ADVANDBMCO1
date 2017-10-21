package util;

import model.Database;

public class Optimize {
	
	public static void createIndex(String name, String table){
		String create = "Create Index " + name + " on " + table + ";";
		Database.getInstance().executeUpdate(create);
	}
	
	public static void dropIndex(String name, String table){
		String drop = "DROP INDEX " + name + " ON " + table;
		Database.getInstance().executeUpdate(drop);
	}
	
	public static void createTable(String name, String query){
		String table = "Create Temporary Table " + name + " AS " + query;
		Database.getInstance().executeUpdate(table);
	}
	
	public static void dropTable(String name){
		String drop = "DROP TABLE " + name + ";";
		Database.getInstance().executeUpdate(drop);
	}
	public static void createView(String name, String query){
		String view = "Create View " + name + " As " + query;
		Database.getInstance().executeUpdate(view);
		
	}
	public static void dropView(String name){
		String drop = "DROP VIEW " + name + ";";
		Database.getInstance().executeUpdate(drop);
	}
}
