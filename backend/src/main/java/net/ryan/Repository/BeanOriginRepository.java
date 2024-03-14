package net.ryan.Repository;

import net.ryan.entities.BeanOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanOriginRepository extends JpaRepository<BeanOrigin, Integer> {

    @Query(nativeQuery = true, value = "select * from \"BeanOrigin\" where \"OriginId\"=?1")
    BeanOrigin getOriginByID(Integer originID);

    @Query(nativeQuery = true, value = "select * from \"BeanOrigin\" where \"OriginId\"=?1")
    List<BeanOrigin> getBeanOriginByName(String Origin);

    @Query(nativeQuery = true, value = "select * from \"BeanOrigin\"")
    List<BeanOrigin> getAllBeans();

}
