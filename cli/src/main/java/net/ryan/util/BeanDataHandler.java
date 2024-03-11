package net.ryan.util;

import net.ryan.bean.*;

import java.util.List;
import java.util.function.Function;

public class BeanDataHandler {

    private static BeanDataHandler instance;

    public static BeanDataHandler getInstance() {
        if (instance == null) instance = new BeanDataHandler();
        else {/*NO-OPP*/}
        return instance;
    }

    private BeanDataHandler() {
    }

    // @formatter:off
    private static final String
            BASE_URL = "http://localhost:8080",
            INSERT_ENDPOINT = "/addBean",
            GET_ALL_ENDPOINT = "/get",
            SEARCH_ENDPOINT = "/search",
            UPDATE_ENDPOINT = "/update",
            DETAIL_ENDPOINT = "/get",
            ORIGIN_ENDPOINT = "/origins",
            SHAPE_ENDPOINT = "/shapes",
            TYPE_ENDPOINT = "/types",
            COLOUR_ENDPOINT = "/colours",
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


    public Result<Pair<Integer, List<ShortBeanModel>>> searchBean(String beanName, int page) {
        return HttpHandler.newPostRequest(BASE_URL + SEARCH_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(String.format("{\"page\":%d, \"beanName\":\"%s\"}", page, beanName)))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parsePagedBeanList);
    }

    public Result<List<BeanModel>> requestListBeans() {
        return HttpHandler.newGetRequest("http://34.249.42.139:8080/test"/*BASE_URL + GET_ALL_ENDPOINT*/)
                          .map(request -> request.bearer(authToken))
//                          .map(request -> request.bodyJson(String.format("{\"page\":%d}", page)))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parseBean);
    }


    public Result<Pair<Integer, List<ShortBeanModel>>> requestListOfShortBeansPaged(int page) {
        return HttpHandler.newPostRequest(BASE_URL + GET_ALL_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(String.format("{\"page\":%d}", page)))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parsePagedBeanList);
    }

    public Result<BeanModel> requestBeanDetail(int beanId) {
        return HttpHandler.newPostRequest(BASE_URL + DETAIL_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(String.format("{\"beanId\":\"%s\"}", beanId)))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parseBeanDetail);
    }


    // Cant update id so this should be ok
    public Result<BeanModel> updateBean(BeanModel newBeanData) {
        return HttpHandler.newPostRequest(BASE_URL + UPDATE_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(String.format("{\"beanData\":\"%s\"}", newBeanData.toJsonString())))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(JsonParser::parseBeanDetail);
    }

    public final Result<Boolean> insertBean(BeanModel model) {
        return HttpHandler.newPostRequest(BASE_URL + INSERT_ENDPOINT)
                          .map(request -> request.bearer(authToken))
                          .map(request -> request.bodyJson(model))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(_s -> true);
    }

    public Result<List<BeanOriginModel>> requestAllOrigins() {
        return basicGetQuery(ORIGIN_ENDPOINT, JsonParser::parseBeanOriginList);
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


    public void searchBeanByShape(String name, int i) {

    }

    public void searchBeanByType(String name, int i) {

    }

    public void searchBeanByColour(String name, int i) {

    }

    public void searchBeanByOrigin(String name, int i) {

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
        HttpHandler.newPostRequest(BASE_URL + "/token")
                   .map(request -> request.bodyJson(""))
                   .mapToNew(HttpHandler.Request::sendJson);


    }
}
