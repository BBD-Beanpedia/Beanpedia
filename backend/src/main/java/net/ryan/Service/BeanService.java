package net.ryan.Service;

import net.ryan.DTO.SearchBeanDTO;
import net.ryan.Entities.*;
import net.ryan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeanService {

    BasicBeanInformationRepository basicBeanInformationRepository;
    BeanColourRepository beanColourRepository;
    BeanOriginRepository beanOriginRepository;
    BeanShapeRepository beanShapeRepository;
    BeanTypeRepository beanTypeRepository;

    @Autowired
    BeanService(BasicBeanInformationRepository basicBeanInformationRepository, BeanColourRepository beanColourRepository, BeanOriginRepository beanOriginRepository, BeanShapeRepository beanShapeRepository, BeanTypeRepository beanTypeRepository){
        this.basicBeanInformationRepository = basicBeanInformationRepository;
        this.beanColourRepository = beanColourRepository;
        this.beanOriginRepository = beanOriginRepository;
        this.beanShapeRepository = beanShapeRepository;
        this.beanTypeRepository = beanTypeRepository;
    }

    public SearchBeanDTO searchBeanByName(String name){

        //need to update this to consider if more than one bean is returned when searching by name
        BasicBeanInformation searchResult = this.basicBeanInformationRepository.searchBeanByName(name).get(0);

        SearchBeanDTO returnDTO = new SearchBeanDTO();

        BeanType beanType = this.beanTypeRepository.getTypeByID(searchResult.getTypeId());
        BeanShape beanShape = this.beanShapeRepository.getShapeByID(searchResult.getShapeId());
        BeanColour beanColour = this.beanColourRepository.getColourByID(searchResult.getColourId());
        BeanOrigin beanOrigin = this.beanOriginRepository.getOriginByID(searchResult.getOriginId());

        returnDTO.setName(searchResult.getBeanName());
        returnDTO.setType(beanType.getBeanType());
        returnDTO.setShape(beanShape.getShape());
        returnDTO.setColour(beanColour.getColour());
        returnDTO.setOrigin(beanOrigin.getOrigin());

        return returnDTO;

    }

}
