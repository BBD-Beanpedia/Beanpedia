package net.ryan.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanType\"")
@Data
public class BeanType {

    @Id
    @Column(name = "\"TypeId\"")
    Integer typeId;

    @Basic
    @Column(name = "\"BeanType\"")
    String beanType;

    @Basic
    @Column(name = "\"Description\"")
    String description;
}
