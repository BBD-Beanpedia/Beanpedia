package net.ryan.Service;

import net.ryan.DTO.BeanDTO;
import net.ryan.Entities.BasicBeanInformation;
import net.ryan.Repository.BeanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeanService {

    BeanRepository beanRepository;

    @Autowired
    BeanService(BeanRepository beanRepository){
        this.beanRepository = beanRepository;
    }

    public List<BeanDTO> getBeans(){

        List<BasicBeanInformation> beanEntities = beanRepository.getAllBeans();

        System.out.println(beanEntities.get(0).getBeanID());

        /*List<BeanDTO> returnBeans = new ArrayList<>();

        for(BeanEntity beanE : beanEntities){

            BeanDTO returnBean = new BeanDTO();

            returnBean.setBean_id(beanE.getBean_id());
            returnBean.setBean_title(beanE.getBean_title());
            returnBean.setBean_content(beanE.getBean_content());

            returnBeans.add(returnBean);
        }

        return returnBeans;*/
        return null;
    }

}
