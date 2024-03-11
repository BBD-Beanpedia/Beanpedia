package net.ryan.Controller;

import net.ryan.DTO.BeanDTO;
import net.ryan.TimoRefactorLater.Service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeanController {

    BeanService beanService;

    @Autowired
    BeanController(BeanService beanService){
        this.beanService = beanService;
    }

    @GetMapping(value = "/getBeans")
    List<BeanDTO> test(){

        return beanService.getBeans();

    }

}
