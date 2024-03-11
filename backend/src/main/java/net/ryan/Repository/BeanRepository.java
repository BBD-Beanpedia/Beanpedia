package net.ryan.Repository;

import net.ryan.Entities.BeanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanRepository extends JpaRepository<BeanEntity, Integer> {

    @Query(nativeQuery = true, value="select * from beans;")
    List<BeanEntity> getBeans();

}
