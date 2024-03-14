package net.ryan.bean;


import java.util.Objects;

public final class BeanModel implements JsonSerializable {
    private final int beanId;
    private String beanName;
    private String newBeanName;
    private String scientificName;
    private String content;
    private final int originId;
    private String origin;
    private final int typeId;
    private String type;
    private final int shapeId;
    private String shape;
    private final int colourId;
    private  String colour;
    private String selection;

    public BeanModel(int beanId, String beanName, String scientificName, String content, int originId, int typeId, int shapeId, int colourId) {
        this.beanId = beanId;
        this.beanName = beanName;
        this.scientificName = scientificName;
        this.content = content;
        this.originId = originId;
        this.typeId = typeId;
        this.shapeId = shapeId;
        this.colourId = colourId;
    }

    public String toJsonString() {
        return String.format("""
                    {
                        "beanId": "%s",
                        "beanName": "%s",
                        "scientificName": "%s",
                        "content": "%s"
                    }
                """, beanId, beanName, scientificName, content);
    }

    public String toJsonStringUpdate() {

        return String.format("""
                    {
                        "beanName": "%s",
                        "scientificName": "%s",
<<<<<<< HEAD
                        "content": "%s",
=======
                        "beanContent": "%s",
>>>>>>> 33ff2803f412121d27fa18455766039f03ab1e48
                        "origin": "%s",
                        "type": "%s",
                        "shape": "%s",
                        "colour":"%s",
                        "selection": "%s",
                        "newBeanName": "%s"
                    }
                """, beanName, scientificName, content, origin, type, shape, colour, selection, newBeanName);
    }



    public int getBeanId() {
        return beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin(){return this.origin;}

    public void setOrigin(String origin){this.origin = origin;}

    public String getType(){return this.type;}

    public void setType(String type){this.type = type;}

    public String getShape(){return this.shape;}

    public void setShape(String shape){this.shape = shape;}

    public String getColour(){return this.colour;}

    public void setColour(String colour){this.colour = colour;}

    public void setSelection(String selection){this.selection = selection;}

    public void setNewBeanName(String newBeanName){this.newBeanName = newBeanName;}

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BeanModel) obj;
        return this.beanId == that.beanId && Objects.equals(this.beanName, that.beanName) && Objects.equals(this.scientificName, that.scientificName) && Objects.equals(this.content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanId, beanName, scientificName, content);
    }

    @Override
    public String toString() {
        return "BeanModel{" +
                "beanId=" + beanId +
                ", beanName='" + beanName + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", content='" + content + '\'' +
                ", originId=" + originId +
                ", typeId=" + typeId +
                ", shapeId=" + shapeId +
                ", colourId=" + colourId +
                '}';
    }
}
