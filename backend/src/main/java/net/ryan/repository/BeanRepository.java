package net.ryan.repository;

import net.ryan.model.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bean", path = "bean")
public interface BeanRepository extends CrudRepository<Bean, Integer> {
    List<Bean> findByName(@Param("name") String name);
}
