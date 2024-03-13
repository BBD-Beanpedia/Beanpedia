package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanShape\"")
@Data
public class BeanShape {

    @Id
    @Column(name = "\"ShapeId\"")
    Integer shapeId;

    @Basic
    @Column(name = "\"Shape\"")
    String shape;

    @Basic
    @Column(name = "\"Description\"")
    String description;
}
