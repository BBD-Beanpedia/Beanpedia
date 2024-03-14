package net.ryan.Repository;

import net.ryan.Entities.BeanColour;
import net.ryan.Entities.BeanShape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeanColourRepository extends JpaRepository<BeanColour, Integer> {

    @Query(nativeQuery = true, value="select * from \"BeanColour\" where \"ColourId\"=?1")
    BeanColour getColourByID(Integer colourID);

    @Query(nativeQuery = true, value = "select * from \"BeanColour\"")
    List<BeanColour> getAllBeans();

}
