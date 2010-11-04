package br.com.jera.botaoteca.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class OpenHelper extends SQLiteOpenHelper {

	public OpenHelper(Context context) {
		super(context, DataHelper.getDatabaseName(), null, DataHelper.DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(DataHelper.DATABASE_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		Log.w(DataHelper.LOG_TAG, "Upgrading database, this will drop tables and recreate.");
		db.execSQL(DataHelper.DROP_SQL );
		
		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(newVersion);

	}

}