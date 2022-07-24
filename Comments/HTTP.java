

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class HTTP {
    public static String API_URL = "https://%s.tumblr.com/api/read/json?type=photo&num=50&start=0";

    public static HttpClient establishHTTPConnection() {
        HttpClient clientConnection = HttpClient.newHttpClient();
        return clientConnection;
    }

    public static HttpRequest buildGetRequest(String blogName) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(API_URL, blogName)))
                .GET()
                .build();
        return request;
    }

    public static HttpResponse getResponse(HttpClient clientConnection, HttpRequest request) {
        try {
            HttpResponse response = clientConnection.send(request, HttpResponse.BodyHandlers.ofString());
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject convertAPIResponseToJSONObject(HttpResponse response) {
        //Removing "var tumblr_api_read = " because to convert the response to JSONObject it must start with "{"
        JSONObject responseData = new JSONObject(response.body().toString().replaceFirst("var tumblr_api_read = ", ""));
        return responseData;
    }

    public static void displayPostDetails(JSONObject responseData) {
        JSONObject tumblelog_data = responseData.getJSONObject("tumblelog");
        System.out.println("title: " + tumblelog_data.get("title"));
        System.out.println("name: " + tumblelog_data.get("name"));
        System.out.println("description: " + tumblelog_data.get("description"));
        System.out.println("no of post: " + responseData.get("posts-total"));
    }

    public static void displayPostsLinksOfSpecifiedRange(JSONObject responseData, String range) {
        int startIndex = Integer.parseInt(range.split("-")[0]);
        int endIndex = Integer.parseInt(range.split("-")[1]);
        JSONArray array = responseData.getJSONArray("posts");
        for (int index = startIndex; index <= endIndex; index++) {
            JSONObject post_data = array.getJSONObject(index);
            System.out.println(index + ". " + post_data.getString("photo-url-1280"));
        }
    }

    public static void main(String[] args) {
        HttpClient clientConnection = establishHTTPConnection();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the Tumblr blog name: ");
        String blogName = sc.nextLine();
        System.out.println("Enter the range(Ex: 1-5) : ");
        String postRange = sc.nextLine();
        HttpRequest request = buildGetRequest(blogName);
        HttpResponse response = getResponse(clientConnection, request);
        JSONObject responseData = convertAPIResponseToJSONObject(response);
        displayPostDetails(responseData);
        displayPostsLinksOfSpecifiedRange(responseData, postRange);
    }
}
