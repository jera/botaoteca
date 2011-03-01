package br.com.jera.botaoteca.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.jera.botaoteca.AppButton;
import br.com.jera.botaoteca.ButtonColor;
import br.com.jera.botaoteca.sound.EmbeddedSound;

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
	sql.append("name TEXT COLLATE NOCASE, ");
	sql.append("type INTEGER, ");
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

    public void close() {
	this.db.close();
    }

    public AppButton findButton(String fileName) {
	Cursor cursor = db
		.rawQuery("SELECT  name, color, type FROM " + TABLE_NAME
			+ " WHERE fileName = ?", new String[] { fileName });

	if (cursor.moveToFirst()) {
	    int type = cursor.getInt(2);
	    String color = cursor.getString(1);

	    if (type == 1) {
		AppButton button;
		try {
		    button = new AppButton(ButtonColor.valueOf(color), context,
			    new EmbeddedSound(fileName, context));
		    cursor.close();
		    return button;
		} catch (Exception e) {
		    Log.e("FILE", "file " + fileName + " not found");
		}
	    }

	}
	cursor.close();
	return null;
    }

    public List<AppButton> createButtonsFromDatabase() {
	Cursor cursor = db.rawQuery("SELECT fileName, name, color, type FROM "
		+ TABLE_NAME+" ORDER BY name", null);
	List<AppButton> buttons = new ArrayList<AppButton>();
	if (cursor.moveToFirst()) {
	    do {
		int type = cursor.getInt(3);

		String fileName = cursor.getString(0);
		String name = cursor.getString(1);
		String color = cursor.getString(2);

		if (type == 1) {
		    AppButton button;
		    try {
			button = new AppButton(ButtonColor.valueOf(color), context,
				new EmbeddedSound(fileName, context));
			button.setName(name);
			buttons.add(button);
		    } catch (Exception e) {
			Log.e("FILE", "file " + fileName + " not found");
		    }
		}
	    } while (cursor.moveToNext());

	}
	cursor.close();
	
	return buttons;
    }

    public List<AppButton> filterButtons(String search) {
	List<AppButton> buttons = new ArrayList<AppButton>();
	Cursor cursor = db.rawQuery("SELECT fileName, name, color, type FROM "
		+ TABLE_NAME + " WHERE name like '%"+search+"%'", null);
	
	if (cursor.moveToFirst()) {
	    do {
		int type = cursor.getInt(3);

		String fileName = cursor.getString(0);
		String name = cursor.getString(1);
		String color = cursor.getString(2);

		if (type == 1) {
		    AppButton button;
		    try {
			button = new AppButton(ButtonColor.valueOf(color), context,
				new EmbeddedSound(fileName, context));
			button.setName(name);
			buttons.add(button);
		    } catch (Exception e) {
			Log.e("FILE", "file " + fileName + " not found");
		    }
		}
	    } while (cursor.moveToNext());

	}
	cursor.close();
	return buttons;
	
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

    public static void setTesting(boolean testing) {
	DataHelper.testing = testing;
    }

}
