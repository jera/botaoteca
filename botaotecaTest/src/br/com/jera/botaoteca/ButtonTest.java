package br.com.jera.botaoteca;

import android.test.ActivityInstrumentationTestCase2;
import br.com.jera.botaoteca.Principal;

import com.jayway.android.robotium.solo.Solo;

public class ButtonTest extends ActivityInstrumentationTestCase2<Principal>{
	
	private Solo solo;
	
	public ButtonTest() {
		super(Principal.class);
	}
	
	 public void setUp() throws Exception {
	        solo = new Solo(getInstrumentation(), getActivity());
	  }
	 
	
	 //O teste não possui nenhum assert pois não é possivel verificar o estado do mediaPlayer
	 //portanto no caso de erro, uma exceção será lançada e o teste falhara.
	public void testPlayDownloadedSound(){
		solo.clickOnButton("bichona.mp3");
	}
	
	public void testPlayEmbeddedSound(){
		solo.clickOnButton("serracomedor.mp3");
	}
	
}
