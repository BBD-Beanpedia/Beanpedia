package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanShape\"")
@Data
public class BeanShape {

    @Id
    @Column(name = "\"ShapeID\"")
    Integer ShapeID;

    @Basic
    @Column(name = "\"Shape\"")
    String Shape;

    @Basic
    @Column(name = "\"Description\"")
    String Description;
}
