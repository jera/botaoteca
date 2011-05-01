package br.com.jera.botaoteca;

import java.io.IOException;

import org.json.JSONException;

import android.test.ActivityInstrumentationTestCase2;
import br.com.jera.botaoteca.download.DownloadActivity;

public class DownloadActivityTest extends ActivityInstrumentationTestCase2<DownloadActivity> {

	public DownloadActivityTest(String pkg, Class<DownloadActivity> activityClass) {
		super(pkg, activityClass);
	}

	public void testGetSoundsInfo() throws JSONException, IOException {
		String jString = "{\"sounds\":[{\"name\": \"Serra_Comedor_BLUE\"}, {\"name\": \"Dilma_oi_RED\"}] }";
		getActivity().createSoundsInfo(jString);
//		List<JSONObject> sounds = getActivity().getSounds();
//		assertEquals("should retrieve 2 sounds", 2, sounds.size());
//
//		assertEquals("should retrieve correct property", sounds.get(0).get("name"), "Serra_Comedor_BLUE");
	}
}
