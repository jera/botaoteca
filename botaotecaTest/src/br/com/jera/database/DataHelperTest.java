package br.com.jera.database;

import android.test.AndroidTestCase;

public class DataHelperTest extends AndroidTestCase {

	@Override
	protected void setUp() throws Exception {
		DataHelper.setTesting(true);
	}

	@Override
	protected void tearDown() throws Exception {
		DataHelper.setTesting(false);
	}

	public void testDatabaseName() {
		DataHelper.setTesting(false);
		assertEquals("Should be equal to botaoteca.db ",DataHelper.DATABASE_NAME, DataHelper.getDatabaseName());
	}

	public void testTestingDatabaseName() {
		DataHelper.setTesting(true);
		assertEquals("Should be equal to botaoteca-test.db ",DataHelper.DATABASE_TEST_NAME, DataHelper.getDatabaseName());
	}

}
