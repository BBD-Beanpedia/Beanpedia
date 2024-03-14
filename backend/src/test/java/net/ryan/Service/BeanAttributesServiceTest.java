package net.ryan.Service;

import net.ryan.dto.*;
import net.ryan.entities.*;
import net.ryan.Repository.*;
import net.ryan.service.BeanAttributesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BeanAttributesServiceTest {

  @Mock
  private BeanColourRepository beanColourRepository;

  @Mock
  private BeanOriginRepository beanOriginRepository;

  @Mock
  private BeanShapeRepository beanShapeRepository;

  @Mock
  private BeanTypeRepository beanTypeRepository;

  @InjectMocks
  private BeanAttributesService beanAttributesService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void findAllColours() {
    BeanColour beanColour = new BeanColour();
    Page<BeanColour> page = new PageImpl<>(Collections.singletonList(beanColour));
    when(beanColourRepository.findAll(any(Pageable.class))).thenReturn(page);

    Page<BeanColourDTO> result = beanAttributesService.findAllColours(Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }

  @Test
  void findAllOrigins() {
    BeanOrigin beanOrigin = new BeanOrigin();
    Page<BeanOrigin> page = new PageImpl<>(Collections.singletonList(beanOrigin));
    when(beanOriginRepository.findAll(any(Pageable.class))).thenReturn(page);

    Page<BeanOriginDTO> result = beanAttributesService.findAllOrigins(Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }

  @Test
  void findAllShapes() {
    BeanShape beanShape = new BeanShape();
    Page<BeanShape> page = new PageImpl<>(Collections.singletonList(beanShape));
    when(beanShapeRepository.findAll(any(Pageable.class))).thenReturn(page);

    Page<BeanShapeDTO> result = beanAttributesService.findAllShapes(Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }

  @Test
  void findAllTypes() {
    BeanType beanType = new BeanType();
    Page<BeanType> page = new PageImpl<>(Collections.singletonList(beanType));
    when(beanTypeRepository.findAll(any(Pageable.class))).thenReturn(page);

    Page<BeanTypeDTO> result = beanAttributesService.findAllTypes(Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }
}
