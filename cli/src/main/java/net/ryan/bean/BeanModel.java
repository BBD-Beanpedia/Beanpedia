package net.ryan.bean;


import java.util.Objects;

public final class BeanModel implements JsonSerializable {
    private final int beanId;
    private String beanName;
    private String scientificName;
    private String content;
    private final int originId;
    private final int typeId;
    private final int shapeId;
    private final int colourId;


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
