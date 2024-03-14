package net.ryan.Repository;

import net.ryan.entities.BeanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanTypeRepository extends JpaRepository<BeanType, Integer> {

    @Query(nativeQuery = true, value="select * from \"BeanType\" where \"TypeId\" = ?1")
    BeanType getTypeByID(Integer typeID);

    @Query(nativeQuery = true, value = "select * from \"BeanType\"")
    List<BeanType> getAllBeans();

}
