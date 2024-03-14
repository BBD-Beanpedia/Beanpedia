package net.ryan.util;

import net.ryan.bean.*;

import java.util.List;
import java.util.function.Function;

// Cool somehow IntelliJ sees lombok being used even though it is not in gradle project.
@SuppressWarnings("LombokGetterMayBeUsed")
public class BeanDataHandler {

    private static BeanDataHandler instance;

    public static BeanDataHandler getInstance() {
        //noinspection StatementWithEmptyBody
        if (instance == null) instance = new BeanDataHandler();
        else {/*NO-OPP*/}
        return instance;
    }

    private BeanDataHandler() {
    }

    // @formatter:off
    private static final String
            BASE_URL = "http://34.249.42.139:8080",
            //BASE_URL = "http://localhost:8080",
            INSERT_ENDPOINT = "/addBean",
            GET_ALL_ENDPOINT = "/beans/all",
            SEARCH_ENDPOINT = "/beans/search",
            UPDATE_ENDPOINT = "/beans/update",
            FILTER = "/beans/filter",
            ORIGIN_FILTER = "/beans/attributes/origins",
            SHAPE_ENDPOINT = "/beans/attributes/shapes",
            TYPE_ENDPOINT = "/beans/attributes/types",
            COLOUR_ENDPOINT = "/beans/attributes/colours",
            WELCOME="/greeter",
            GITHUB_AUTH="https://github.com/login/",
            GITHUB_DEVICE = "/device/code",
            GITHUB_POLL = "oauth/access_token";
    // @formatter:on

    private String authToken;

    public void saveAuthFromFile() {

    }

    public void readAuthFromFile() {

    }

    public String getAuthToken() {
        return authToken;
    }


    public Result<BeanModelPage> searchBean(String beanName, int page) {
        return HttpHandler.newGetRequest(BASE_URL + SEARCH_ENDPOINT + "?name=" + beanName.strip() + "&page=" + page + "&size=5")
                          .map(request -> request.bearer(authToken))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parsePagedBeanList);
    }

    public Result<BeanModelPage> requestListOfBeansPaged(int page) {
        final String url = BASE_URL + GET_ALL_ENDPOINT + "?page=" + page + "&size=5";
        return HttpHandler.newGetRequest(url)
                          .map(request -> request.bearer(authToken))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parsePagedBeanList);
    }


    //public Result<BeanModel> updateBean(BeanModel newBeanData) {
    public Result<Boolean> updateBean(String newBeanData) {
        return HttpHandler.newPostRequest(BASE_URL + UPDATE_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(newBeanData))
                          .mapToNew(HttpHandler.Request::sendJson)
                          .map(_s -> true);
    }

    public final Result<Boolean> insertBean(BeanModel model) {
        return HttpHandler.newPostRequest(BASE_URL + INSERT_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(model))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(_s -> true);
    }

    public Result<List<BeanOriginModel>> requestAllOrigins() {
        return basicGetQuery(ORIGIN_FILTER, JsonParser::parseBeanOriginList);
    }

    public Result<List<BeanShapeModel>> requestAllShapes() {
        return basicGetQuery(SHAPE_ENDPOINT, JsonParser::parseBeanShapeList);
    }

    public Result<List<BeanTypeModel>> requestAllTypes() {
        return basicGetQuery(TYPE_ENDPOINT, JsonParser::parseBeanType);
    }

    public Result<List<BeanColourModel>> requestAllColours() {
        return basicGetQuery(COLOUR_ENDPOINT, JsonParser::parseBeanColourList);
    }


    private <T> Result<T> basicGetQuery(String endpoint, Function<String, T> parseFunction) {
        return HttpHandler.newGetRequest(BASE_URL + endpoint)
                          .map(request -> request.bearer(authToken))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(parseFunction);
    }

    public Result<BeanModelPage> searchBeanByShape(int shapeId) {
        return genericSearch("?shapeId=" + shapeId);
    }

    public Result<BeanModelPage> searchBeanByType(int typeId) {
        return genericSearch("?typeId=" + typeId);
    }

    public Result<BeanModelPage> searchBeanByColour(int colourId) {
        return genericSearch("?colourId=" + colourId);
    }

    public Result<BeanModelPage> searchBeanByOrigin(int originId) {
        return genericSearch("?originId=" + originId);
    }

    private Result<BeanModelPage> genericSearch(String text) {
        return HttpHandler.newGetRequest(BASE_URL + FILTER + text)
                          .map(request -> request.bearer(authToken))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parsePagedBeanList);
    }


    public Result<String> getWelcome() {
        return basicGetQuery(WELCOME, JsonParser::parseWelcome);
    }

    public Result<GithubAuthDeviceModel> getGithubAuth() {
        return HttpHandler.newPostRequest(GITHUB_AUTH + GITHUB_DEVICE)
                          .map(request -> request.bodyJson("{\"client_id\": \"a2a0895678d18622ca6d\"}"))
                          .mapToNew(HttpHandler.Request::sendJson)
                          .map(JsonParser::parseGithubAuth);
    }

    public Result<String> getGithubPoll(String deviceCode) {
        return HttpHandler.newPostRequest(GITHUB_AUTH + GITHUB_POLL)
                          .map(request -> request.bodyJson("{\"client_id\": \"a2a0895678d18622ca6d\", \"device_code\": \"" + deviceCode + "\", \"grant_type\": \"urn:ietf:params:oauth:grant-type:device_code\"}"))
                          .mapToNew(HttpHandler.Request::sendJson)
                          .map(JsonParser::parseGithubPoll);
    }

    public void setAuthToken(String token) {
        this.authToken = token;
    }

    public void requestAndSaveToken(String token) {
        HttpHandler.newPostRequest(BASE_URL + "/auth/token")
                   .map(request -> request.bodyJson("{\"\"}"))
                   .mapToNew(HttpHandler.Request::sendJson);


    }
}
