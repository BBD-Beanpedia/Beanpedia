package net.ryan.Repository;

import net.ryan.Entities.BeanShape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanShapeRepository  extends JpaRepository<BeanShape, Integer> {

    @Query(nativeQuery = true, value="select * from \"BeanShape\" where \"ShapeId\" =?1")
    BeanShape getShapeByID(Integer shapeID);

}
