package net.ryan.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanOrigin\"")
@Data
public class BeanOrigin {

    @Id
    @Column(name = "\"OriginId\"")
    Integer originId;

    @Basic
    @Column(name = "\"Origin\"")
    String origin;

}
