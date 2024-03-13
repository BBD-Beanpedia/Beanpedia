package net.ryan.Service;

import net.ryan.DTO.BeanDTO;
import net.ryan.Entities.*;
import net.ryan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<BeanDTO> searchBeansByName(String name, Pageable pageable) {
        Page<BasicBeanInformation> searchResults = basicBeanInformationRepository.searchBeanByName(name, pageable);
        return searchResults.map(this::convertToDto);
    }

    public Page<BeanDTO> findAllBeans(Pageable pageable) {
        Page<BasicBeanInformation> beanPage = basicBeanInformationRepository.findAll(pageable);
        return beanPage.map(this::convertToDto);
    }

    public Page<BeanDTO> findBeansByCriteria(Integer typeId, Integer shapeId, Integer colourId, Integer originId, Pageable pageable) {
        Page<BasicBeanInformation> beansPage = basicBeanInformationRepository.findByTypeShapeColourOrigin(typeId, shapeId, colourId, originId, pageable);
        return beansPage.map(this::convertToDto);
    }

    private BeanDTO convertToDto(BasicBeanInformation bean) {
        BeanDTO dto = new BeanDTO();
        dto.setBeanName(bean.getBeanName());
        dto.setScientificName(bean.getScientificName());
        dto.setContent(bean.getBeanContent());

        beanTypeRepository.findById(bean.getTypeId())
                .ifPresent(beanType -> dto.setType(beanType.getBeanType()));
        beanShapeRepository.findById(bean.getShapeId())
                .ifPresent(beanShape -> dto.setShape(beanShape.getShape()));
        beanColourRepository.findById(bean.getColourId())
                .ifPresent(beanColour -> dto.setColour(beanColour.getColour()));
        beanOriginRepository.findById(bean.getOriginId())
                .ifPresent(beanOrigin -> dto.setOrigin(beanOrigin.getOrigin()));

        return dto;
    }
}
