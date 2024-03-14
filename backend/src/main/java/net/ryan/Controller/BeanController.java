package net.ryan.controller;

import net.ryan.dto.BeanDTO;
import net.ryan.Service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/beans/")
public class BeanController {

    private final BeanService beanService;

    @Autowired
    public BeanController(BeanService beanService) {
        this.beanService = beanService;
    }

    @GetMapping("/search")
    public Page<BeanDTO> searchBeans(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return beanService.searchBeansByName(name, pageable);
    }

    @GetMapping("/all")
    public Page<BeanDTO> getAllBeans(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return beanService.findAllBeans(pageable);
    }

    @GetMapping("/filter")
    public Page<BeanDTO> getFilteredBeans(
            @RequestParam(value = "typeId", required = false) Integer typeId,
            @RequestParam(value = "shapeId", required = false) Integer shapeId,
            @RequestParam(value = "colourId", required = false) Integer colourId,
            @RequestParam(value = "originId", required = false) Integer originId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "100") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return beanService.findBeansByCriteria(typeId, shapeId, colourId, originId, pageable);
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
    @PostMapping("/update")
    public void updateBean(@RequestBody BeanDTO beanDTO){
        this.beanService.updateBean(beanDTO);
    }

    @PostMapping("/insert")
    public void insertBean(@RequestBody BeanDTO beanDTO){

    }

}
