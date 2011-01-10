package br.com.jera.botaoteca.database;

import java.util.List;

import android.test.AndroidTestCase;
import br.com.jera.botaoteca.Botao;

public class DataHelperTest extends AndroidTestCase {

	DataHelper dataHelper;
	@Override
	protected void setUp() throws Exception {
		DataHelper.setTesting(true);
		dataHelper = new DataHelper(getContext());
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
	
	public void testCreateButtonsFromDatabase(){
	    	DataHelper.setTesting(true);
		List<Botao> buttons = dataHelper.createButtonsFromDatabase();
		assertEquals("should create 18 buttons",18 , buttons.size());
	}

}
