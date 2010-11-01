package br.com.jera.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataHelper {

	public static final String TABLE_NAME = "sounds";
	public static final int DATABASE_VERSION = 1;
	public static final String CREATE_SQL;
	public static final String DROP_SQL;
	static final String DATABASE_NAME = "botaoteca.db";
	static final String DATABASE_TEST_NAME = "botaoteca-test.db";
	
	static final String LOG_TAG = "DataBase";

	private Context context;
	private SQLiteDatabase db;
	private static boolean testing;

	static {

		StringBuffer sql = new StringBuffer("CREATE TABLE ");
		sql.append(DataHelper.TABLE_NAME);
		sql.append(" (");
		sql.append("fileName TEXT PRIMARY KEY, ");
		sql.append("name TEXT, ");
		sql.append("color TEXT");
		sql.append(")");

		CREATE_SQL = String.format(sql.toString(), TABLE_NAME);
		DROP_SQL = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
	}

	public DataHelper(Context context) {

		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();
	}

	static String getDatabaseName() {
		if (testing) {
			return DATABASE_TEST_NAME;
		} else {
			return DATABASE_NAME;
		}
	}

	static boolean isTesting() {
		return testing;
	}

	static void setTesting(boolean testing) {
		DataHelper.testing = testing;
	}

}
