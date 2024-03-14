package net.ryan.Controller;

import net.ryan.dto.BeanColourDTO;
import net.ryan.dto.BeanOriginDTO;
import net.ryan.dto.BeanShapeDTO;
import net.ryan.dto.BeanTypeDTO;
import net.ryan.service.BeanAttributesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/beans/attributes")
public class BeanAttributesController {

    private final BeanAttributesService beanAttributesService;

    public BeanAttributesController(BeanAttributesService beanAttributesService) {
        this.beanAttributesService = beanAttributesService;
    }

    @GetMapping("/colours")
    public Page<BeanColourDTO> getAllBeanColours(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "100") int size) {
        return beanAttributesService.findAllColours(PageRequest.of(page, size));
    }

    @GetMapping("/origins")
    public Page<BeanOriginDTO> getAllBeanOrigins(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "100") int size) {
        return beanAttributesService.findAllOrigins(PageRequest.of(page, size));
    }

    @GetMapping("/shapes")
    public Page<BeanShapeDTO> getAllBeanShapes(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "100") int size) {
        return beanAttributesService.findAllShapes(PageRequest.of(page, size));
    }

    @GetMapping("/types")
    public Page<BeanTypeDTO> getAllBeanTypes(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "100") int size) {
        return beanAttributesService.findAllTypes(PageRequest.of(page, size));
    }
}
