package br.com.jera.database;

public class DataHelper {
	
	public static final String DATABASE_NAME = "botaoteca.db";
	public static final String TABLE_NAME = "sounds";
	public static final int DATABASE_VERSION = 1;
	public static final String CREATE_SQL;
	public static final String DROP_SQL;
	
	static final String LOG_TAG = "DataBase";
	
	static {
		
		StringBuffer sql =  new StringBuffer("CREATE TABLE ");
		sql.append(DataHelper.TABLE_NAME);
		sql.append(" (");
		sql.append("fileName TEXT PRIMARY KEY, ");
		sql.append("name TEXT, ");
		sql.append("color TEXT");
		sql.append(")");
		
		CREATE_SQL = String.format(sql.toString(), TABLE_NAME);
		DROP_SQL = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
	}

}
