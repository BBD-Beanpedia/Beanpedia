package net.ryan.util;

import net.ryan.bean.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    public static BeanModel parseBeanDetail(String json) {
        //Use regex to parse the json {}

        return null;
    }

    public static Pair<Integer, List<ShortBeanModel>> parsePagedBeanList(String string) {
        return null;
    }

    public static List<BeanOriginModel> parseBeanOriginList(String json) {
        return null;
    }

    public static List<BeanTypeModel> parseBeanType(String string) {
        return null;
    }

    public static List<BeanColourModel> parseBeanColourList(String string) {
        return null;
    }

    public static List<BeanShapeModel> parseBeanShapeList(String string) {
        //TODO: Use regex to parse the json { 'a':'b' }


        return null;
    }

    public static String parseWelcome(String string) {
        return string;
    }

    public static GithubAuthDeviceModel parseGithubAuth(String json) {
        String jsonRegex = "\\{\\s*\"device_code\":\"([^\"]*)\",\\s*\"user_code\":\"([^\"]*)\",\\s*\"verification_uri\":\"([^\"]*)\",\\s*\"expires_in\":(\\d+),\\s*\"interval\":(\\d+)\\s*\\}";

        Pattern pattern = Pattern.compile(jsonRegex);
        Matcher matcher = pattern.matcher(json);

        if (matcher.matches()) {
            String deviceCode = matcher.group(1);
            String userCode = matcher.group(2);
            String verificationUri = matcher.group(3);
            int expiresIn = Integer.parseInt(matcher.group(4));
            int interval = Integer.parseInt(matcher.group(5));
            return new GithubAuthDeviceModel(deviceCode, userCode, verificationUri, expiresIn, interval);
        }
        return null;
    }

    public static String parseGithubPoll(String s) {
        if (s != null) {
            final String regex = "\"access_token\":\"(.*?)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }
}
