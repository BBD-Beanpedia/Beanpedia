package net.ryan.Repository;

import net.ryan.Entities.BasicBeanInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasicBeanInformationRepository extends JpaRepository<BasicBeanInformation, Integer> {

    @Query(nativeQuery = true, value="select * from \"BasicBeanInformation\" where \"BeanName\" = ?1")
    List<BasicBeanInformation> searchBeanByName(String name);

}
