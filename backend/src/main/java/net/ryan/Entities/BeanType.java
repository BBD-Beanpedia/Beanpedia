package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanType\"")
@Data
public class BeanType {

    @Id
    @Column(name = "\"TypeId\"")
    Integer TypeID;

    @Basic
    @Column(name = "\"BeanType\"")
    String BeanType;

    @Basic
    @Column(name = "\"Description\"")
    String Description;
}
