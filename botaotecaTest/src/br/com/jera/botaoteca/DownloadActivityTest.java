package br.com.jera.botaoteca;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.test.ActivityInstrumentationTestCase2;

public class DownloadActivityTest extends ActivityInstrumentationTestCase2<DownloadActivity> {

	public DownloadActivityTest() {
		super(DownloadActivity.class);
	}

	public void testGetSoundsInfo() throws JSONException {
		String jString = "{\"sounds\":[{\"name\": \"Serra Comedor\", \"filename\": \"serracomedor.mp3\", \"color\": \"BLUE\"}, {\"name\": \"Dilma oi\", \"filename\": \"oidilma.mp3\", \"color\": \"RED\"}] }";
		getActivity().getSoundsInfo(jString);
		List<JSONObject> sounds = getActivity().getSounds();
		assertEquals("should retrieve 2 sounds", 2,sounds.size());
		
		assertEquals("should retrieve correct property", sounds.get(0).get("name"),"Serra Comedor");
		assertEquals("should retrieve correct property", sounds.get(0).get("filename"),"serracomedor.mp3");
		assertEquals("should retrieve correct property", sounds.get(0).get("color"),"BLUE");
	}
}
