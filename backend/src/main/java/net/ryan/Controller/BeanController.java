package net.ryan.Controller;

import net.ryan.DTO.SearchBeanDTO;
import net.ryan.Service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeanController {

    BeanService beanService;

    @Autowired
    BeanController(BeanService beanService){
        this.beanService = beanService;
    }


    //TODO:Need to check for spaces and capital letters in the name field...
    @GetMapping(value =  "/search/{name}")
    public SearchBeanDTO searchBeanByName(@PathVariable("name") String name){
        return this.beanService.searchBeanByName(name);
    }

}
