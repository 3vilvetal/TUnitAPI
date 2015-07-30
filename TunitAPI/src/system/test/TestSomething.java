package system.test;

import org.json.JSONException;
import org.json.JSONObject;

import system.file.FSUtils;

public class TestSomething {
	
	public static void main(String args[]) throws JSONException {
		//remove
		FSUtils fs = new FSUtils();
		String track = fs.getString("src/system/example.json");
		
		JSONObject json = new JSONObject(track);
		JSONObject testResults = json.getJSONObject("result");
		
		System.out.println(testResults.getString("suite"));
	}
}
