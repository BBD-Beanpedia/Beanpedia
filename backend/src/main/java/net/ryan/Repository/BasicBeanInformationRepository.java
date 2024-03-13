package net.ryan.Repository;

import net.ryan.Entities.BasicBeanInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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




}
