package net.ryan.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BasicBeanInformation\"")
@Data
public class BasicBeanInformation {

    @Id
    @Column(name = "\"BeanId\"")
    Integer beanId;

    @Basic
    @Column(name = "\"BeanName\"")
    String beanName;

    @Basic
    @Column(name = "\"ScientificName\"")
    String scientificName;

    @Basic
    @Column(name = "\"BeanContent\"")
    String beanContent;

    @Basic
    @Column(name = "\"OriginId\"")
    Integer originId;

    @Basic
    @Column(name = "\"TypeId\"")
    Integer typeId;

    @Basic
    @Column(name = "\"ShapeId\"")
    Integer shapeId;

    @Basic
    @Column(name = "\"ColourId\"")
    Integer colourId;

}
