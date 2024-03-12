package net.ryan.Repository;

import net.ryan.Entities.BeanOrigin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanOriginRepository extends JpaRepository<BeanOrigin, Integer> {

    @Query(nativeQuery = true, value = "select * from \"BeanOrigin\" where \"OriginId\"=?1")
    BeanOrigin getOriginByID(Integer originID);

}
