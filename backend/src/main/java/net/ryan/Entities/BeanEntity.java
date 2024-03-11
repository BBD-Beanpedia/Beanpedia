package net.ryan.Entities;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class BeanEntity {

    @Id
    @Column(name = "bean_id")
    private Integer bean_id;

    @Basic
    @Column(name = "bean_title")
    private String bean_title;

    @Basic
    @Column(name = "bean_content")
    private String bean_content;

    //I can't get lombok to work :(
    public Integer getBean_id(){return this.bean_id;}
    public String getBean_title(){return this.bean_title;}
    public String getBean_content(){return this.bean_content;}

}
