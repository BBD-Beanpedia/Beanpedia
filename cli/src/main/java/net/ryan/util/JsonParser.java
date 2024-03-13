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
        final Pattern pagePattern = Pattern.compile(regex);
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

    public static BeanModelPage parsePagedBeanList(String json) {
        List<BeanModelFull> list = new ArrayList<>();
        int totalPages;
        final Pattern pagePattern = Pattern.compile("\"totalPages\":(\\d+)");
        final Matcher pageMatcher = pagePattern.matcher(json);
        if (pageMatcher.find()) {
            totalPages = Integer.parseInt(pageMatcher.group(1));
        } else {
            totalPages = 0;
        }
        final Pattern beanPattern = Pattern.compile("\"beanName\":\\s*\"(?<beanName>[^\"]+)\",\\s*" + "\"scientificName\":\\s*\"(?<scientificName>[^\"]+)\",\\s*" + "\"content\":\\s*\"(?<content>[^\"]+)\",\\s*" + "\"origin\":\\s*\"(?<origin>[^\"]+)\",\\s*" + "\"type\":\\s*\"(?<type>[^\"]+)\",\\s*" + "\"shape\":\\s*\"(?<shape>[^\"]+)\",\\s*" + "\"colour\":\\s*\"(?<colour>[^\"]+)\"");
        final Matcher matcher = beanPattern.matcher(json);

        while (matcher.find()) {
            list.add(new BeanModelFull(matcher.group("beanName"), matcher.group("scientificName"), matcher.group("content"), matcher.group("origin"), matcher.group("type"), matcher.group("shape"), matcher.group("colour")));
        }

        return new BeanModelPage(list, totalPages);
    }

    public static List<BeanOriginModel> parseBeanOriginList(String json) {
        final Pattern originPattern = Pattern.compile("\"originId\":\\s*(?<originId>\\d+),\\s*" + "\"origin\":\\s*\"(?<origin>[^\"]+)\"");
        List<BeanOriginModel> list = new ArrayList<>();
        final Matcher matcher = originPattern.matcher(json);
        while (matcher.find()) {
            list.add(new BeanOriginModel(Integer.parseInt(matcher.group("originId")), matcher.group("origin")));
        }
        return list;
    }

    public static List<BeanTypeModel> parseBeanType(String json) {
        final Pattern originPattern = Pattern.compile("\"typeId\":\\s*(?<typeId>\\d+),\\s*" + "\"beanType\":\\s*\"(?<beanType>[^\"]+)\",\\s*" + "\"description\":\\s*\"(?<description>[^\"]+)\"");
        List<BeanTypeModel> list = new ArrayList<>();
        final Matcher matcher = originPattern.matcher(json);
        while (matcher.find()) {
            list.add(new BeanTypeModel(Integer.parseInt(matcher.group("typeId")), matcher.group("beanType"), matcher.group("description")));
        }
        return list;
    }

    public static List<BeanColourModel> parseBeanColourList(String json) {
        final Pattern originPattern = Pattern.compile("\"colourId\":\\s*(?<colourId>\\d+),\\s*" + "\"colour\":\\s*\"(?<colour>[^\"]+)\",\\s*" + "\"description\":\\s*\"(?<description>[^\"]+)\"");
        List<BeanColourModel> list = new ArrayList<>();
        final Matcher matcher = originPattern.matcher(json);
        while (matcher.find()) {
            list.add(new BeanColourModel(Integer.parseInt(matcher.group("colourId")), matcher.group("colour"), matcher.group("description")));
        }
        return list;
    }

    public static List<BeanShapeModel> parseBeanShapeList(String json) {
        final Pattern originPattern = Pattern.compile("\"shapeId\":\\s*(?<shapeId>\\d+),\\s*" + "\"shape\":\\s*\"(?<shape>[^\"]+)\",\\s*" + "\"description\":\\s*\"(?<description>[^\"]+)\"");
        List<BeanShapeModel> list = new ArrayList<>();
        final Matcher matcher = originPattern.matcher(json);
        while (matcher.find()) {
            list.add(new BeanShapeModel(Integer.parseInt(matcher.group("shapeId")), matcher.group("shape"), matcher.group("description")));
        }

        return list;
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
