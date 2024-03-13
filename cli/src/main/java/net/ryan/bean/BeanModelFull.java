package net.ryan.bean;


import net.ryan.cli.Nameable;

public final class BeanModelFull implements JsonSerializable, Nameable {
    private final int beanId;
    private String beanName;
    private String scientificName;
    private String content;
    private String origin;
    private String type;
    private String shape;
    private String colour;

    public BeanModelFull(String beanName, String scientificName, String content, String origin, String type, String shape, String colour) {
        this.beanId = -1;
        this.beanName = beanName;
        this.scientificName = scientificName;
        this.content = content;
        this.origin = origin;
        this.type = type;
        this.shape = shape;
        this.colour = colour;
    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public String getName() {
        return beanName;
    }

    public String getContent() {
        return content;
    }

    public String getOrigin() {
        return origin;
    }

    public String getType() {
        return type;
    }

    public String getShape() {
        return shape;
    }

    public String getColour() {
        return colour;
    }

    public int getBeanId() {
        return beanId;
    }

    public String getScientificName() {
        return this.scientificName;
    }
}