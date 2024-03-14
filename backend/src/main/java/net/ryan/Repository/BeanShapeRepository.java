package net.ryan.Repository;

import net.ryan.Entities.BeanShape;
import net.ryan.Entities.BeanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanShapeRepository  extends JpaRepository<BeanShape, Integer> {

    @Query(nativeQuery = true, value = "select * from \"BeanShape\"")
    List<BeanShape> getAllBeans();

}
