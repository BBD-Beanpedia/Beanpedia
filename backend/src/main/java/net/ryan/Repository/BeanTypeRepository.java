package net.ryan.Repository;

import net.ryan.Entities.BeanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanTypeRepository extends JpaRepository<BeanType, Integer> {

    @Query(nativeQuery = true, value="select * from \"BeanType\" where \"TypeId\" = ?1")
    BeanType getTypeByID(Integer typeID);

}
