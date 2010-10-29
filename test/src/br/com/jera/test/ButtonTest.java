package br.com.jera.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.LinearLayout;
import br.com.jera.Principal;

import com.jayway.android.robotium.solo.Solo;

public class ButtonTest extends ActivityInstrumentationTestCase2<Principal>{
	
	private Solo solo;
	
	public ButtonTest() {
		super(Principal.class);
	}
	
	 public void setUp() throws Exception {
	        solo = new Solo(getInstrumentation(), getActivity());
	  }
	
	
	public void testPlay(){
		solo.clickOnButton("bichona.mp3");
	}
	
}
