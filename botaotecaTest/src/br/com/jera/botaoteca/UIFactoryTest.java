package br.com.jera.botaoteca;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TableRow;
import br.com.jera.botaoteca.database.DataHelper;

public class UIFactoryTest extends ActivityInstrumentationTestCase2<Principal> {

    DataHelper dataHelper;

    @Override
    protected void setUp() throws Exception {
	DataHelper.setTesting(true);
	dataHelper = new DataHelper(getActivity());
    }

    public UIFactoryTest() {
	super(Principal.class);
    }

    public void testCreateRows(){
	List<Button> buttons = dataHelper.createButtonsFromDatabase();
	List<TableRow> rows = UIFactory.createRows(getActivity(), buttons);
	
	int nRows = buttons.size()/3 + buttons.size()%3;
	assertEquals("should create "+nRows+" rows.",nRows, rows.size());
    }
}
