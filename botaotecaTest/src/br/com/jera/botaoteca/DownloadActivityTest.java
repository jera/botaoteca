package br.com.jera.botaoteca;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;

public class DownloadActivityTest extends ActivityInstrumentationTestCase2<DownloadActivity> {


	public DownloadActivityTest(String pkg, Class<DownloadActivity> activityClass) {
		super(pkg, activityClass);
	}

	public void testGetSoundsInfo() throws JSONException {
		String jString = "{\"sounds\":[{\"name\": \"Serra_Comedor_BLUE\"}, {\"name\": \"Dilma_oi_RED\"}] }";
		getActivity().getSoundsInfo(jString);
		List<JSONObject> sounds = getActivity().getSounds();
		assertEquals("should retrieve 2 sounds", 2,sounds.size());
		
		assertEquals("should retrieve correct property", sounds.get(0).get("name"),"Serra_Comedor_BLUE");
	}
}
