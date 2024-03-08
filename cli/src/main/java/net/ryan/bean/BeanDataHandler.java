package net.ryan.bean;

import net.ryan.util.HttpHandler;
import net.ryan.util.Result;

import java.util.List;

public class BeanDataHandler {

    private HttpHandler client;
    private String baseUrl = "http://localhost:8080/";

    public Result<List<ShortBeanModel>> requestListOfShortBeans() {

       /* Result<ShortBeanModel> map = HttpHandler.newGetRequest(baseUrl)
                .mapToNew(HttpHandler.Request::sendString)
                .map(s -> {
//                    s.
                });*/
//        return ;
        return null;
    }




}
