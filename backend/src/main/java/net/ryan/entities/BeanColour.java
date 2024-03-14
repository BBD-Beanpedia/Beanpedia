package net.ryan.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanColour\"")
@Data
public class BeanColour {

    @Id
    @Column(name = "\"ColourId\"")
    Integer colourId;

    @Basic
    @Column(name = "\"Colour\"")
    String colour;

    @Basic
    @Column(name = "\"Description\"")
    String description;

}
