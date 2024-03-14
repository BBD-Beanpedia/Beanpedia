package net.ryan.util;

import net.ryan.bean.*;

import java.util.List;
import java.util.function.Function;

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
            INSERT_ENDPOINT = "/beans/addBean",

            GET_ALL_ENDPOINT = "/beans/all",
            SEARCH_ENDPOINT = "/beans/search",
            UPDATE_ENDPOINT = "/beans/update",

            FILTER = "/beans/filter",
            ORIGIN_FILTER = "/beans/attributes/origins",
            SHAPE_ENDPOINT = "/beans/attributes/shapes",
            TYPE_ENDPOINT = "/beans/attributes/types",
            COLOUR_ENDPOINT = "/beans/attributes/colours",
            WELCOME="/greeter",
            GITHUB_AUTH="https://github.com/login",
            GITHUB_DEVICE = "/device/code",
            GITHUB_POLL = "/oauth/access_token";
    // @formatter:on

    private String authToken = "eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiam1vdXRvbjE5IiwiZ2l0aHViX2lkIjoxMjI4MjA4OTksImV4cCI6MTcxMDQ2MDMxNiwiaWF0IjoxNzEwNDE3MTE2fQ.SqVGuDItVkaTxyKZoPGP-sWNbef7mhNaNom-C83saz9T7VLqFWWxvwcaXk44FWZrXj1ywwiVRDSHB5kl9fyNEvWs85n3d09Px4pA7ufSbMwf7wPRwP6vpGGXZZH-JFHyGp70VjNItFFf-2nrSDz_RAaPVBTAiboGkvt42VSoXRzRLzJg4yZUgD7IE_joh8usL1UvcPqTE5s_EyQ3vv33ppe86Te_RA-sHOXx999ZvObaCL7Nlz_R9Qr6p-Hqljl6UUYg3g-ZDxRi9aD8S95ObZ09rP1bB9DCGCifM9-gOmsFnDv5y561VYasjjFXX6GS4xinePjdFC8ln7P6mGHcKQ";

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
                          .map(Boolean::parseBoolean);
    }



    public Result<String> createBean(String newBeanData) {
        return HttpHandler.newPostRequest(BASE_URL + INSERT_ENDPOINT)
                .map(request -> request.bearer(authToken))
                .map(request -> request.bodyJson(newBeanData))
                .mapToNew(HttpHandler.Request::sendJson);
                //.map(r -> System.out.println(r));
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

    public Result<BeanModelPage> searchBeanByShape(FilterPageModel filterPageModel) {
        return genericSearch("?shapeId=%d&page=%d".formatted(filterPageModel.id(), filterPageModel.page()));
    }

    public Result<BeanModelPage> searchBeanByType(FilterPageModel filterPageModel) {
        return genericSearch("?typeId=%d&page=%d".formatted(filterPageModel.id(), filterPageModel.page()));
    }

    public Result<BeanModelPage> searchBeanByColour(FilterPageModel filterPageModel) {
        return genericSearch("?colourId=%d&page=%d".formatted(filterPageModel.id(), filterPageModel.page()));
    }

    public Result<BeanModelPage> searchBeanByOrigin(FilterPageModel filterPageModel) {
        return genericSearch("?originId=%d&page=%d".formatted(filterPageModel.id(), filterPageModel.page()));
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
                          .map(request -> request.bodyJsonReal("{\"client_id\": \"2f4dc8cbcebf01a1ae88\"}"))
                          .mapToNew(HttpHandler.Request::sendJson)
                          .map(JsonParser::parseGithubAuth).ifError(e -> e.printStackTrace());
    }

    public Result<String> getGithubPoll(String deviceCode) {
        return HttpHandler.newPostRequest(GITHUB_AUTH + GITHUB_POLL)
                          .map(request -> request.bodyJsonReal("{\"client_id\": \"2f4dc8cbcebf01a1ae88\", \"device_code\": \"" + deviceCode + "\", \"grant_type\": \"urn:ietf:params:oauth:grant-type:device_code\"}"))
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

    public Result<Void> updateBean(BeanModelFull beanModel) {
        return HttpHandler.newPostRequest(GITHUB_AUTH + GITHUB_POLL)
                          .map(request -> request.bearer(authToken))
                          .mapToNew(HttpHandler.Request::sendString)
                          .map(_r -> null);
    }
}
