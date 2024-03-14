package net.ryan.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import net.ryan.dto.BeanDTO;
import net.ryan.entities.BasicBeanInformation;
import net.ryan.repository.*;
import net.ryan.service.BeanService;
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

class BeanServiceTest {

  @Mock
  private BasicBeanInformationRepository basicBeanInformationRepository;

  @Mock
  private BeanColourRepository beanColourRepository;

  @Mock
  private BeanOriginRepository beanOriginRepository;

  @Mock
  private BeanShapeRepository beanShapeRepository;

  @Mock
  private BeanTypeRepository beanTypeRepository;

  @InjectMocks
  private BeanService beanService;

  @BeforeEach
  void setUp() {
    // Initialize mocks created above
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void searchBeansByName() {
    BasicBeanInformation beanInfo = new BasicBeanInformation();
    Page<BasicBeanInformation> page = new PageImpl<>(Collections.singletonList(beanInfo));
    when(basicBeanInformationRepository.searchBeanByName(anyString(), any(Pageable.class))).thenReturn(page);

    Page<BeanDTO> result = beanService.searchBeansByName("testName", Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }

  @Test
  void findAllBeans() {
    BasicBeanInformation beanInfo = new BasicBeanInformation();
    Page<BasicBeanInformation> page = new PageImpl<>(Collections.singletonList(beanInfo));
    when(basicBeanInformationRepository.findAll(any(Pageable.class))).thenReturn(page);

    Page<BeanDTO> result = beanService.findAllBeans(Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }

  @Test
  void findBeansByCriteria() {
    BasicBeanInformation beanInfo = new BasicBeanInformation();
    Page<BasicBeanInformation> page = new PageImpl<>(Collections.singletonList(beanInfo));
    when(basicBeanInformationRepository.findByTypeShapeColourOrigin(anyInt(), anyInt(), anyInt(), anyInt(), any(Pageable.class))).thenReturn(page);

    Page<BeanDTO> result = beanService.findBeansByCriteria(1, 1, 1, 1, Pageable.unpaged());

    assertNotNull(result);
    assertEquals(1, result.getContent().size());
  }
}
