package net.ryan.DTO;

public class BeanDTO {
    int bean_id;
    String bean_title;
    String bean_content;

    //I can't get lombok to work :(
    public Integer getBean_id(){return this.bean_id;}
    public String getBean_title(){return this.bean_title;}
    public String getBean_content(){return this.bean_content;}

    public void setBean_id(Integer bean_id){this.bean_id=bean_id;}
    public void setBean_title(String bean_title){this.bean_title=bean_title;}
    public void setBean_content(String bean_content){this.bean_content=bean_content;}

}
