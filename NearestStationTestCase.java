package automationFramework;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NearestStationTestCase {
 
    @Before
    public void setUp() throws Exception {		
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHyattAustinExists() throws Exception {
		try {
			String apiKey = "t4oUJLrv5ERXYb6rfvKgcAUVcY6uVCLABz2MiTBo";
			
			// Query for nearest stations to Austin, TX that are part of the "ChargePoint Network"
			String urlStr = "http://developer.nrel.gov/api/alt-fuel-stations/v1/nearest.json?api_key="+apiKey+"&location=AUSTIN,TX&env_network=ChargePoint%20Network";
			URL url = new URL(urlStr);
			HttpURLConnection conn = getConnection(url);
			JSONObject nearestStations = makeApiCall(url);
			JSONArray stations = nearestStations.getJSONArray("fuel_stations");

			// Verify that "HYATT AUSTIN" appears in the results
			boolean exist = false;
			int stationID = 0;
			for (int i = 0; i < stations.length(); i++) {				
				String stationName = stations.getJSONObject(i).getString("station_name");
				if (stationName.equals("HYATT AUSTIN")) {
					exist = true;
					stationID = stations.getJSONObject(i).getInt("id");
				}
			}
			assertTrue("HYATT AUSTIN station exists", exist);
			closeConnection(conn);
			
			// Use the station ID to get query the API and return the street address of that station
			urlStr = "http://developer.nrel.gov/api/alt-fuel-stations/v1/"+stationID+".json?api_key="+apiKey;
			url = new URL(urlStr);
			conn = getConnection(url);
			JSONObject stationObj = makeApiCall(url);

			JSONObject station = stationObj.getJSONObject("alt_fuel_station");
			
			// Verify the station address is 208 Barton Springs, Austn, TX 78704
			String streetAddress = station.getString("street_address");
			assertEquals("208 Barton Springs Rd", streetAddress);
			String city = station.getString("city");
			assertEquals("Austin", city);
			String state = station.getString("state");
			assertEquals("TX", state);
			String zip = station.getString("zip");
			assertEquals("78704", zip);
			closeConnection(conn);
		} 
		catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HttpURLConnection getConnection(URL url) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		//if (conn.getResponseCode() != 200) {
			//throw new RuntimeException("HTTP error code:" + conn.getResponseCode());
		//}
		
		return conn;
	}
	
	public void closeConnection(HttpURLConnection conn) throws IOException {
		conn.disconnect();
	}
	
	public JSONObject makeApiCall(URL url) throws Exception {	
		Scanner scan = new Scanner(url.openStream());
		String entireResponse = new String();
		
		while (scan.hasNext()) {
			entireResponse += scan.nextLine();
		}
		//System.out.println("Response : "+entireResponse);
		scan.close();
	
		JSONObject obj = new JSONObject(entireResponse);
	
		return obj;
	}
}
