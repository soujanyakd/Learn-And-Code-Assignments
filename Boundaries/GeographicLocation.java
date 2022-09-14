import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class GeographicLocation {

    private static final String BASE_URL = "http://api.positionstack.com/v1/forward?";
    private static final String API_KEY = "access_key=212efcfde0808cd64043151b924527d4";

    public HttpRequest initializeGetRequest(String locationName) {
        String encodedQuery = null;
        try {
            encodedQuery = URLEncoder.encode(locationName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Enable to encode Location Name");
        }
        String requestUri = BASE_URL + API_KEY + "&query=" + encodedQuery;
        HttpRequest getRequest = HttpRequest.newBuilder().GET().uri(URI.create(requestUri))
                .timeout(Duration.ofMillis(2000)).build();
        return getRequest;
    }

    public JSONObject getResponse(String locationName) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse geocodingResponse = null;
        try {
            geocodingResponse = httpClient.send(initializeGetRequest(locationName),
                    HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println("Enable to perform GET request");
        }
        JSONObject responseData = new JSONObject(geocodingResponse.body().toString());
        return responseData;
    }

    public String getLatitudeOfSpecifiedLocation(String locationName) {
        JSONArray locationDetails = getResponse(locationName).getJSONArray("data");
        JSONObject locationData = locationDetails.getJSONObject(0);
        return locationData.get("latitude").toString();
    }

    public String getLongitudeOfSpecifiedLocation(String locationName) {
        JSONArray locationDetails = getResponse(locationName).getJSONArray("data");
        JSONObject locationData = locationDetails.getJSONObject(0);
        return locationData.get("longitude").toString();
    }
}