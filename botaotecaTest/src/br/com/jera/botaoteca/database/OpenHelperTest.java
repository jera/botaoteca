package br.com.jera.botaoteca.database;

import java.util.ArrayList;
import java.util.List;

import br.com.jera.botaoteca.database.DataHelper;
import br.com.jera.botaoteca.database.OpenHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class OpenHelperTest extends AndroidTestCase {

	private Context context;
	private OpenHelper helper;
	
	@Override
	protected void setUp() throws Exception {
		context = getContext();
		DataHelper.setTesting(true);
		helper = new OpenHelper(context);
	}
	
	@Override
	protected void tearDown() throws Exception {
		DataHelper.setTesting(false);
	}
	
	public void testOnCreate() {
		
		List<String> list = new ArrayList<String>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
		
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(0));
			} while (cursor.moveToNext());

		}
		
		assertTrue("Should contain the 'sounds' table", list.contains(DataHelper.TABLE_NAME));
	}
	
	public void testOnUpgrade(){
		
		SQLiteDatabase db = helper.getReadableDatabase();
		int oldVersion = db.getVersion();
		helper.onUpgrade(db, db.getVersion(), db.getVersion()+1);
		db = helper.getReadableDatabase();
		
		assertEquals("Should increment database version", oldVersion + 1, db.getVersion());
	}
}
