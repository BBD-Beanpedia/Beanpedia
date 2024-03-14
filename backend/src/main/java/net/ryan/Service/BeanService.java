package net.ryan.Service;

import net.ryan.DTO.BeanDTO;
import net.ryan.Entities.*;
import net.ryan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    /*
    1 = name
    2 = scientific name
    3 = content
    4 = origin
    5 = type
    6 = shape
    7 = colour
     */
    public boolean updateBean(BeanDTO beanDTO){

        AtomicBoolean updateDone = new AtomicBoolean(false);

        switch (beanDTO.getSelection()){
            case "1":
                this.basicBeanInformationRepository.updateBeanName(beanDTO.getNewBeanName(), beanDTO.getBeanName());
                updateDone.set(true);
                break;
            case "2":
                this.basicBeanInformationRepository.updateScientificName(beanDTO.getScientificName(), beanDTO.getBeanName());
                updateDone.set(true);
                break;
            case "3":
                this.basicBeanInformationRepository.updateBeanContent(beanDTO.getContent(), beanDTO.getBeanName());
                updateDone.set(true);
                break;
            case "4":

                beanDTO.setOrigin(beanDTO.getOrigin().toLowerCase().replaceAll("\\s+", ""));

                List<BeanOrigin> beanOrigins = this.beanOriginRepository.getAllBeans();

                beanOrigins.stream().forEach(beanOrigin -> {
                    beanOrigin.setOrigin(beanOrigin.getOrigin().toLowerCase().replaceAll("\\s+", ""));
                });

                beanOrigins.stream().forEach(beanOrigin -> {
                    if(beanDTO.getOrigin().equals(beanOrigin.getOrigin())){
                        updateDone.set(true);
                        this.basicBeanInformationRepository.updateOriginId(beanOrigin.getOriginId(), beanDTO.getBeanName());
                    }
                });

                break;
            case "5":

                beanDTO.setType(beanDTO.getType().toLowerCase().replaceAll("\\s+", ""));

                List<BeanType> beanTypes = this.beanTypeRepository.getAllBeans();

                beanTypes.stream().forEach(beanType -> {
                    beanType.setBeanType(beanType.getBeanType().toLowerCase().replaceAll("\\s+", ""));
                });

                beanTypes.stream().forEach(beanType -> {
                    if(beanDTO.getType().equals(beanType.getBeanType())){
                        updateDone.set(true);
                        this.basicBeanInformationRepository.updateTypeId(beanType.getTypeId(), beanDTO.getBeanName());
                    }
                });

                break;
            case "6":

                beanDTO.setShape(beanDTO.getShape().toLowerCase().replaceAll("\\s+", ""));

                List<BeanShape> beanShapes = this.beanShapeRepository.getAllBeans();

                beanShapes.stream().forEach(beanShape -> {
                    beanShape.setShape(beanShape.getShape().toLowerCase().replaceAll("\\s+", ""));
                });

                beanShapes.stream().forEach(beanShape -> {
                    if(beanDTO.getShape().equals(beanShape.getShape())){
                        updateDone.set(true);
                        this.basicBeanInformationRepository.updateShapeId(beanShape.getShapeId(), beanDTO.getBeanName());
                    }
                });

                break;
            case "7":

                beanDTO.setColour(beanDTO.getColour().toLowerCase().replaceAll("\\s+", ""));

                List<BeanColour> beanColours = this.beanColourRepository.getAllBeans();

                beanColours.stream().forEach(beanColour -> {
                    beanColour.setColour(beanColour.getColour().toLowerCase().replaceAll("\\s+", ""));
                });


                beanColours.stream().forEach(beanColour -> {
                    if(beanDTO.getColour().equals(beanColour.getColour())){
                        updateDone.set(true);
                        this.basicBeanInformationRepository.updateColourId(beanColour.getColourId(), beanDTO.getBeanName());
                    }
                });

                break;
            default:
                break;
        }

        return updateDone.get();

    }

    public String createBean(BeanDTO beanDTO){

        BasicBeanInformation basicBeanInformation = new BasicBeanInformation();

        basicBeanInformation.setBeanName(beanDTO.getBeanName());
        basicBeanInformation.setScientificName(beanDTO.getScientificName());
        basicBeanInformation.setBeanContent(beanDTO.getContent());

        ////////////////////////////////////////////

        List<BeanOrigin> beanOrigins = beanOriginRepository.getAllBeans();

        beanOrigins.stream().forEach(beanOrigin -> {
            if(beanOrigin.getOrigin().toLowerCase().replaceAll("\\s+", "").equals(beanDTO.getOrigin().toLowerCase().replaceAll("\\s+", ""))){
                basicBeanInformation.setOriginId(beanOrigin.getOriginId());
            }
        });

        if(basicBeanInformation.getOriginId() == null){
            return "The origin is not recognised. Please try again.";
        }


        ///////////////////////////////////////

        List<BeanType> beanTypes = beanTypeRepository.getAllBeans();

        beanTypes.stream().forEach(beanType -> {
            if(beanType.getBeanType().toLowerCase().replaceAll("\\s+", "").equals(beanDTO.getType().toLowerCase().replaceAll("\\s+", ""))){
                basicBeanInformation.setTypeId(beanType.getTypeId());
            }
        });

        if(basicBeanInformation.getTypeId() == null){
            return "The bean type is not recognised. Please try again.";
        }

        ///////////////////////////////////////

        List<BeanShape> beanShapes = beanShapeRepository.getAllBeans();

        beanShapes.stream().forEach(beanShape -> {
            if(beanShape.getShape().toLowerCase().replaceAll("\\s+", "").equals(beanDTO.getShape().toLowerCase().replaceAll("\\s+", ""))){
                basicBeanInformation.setShapeId(beanShape.getShapeId());
            }
        });

        if(basicBeanInformation.getShapeId() == null){
            return "The bean shape is not recognised. Please try again.";
        }

        ///////////////////////////////////////

        List<BeanColour> beanColours = beanColourRepository.getAllBeans();

        beanColours.stream().forEach(beanColour -> {
            if(beanColour.getColour().toLowerCase().replaceAll("\\s+", "").equals(beanDTO.getColour().toLowerCase().replaceAll("\\s+", ""))){
                basicBeanInformation.setColourId(beanColour.getColourId());
            }
        });

        if(basicBeanInformation.getColourId() == null){
            return "The bean colour is not recognised. Please try again.";
        }

        basicBeanInformationRepository.save(basicBeanInformation);

        return "Bean saved successfully!";

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
