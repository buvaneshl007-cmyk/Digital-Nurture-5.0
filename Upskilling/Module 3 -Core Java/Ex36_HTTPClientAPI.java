import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Ex36_HTTPClientAPI {
    public static void main(String[] args) throws Exception {
        String url = "https://api.github.com/users/octocat";

        HttpClient  client  = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github.v3+json")
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code : " + response.statusCode());
        System.out.println("Response Body:");
        System.out.println(response.body());
        String body = response.body();
        extractField(body, "login");
        extractField(body, "name");
        extractField(body, "public_repos");
        extractField(body, "followers");
    }

    static void extractField(String json, String field) {
        String key = "\"" + field + "\"";
        int idx = json.indexOf(key);
        if (idx == -1) return;
        int colon = json.indexOf(':', idx) + 1;
        int end   = json.indexOf(',', colon);
        if (end == -1) end = json.indexOf('}', colon);
        System.out.println(field + " → " + json.substring(colon, end).trim().replace("\"", ""));
    }
}