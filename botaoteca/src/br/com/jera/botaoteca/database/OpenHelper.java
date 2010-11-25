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
		db.execSQL(DataHelper.DROP_SQL );
		
		Log.i(DataHelper.LOG_TAG, "Creating database");
		db.execSQL(DataHelper.CREATE_SQL);
		db.setVersion(newVersion);
		populateDatabase(db);

	}
	
	  private void populateDatabase(SQLiteDatabase db) {
		db.beginTransaction();
		db.execSQL("INSERT INTO sounds VALUES('serracomedor.mp3','Serra Comedor',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES('atoron.mp3','Adoro Perigo',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES('dilma.mp3','Oi dilma',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES('hmboiola.mp3','Humm Boiola',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES('hojenao.mp3','Cleber Machado',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES('ladygaga.mp3','Ta ouvindo?',1,'BLUE')");
		
		db.execSQL("INSERT INTO sounds VALUES('quebom.mp3','Que bom',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES('travesti.mp3','Travesti',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES('xingarnotwitter.mp3','Xingar no twitter',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES('vera.mp3','Vera verão',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES('tudumpa.mp3','Tudun pa',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES('rica.mp3','Sou Rica',1,'RED')");
		
		db.execSQL("INSERT INTO sounds VALUES('funkjoelsantana.mp3','Joel Santana',1,'YELLOW')");
		db.execSQL("INSERT INTO sounds VALUES('pedrochip.mp3','Pedro',1,'RED')");
		db.execSQL("INSERT INTO sounds VALUES('dacarrinhonao.mp3','Da carrin não',1,'GREEN')");
		db.execSQL("INSERT INTO sounds VALUES('mussum.mp3','Mussum',1,'ORANGE')");
		db.execSQL("INSERT INTO sounds VALUES('jeremias.mp3','Jeremias',1,'BLUE')");
		db.execSQL("INSERT INTO sounds VALUES('arveres.mp3','Arveres',1,'GREEN')");
		
		db.setTransactionSuccessful();
		db.endTransaction();
	    }

}
