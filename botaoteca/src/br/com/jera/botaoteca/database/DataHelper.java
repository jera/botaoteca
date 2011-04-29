package br.com.jera.botaoteca.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.jera.botaoteca.AppButton;
import br.com.jera.botaoteca.ButtonColor;
import br.com.jera.botaoteca.sound.DownloadedSound;
import br.com.jera.botaoteca.sound.EmbeddedSound;

public class DataHelper {

	public static final String TABLE_NAME = "sounds";
	public static final int DATABASE_VERSION = 1;
	public static final String CREATE_SQL;
	public static final String DROP_SQL;
	static final String DATABASE_NAME = "botaoteca2.db";
	static final String DATABASE_TEST_NAME = "botaoteca-test.db";

	static final String LOG_TAG = "DataBase";

	private Context context;
	private SQLiteDatabase db;
	private static boolean testing;
	private static DataHelper dataHelperInstance;

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

	public static DataHelper getDataHelper(Context context) {
		if (dataHelperInstance == null) {
			dataHelperInstance = new DataHelper(context);
		}
		dataHelperInstance.context = context;
		return dataHelperInstance;
	}

	private DataHelper(Context context) {
		this.context = context;
		OpenHelper openHelper = new OpenHelper(this.context);
		this.db = openHelper.getWritableDatabase();

	}

	public void close() {
		this.db.close();
	}

	public AppButton findButton(String fileName) {
		Cursor cursor = db.rawQuery("SELECT  fileName, name, color, type FROM " + TABLE_NAME + " WHERE fileName = ?", new String[] { fileName });
		AppButton button = null;
		if (cursor.moveToFirst()) {
			button = this.constructButton(cursor);
		}
		cursor.close();
		return button;
	}

	public List<AppButton> filterButtons(String search) {
		List<AppButton> buttons = new ArrayList<AppButton>();
		Cursor cursor = db.rawQuery("SELECT fileName, name, color, type FROM " + TABLE_NAME + " WHERE name like '%" + search + "%' ORDER BY name", null);
		if (cursor.moveToFirst()) {
			do {
				buttons.add(this.constructButton(cursor));
			} while (cursor.moveToNext());

		}
		cursor.close();
		return buttons;
	}

	public List<AppButton> createButtonsFromDatabase() {
		Cursor cursor = db.rawQuery("SELECT fileName, name, color, type FROM " + TABLE_NAME + " ORDER BY name", null);
		List<AppButton> buttons = new ArrayList<AppButton>();
		if (cursor.moveToFirst()) {
			do {
				buttons.add(this.constructButton(cursor));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return buttons;
	}

	public void insert(String fileName) {
		String[] nameSound = fileName.split("_");
		String color = nameSound[nameSound.length - 1];
		String name = getNameSound(fileName);
		db.beginTransaction();
		db.execSQL("INSERT INTO sounds VALUES ('" + fileName + ".mp3','" + name + "',2,'" + color + "')");
		db.setTransactionSuccessful();
		db.endTransaction();
	}

	private AppButton constructButton(Cursor cursor) {
		AppButton button = null;
		String fileName = cursor.getString(0);
		String name = cursor.getString(1);
		String color = cursor.getString(2);
		int type = cursor.getInt(3);
		try {
			if (type == 1) {
				button = new AppButton(ButtonColor.valueOf(color), fileName, name, context, new EmbeddedSound(fileName, context));
			} else if (type == 2) {
				button = new AppButton(ButtonColor.valueOf(color), fileName, name, context, new DownloadedSound(fileName));
			}
		} catch (Exception e) {
			Log.e("FILE", "file " + fileName + " not found");
		}
		return button;
	}

	static String getDatabaseName() {
		if (testing) {
			return DATABASE_TEST_NAME;
		} else {
			return DATABASE_NAME;
		}
	}

	public String getNameSound(String file) {
		String[] name = file.split("_");
		String nameSound = "";
		for (int i = 0; i < name.length - 1; i++) {
			nameSound += name[i] + " ";
		}
		return nameSound;
	}

	static boolean isTesting() {
		return testing;
	}

	public static void setTesting(boolean testing) {
		DataHelper.testing = testing;
	}

	public void delete(AppButton button) {
		db.beginTransaction();
		db.execSQL("DELETE FROM sounds WHERE fileName = ?",new String[] { button.getFileName() });
		db.setTransactionSuccessful();
		db.endTransaction();

	}

}
