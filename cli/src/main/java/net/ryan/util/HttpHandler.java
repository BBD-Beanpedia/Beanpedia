package net.ryan.util;

import net.ryan.bean.JsonSerializable;
import net.ryan.exception.NotFoundException;
import net.ryan.exception.UnauthorisedException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Stream;

public class HttpHandler {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private enum Method {
        GET, POST
    }

    public static class Request {
        private final HttpRequest.Builder builder;
        private Method method;

        private static Result<Request> createRequest(Method method, String url) {
            return Result.from(() -> new Request(method, url));
        }

        private Request(Method method, String url) throws URISyntaxException {
            this.builder = HttpRequest.newBuilder()
                                      .uri(new URI(url));
            this.method = method;
        }

        public Request bearer(String token) {
            builder.header("Authorization", "Bearer " + token);

            return this;
        }

        public Request bodyString(String string) {
            builder.header("Content-Type", "text/plain");
            builder.method(method.name(), HttpRequest.BodyPublishers.ofString(string));
            method = null;

            return this;
        }

        public Request bodyForm(String string) {
            builder.header("Content-Type", "application/x-www-form-urlencoded");
            builder.method(method.name(), HttpRequest.BodyPublishers.ofString(string));
            method = null;

            return this;
        }

        public <T extends JsonSerializable> Request bodyJson(T jsonClass) {
            return bodyJson(jsonClass.toJsonString());
        }

        public Request bodyJson(String string) {
            builder.header("Content-Type", "application/json");
            //builder.method(method.name(), HttpRequest.BodyPublishers.ofString(string));
            //method = null;
            //builder.

            //System.out.println(builder);

            builder.POST(HttpRequest.BodyPublishers.ofString(string));

            return this;
        }

      /*  public Request bodyJson(Object object) {
            builder.header("Content-Type", "application/json");
            builder.method(method.name(), HttpRequest.BodyPublishers.ofString(GSON.toJson(object)));
            method = null;

            return this;
        }*/

        private <T> Result<T> send(String accept, HttpResponse.BodyHandler<T> responseBodyHandler) {
            builder.header("Accept", accept);
            if (method != null) builder.method(method.name(), HttpRequest.BodyPublishers.noBody());

            try {
                final HttpRequest request = builder.build();
//                System.out.println(request);
                HttpResponse<T> res = CLIENT.send(request, responseBodyHandler);
                int statusCode = res.statusCode();
                if (statusCode == 200) return Result.success(res.body());
                else if (statusCode == 404) {
                    return Result.fail(new NotFoundException("Http Error code " + statusCode + "Please login"));
                } else
                    return Result.fail(new UnauthorisedException("Http Error code: " + statusCode + "\t" + request.uri() + " Not found"));
            } catch (IOException | InterruptedException e) {
                return Result.fail(e);
            }
        }

        public Result<InputStream> sendInputStream() {
            return send("*/*", HttpResponse.BodyHandlers.ofInputStream());
        }

        public Result<String> sendString() {
            return send("*/*", HttpResponse.BodyHandlers.ofString());
        }

        public Result<String> sendJson() {
            return send("application/json", HttpResponse.BodyHandlers.ofString());
        }


        public Result<Stream<String>> sendLines() {
            return send("*/*", HttpResponse.BodyHandlers.ofLines());
        }

       /* public <T> T sendJson(Type type) {
            InputStream in = send("application/json", HttpResponse.BodyHandlers.ofInputStream());
            return in == null ? null : GSON.fromJson(new InputStreamReader(in), type);
        }*/
    }

    public static Result<Request> newGetRequest(String url) {
        return Request.createRequest(Method.GET, url);
    }

    public static Result<Request> newPostRequest(String url) {
        return Request.createRequest(Method.POST, url);
    }
}
