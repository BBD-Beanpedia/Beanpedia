package net.ryan.Entities;

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
