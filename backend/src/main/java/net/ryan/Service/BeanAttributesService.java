package net.ryan.Service;

import net.ryan.DTO.*;
import net.ryan.Entities.*;
import net.ryan.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BeanAttributesService {

    private final BeanColourRepository beanColourRepository;
    private final BeanOriginRepository beanOriginRepository;
    private final BeanShapeRepository beanShapeRepository;
    private final BeanTypeRepository beanTypeRepository;

    @Autowired
    public BeanAttributesService(BeanColourRepository beanColourRepository,
                                 BeanOriginRepository beanOriginRepository,
                                 BeanShapeRepository beanShapeRepository,
                                 BeanTypeRepository beanTypeRepository) {
        this.beanColourRepository = beanColourRepository;
        this.beanOriginRepository = beanOriginRepository;
        this.beanShapeRepository = beanShapeRepository;
        this.beanTypeRepository = beanTypeRepository;
    }

    public Page<BeanColourDTO> findAllColours(Pageable pageable) {
        Page<BeanColour> colours = beanColourRepository.findAll(pageable);
        return colours.map(this::convertToColourDTO);
    }

    public Page<BeanOriginDTO> findAllOrigins(Pageable pageable) {
        Page<BeanOrigin> origins = beanOriginRepository.findAll(pageable);
        return origins.map(this::convertToOriginDTO);
    }

    public Page<BeanShapeDTO> findAllShapes(Pageable pageable) {
        Page<BeanShape> shapes = beanShapeRepository.findAll(pageable);
        return shapes.map(this::convertToShapeDTO);
    }

    public Page<BeanTypeDTO> findAllTypes(Pageable pageable) {
        Page<BeanType> types = beanTypeRepository.findAll(pageable);
        return types.map(this::convertToTypeDTO);
    }

    private BeanColourDTO convertToColourDTO(BeanColour colour) {
        BeanColourDTO dto = new BeanColourDTO();
        dto.setColourId(colour.getColourId());
        dto.setColour(colour.getColour());
        dto.setDescription(colour.getDescription());
        return dto;
    }

    private BeanOriginDTO convertToOriginDTO(BeanOrigin origin) {
        BeanOriginDTO dto = new BeanOriginDTO();
        dto.setOriginId(origin.getOriginId());
        dto.setOrigin(origin.getOrigin());
        return dto;
    }

    private BeanShapeDTO convertToShapeDTO(BeanShape shape) {
        BeanShapeDTO dto = new BeanShapeDTO();
        dto.setShapeId(shape.getShapeId());
        dto.setShape(shape.getShape());
        dto.setDescription(shape.getDescription());
        return dto;
    }

    private BeanTypeDTO convertToTypeDTO(BeanType type) {
        BeanTypeDTO dto = new BeanTypeDTO();
        dto.setTypeId(type.getTypeId());
        dto.setBeanType(type.getBeanType());
        dto.setDescription(type.getDescription());
        return dto;
    }
}
