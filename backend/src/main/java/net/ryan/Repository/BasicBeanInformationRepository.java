package net.ryan.Repository;

import jakarta.transaction.Transactional;
import net.ryan.Entities.BasicBeanInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicBeanInformationRepository extends JpaRepository<BasicBeanInformation, Integer> {

    @Query("SELECT b FROM BasicBeanInformation b WHERE lower(b.beanName) LIKE lower(concat('%', :name, '%')) OR lower(b.scientificName) LIKE lower(concat('%', :name, '%'))")
    Page<BasicBeanInformation> searchBeanByName(@Param("name") String name, Pageable pageable);


    @Query("SELECT b FROM BasicBeanInformation b WHERE " +
            "(:typeId IS NULL OR b.typeId = :typeId) AND " +
            "(:shapeId IS NULL OR b.shapeId = :shapeId) AND " +
            "(:colourId IS NULL OR b.colourId = :colourId) AND " +
            "(:originId IS NULL OR b.originId = :originId)")
    Page<BasicBeanInformation> findByTypeShapeColourOrigin(@Param("typeId") Integer typeId,
                                                           @Param("shapeId") Integer shapeId,
                                                           @Param("colourId") Integer colourId,
                                                           @Param("originId") Integer originId,
                                                           Pageable pageable);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"BeanName\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateBeanName(String newBeanName, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"ScientificName\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateScientificName(String newScientificName, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"BeanContent\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateBeanContent(String newBeanContent, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"OriginId\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateOriginId(Integer newOriginId, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"TypeId\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateTypeId(Integer newTypeId, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"ShapeId\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateShapeId(Integer newShapeId, String currentBeanName);

    @Query(nativeQuery = true, value="update \"BasicBeanInformation\" set \"ColourId\" = ?1 where \"BeanName\"=?2")
    @Modifying
    @Transactional
    void updateColourId(Integer newColourId, String currentBeanName);

}
