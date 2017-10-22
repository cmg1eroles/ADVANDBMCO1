package util;

import java.util.ArrayList;

import model.Database;

public class Optimize {
	
	public static String optimizations = "";
	public static ArrayList<String[]> dropIndexTags = new ArrayList<String[]>();
	public static ArrayList<String> dropTableTags = new ArrayList<String>();
	public static ArrayList<String> dropViewTags = new ArrayList<String>();
	
	public static void createIndex(String name, String table){
		String create = "Create Index " + name + " on " + table + ";";
		optimizations = optimizations + create + "\n";
		dropIndexTags.add(new String[]{name, table.split("\\(")[0]});
		Database.getInstance().executeUpdate(create);
	}
	
	public static void dropIndex(String name, String table){
		String drop = "DROP INDEX " + name + " ON " + table;
		Database.getInstance().executeUpdate(drop);
	}
	
	public static void createTable(String name, String query){
		String table = "Create Temporary Table " + name + " AS " + query;
		optimizations = optimizations + table + "\n";
		dropTableTags.add(name);
		Database.getInstance().executeUpdate(table);
	}
	
	public static void dropTable(String name){
		String drop = "DROP TABLE " + name + ";";
		Database.getInstance().executeUpdate(drop);
	}
	public static void createView(String name, String query){
		String view = "Create View " + name + " As " + query;
		optimizations = optimizations + view + "\n";
		dropViewTags.add(name);
		Database.getInstance().executeUpdate(view);
		
	}
	public static void dropView(String name){
		String drop = "DROP VIEW " + name + ";";
		Database.getInstance().executeUpdate(drop);
	}
}
