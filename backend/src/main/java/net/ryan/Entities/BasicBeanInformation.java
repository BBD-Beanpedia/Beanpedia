package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BasicBeanInformation\"")
@Data
public class BasicBeanInformation {

    @Id
    @Column(name = "\"BeanId\"")
    Integer BeanID;

    @Basic
    @Column(name = "\"BeanName\"")
    String BeanName;

    @Basic
    @Column(name = "\"ScientificName\"")
    String ScientificNam;

    @Basic
    @Column(name = "\"BeanContent\"")
    String BeanContent;

    @Basic
    @Column(name = "\"OriginId\"")
    Integer OriginId;

    @Basic
    @Column(name = "\"TypeId\"")
    Integer TypeID;

    @Basic
    @Column(name = "\"ShapeId\"")
    Integer ShapeId;

    @Basic
    @Column(name = "\"ColourId\"")
    Integer ColourID;

}
