package net.ryan.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"BeanColour\"")
@Data
public class BeanColour {

    @Id
    @Column(name = "\"ColourID\"")
    Integer ColourID;

    @Basic
    @Column(name = "\"Colour\"")
    String Colour;

    @Basic
    @Column(name = "\"Description\"")
    String Description;

}
