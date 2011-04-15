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
		populateDatabase(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		Log.w(DataHelper.LOG_TAG, "Upgrading database, this will drop tables and recreate.");
		db.execSQL(DataHelper.DROP_SQL);

		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(newVersion);
		populateDatabase(db);
	}

	private void populateDatabase(SQLiteDatabase db) {
		db.beginTransaction();
		db.execSQL("INSERT INTO sounds VALUES ('Pedro_chip_RED.mp3','Me dá meu Chipe',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('Grilo_GREEN.mp3','Grilo',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('Tudumpa_ORANGE.mp3','Tudum pa',1,'ORANGE')");

		db.execSQL("INSERT INTO sounds VALUES ('Hum_boiola_GREEN.mp3','Hm Boiola',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('Adoro_Perigo_YELLOW.mp3','Adoro Perigo',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES ('Ritmo_de_Festa_BLUE.mp3','Ritmo de Festa',1,'BLUE')");

		db.execSQL("INSERT INTO sounds VALUES ('Vera_Verao_GREEN.mp3','Vera Verão',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES ('Sou_foda_2_BLUE.mp3','Sou foda 2',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('Cleber_Machado_GREEN.mp3','Cleber Machado',1,'GREEN')");

		db.execSQL("INSERT INTO sounds VALUES ('Travesti_BLUE.mp3','Travesti',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES ('Jeremias_RED.mp3','Jeremias',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES ('Ronaldo_BLUE.mp3','Ronaldo',1,'BLUE')");

		db.setTransactionSuccessful();
		db.endTransaction();
	}
}
