package net.ryan.bean;

public record BeanModel(
        int beanId,
        String beanName,
        String scientificName,
        String content
) {
}
