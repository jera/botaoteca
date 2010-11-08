package br.com.jera.botaoteca.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.jera.botaoteca.Button;
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
	sql.append("name TEXT, ");
	sql.append("type INTEGER, ");
	sql.append("color TEXT");
	sql.append(")");

	CREATE_SQL = String.format(sql.toString(), TABLE_NAME);
	DROP_SQL = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public List<Button> createButtonsFromDatabase() {
	Cursor cursor = db.rawQuery("SELECT fileName, name, color, type FROM "
		+ TABLE_NAME, null);
	List<Button> buttons = new ArrayList<Button>();
	if (cursor.moveToFirst()) {
	    do {
		int type = cursor.getInt(3);

		String fileName = cursor.getString(0);
		String name = cursor.getString(1);
		String color = cursor.getString(2);

		if (type == 1) {
		    Button button;
		    try {
			button = new Button(ButtonColor.valueOf(color),
				context, new EmbeddedSound(fileName, context));
			button.setName(name);
			buttons.add(button);
		    } catch (Exception e) {
			Log.e("FILE", "file "+ fileName + " not found");
		    }
		}
	    } while (cursor.moveToNext());

	}
	return buttons;
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
