package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BasicBeanInformation\"", schema = "\"public\"")
@Data
public class BasicBeanInformation {

    @Id
    @Column(name = "\"BeanId\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
