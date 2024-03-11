package net.ryan.bean;


public record BeanModel(int beanId, String beanName, String scientificName,
                        String content) implements JsonSerializable {

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

}
