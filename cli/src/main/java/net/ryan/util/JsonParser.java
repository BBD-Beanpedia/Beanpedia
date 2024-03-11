package net.ryan.util;

import net.ryan.bean.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {

    public static List<BeanModel> parseBean(String json) {
        //"BeanId":(\d+),"BeanName":"(.*?)","ScientificName":"(.*?)","BeanContent":"(.*?)","OriginId":(\d+),"TypeId":(\d+),"ShapeId":(\d+),"ColourId":(\d+)
        String regex = "\"BeanId\":(\\d+),\"BeanName\":\"(.*?)\",\"ScientificName\":\"(.*?)\",\"BeanContent\":\"(.*?)\",\"OriginId\":(\\d+),\"TypeId\":(\\d+),\"ShapeId\":(\\d+),\"ColourId\":(\\d+)";
        final Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        final List<BeanModel> beanModels = new ArrayList<>();
        while (matcher.find()) {
            final String beanId = matcher.group(1);
            final String beanName = matcher.group(2);
            final String beanContent = matcher.group(3);
            final String beanScientificName = matcher.group(4);
            final String originId = matcher.group(5);
            final String typeId = matcher.group(6);
            final String shapeId = matcher.group(7);
            final String colourId = matcher.group(8);
            beanModels.add(new BeanModel(Integer.parseInt(beanId), beanName, beanContent, beanScientificName, Integer.parseInt(originId), Integer.parseInt(typeId), Integer.parseInt(shapeId), Integer.parseInt(colourId)));
        }

        return beanModels;
    }

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

        final Pattern pattern = Pattern.compile(jsonRegex);
        final Matcher matcher = pattern.matcher(json);

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
