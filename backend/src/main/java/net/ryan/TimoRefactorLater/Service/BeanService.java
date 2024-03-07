package net.ryan.TimoRefactorLater.Service;

import net.ryan.TimoRefactorLater.DTO.BeanDTO;
import net.ryan.TimoRefactorLater.Entities.BeanEntity;
import net.ryan.TimoRefactorLater.Repository.BeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BeanService {

    BeanRepository beanRepository;

    @Autowired
    BeanService(BeanRepository beanRepository){
        this.beanRepository = beanRepository;
    }

    public List<BeanDTO> getBeans(){

        List<BeanEntity> beanEntities = beanRepository.getBeans();

        List<BeanDTO> returnBeans = new ArrayList<>();

        for(BeanEntity beanE : beanEntities){

            BeanDTO returnBean = new BeanDTO();

            returnBean.setBean_id(beanE.getBean_id());
            returnBean.setBean_title(beanE.getBean_title());
            returnBean.setBean_content(beanE.getBean_content());

            returnBeans.add(returnBean);
        }

        return returnBeans;
    }

}
