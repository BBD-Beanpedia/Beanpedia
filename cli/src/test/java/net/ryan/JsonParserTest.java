package net.ryan;

import net.ryan.bean.BeanShapeModel;
import net.ryan.bean.BeanTypeModel;
import net.ryan.util.JsonParser;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JsonParserTest {

    @Test
    void parseBeanTypeList() {
        String j = "{\"content\":[{\"typeId\":1,\"beanType\":\"Legumes\",\"description\":\"This is a broad category that includes all beans and peas that grow in pods. Legumes are known for their ability to fix nitrogen in the soil, making them an important part of sustainable agriculture.\"},{\"typeId\":2,\"beanType\":\"Pulses\",\"description\":\"Pulses are the edible seeds of plants in the legume family. This category specifically refers to the dried seed and includes lentils, chickpeas, and dried peas and beans. Pulses are a staple in diets around the world due to their high protein and fiber content.\"},{\"typeId\":3,\"beanType\":\"Fresh Beans\",\"description\":\"These are beans harvested while still young and tender, before the seed inside the pod has fully matured. Green beans and snap beans fall into this category.\"},{\"typeId\":4,\"beanType\":\"Dried Beans\",\"description\":\"These are mature beans that are dried to remove moisture so they can be stored for long periods. Kidney beans, black beans, and pinto beans are examples of dried beans.\"},{\"typeId\":5,\"beanType\":\"Shell Beans\",\"description\":\"Shell beans are beans that are grown to full maturity, harvested, and then removed from their pods before eating. Unlike dried beans, shell beans are often cooked and eaten fresh. Examples include fresh lima beans and edamame (young soybeans).\"},{\"typeId\":6,\"beanType\":\"Oilseeds\",\"description\":\"Some legumes are grown primarily for the extraction of oil from their seeds. Soybeans are the most notable example in this category, used to produce vegetable oil and various soy products.\"}],\"pageable\":{\"pageNumber\":0,\"pageSize\":100,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":6,\"first\":true,\"size\":100,\"number\":0,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"numberOfElements\":6,\"empty\":false}";
        final List<BeanTypeModel> models = JsonParser.parseBeanType(j);
        models.forEach(System.out::println);

    }

    @Test
    void parseBeanShapeList() {
        String j = "{\"content\":[{\"shapeId\":1,\"shape\":\"Kidney-Shaped\",\"description\":\"Characteristic of kidney beans, with a curved surface and resembling a kidney.\"},{\"shapeId\":2,\"shape\":\"Round\",\"description\":\"Spherical or nearly spherical, common in peas and some types of lentils.\"},{\"shapeId\":3,\"shape\":\"Oval\",\"description\":\"A more elongated round shape, typical of navy beans and pinto beans.\"},{\"shapeId\":4,\"shape\":\"Elongated\",\"description\":\"Longer than they are wide, seen in runner beans and some types of Italian beans.\"},{\"shapeId\":5,\"shape\":\"Flat\",\"description\":\"Long and narrow, common in string beans and green beans.\"},{\"shapeId\":6,\"shape\":\"Irregular\",\"description\":\"Non-uniform shapes, which can be seen in heirloom varieties or certain types of dried beans.\"},{\"shapeId\":7,\"shape\":\"Heart-Shaped\",\"description\":\"This is less common but can be seen in some bean varieties when viewed from certain angles.\"},{\"shapeId\":8,\"shape\":\"Crescent-Shaped\",\"description\":\"Similar to kidney beans but with a more pronounced curve.\"}],\"pageable\":{\"pageNumber\":0,\"pageSize\":100,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"offset\":0,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":8,\"first\":true,\"size\":100,\"number\":0,\"sort\":{\"sorted\":false,\"empty\":true,\"unsorted\":true},\"numberOfElements\":8,\"empty\":false}";
        final List<BeanShapeModel> models = JsonParser.parseBeanShapeList(j);
        models.forEach(System.out::println);

    }

}
