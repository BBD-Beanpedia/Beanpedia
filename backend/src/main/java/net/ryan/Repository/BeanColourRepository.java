package net.ryan.Repository;

import net.ryan.Entities.BeanColour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BeanColourRepository extends JpaRepository<BeanColour, Integer> {

    @Query(nativeQuery = true, value="select * from \"BeanColour\" where \"ColourId\"=?1")
    BeanColour getColourByID(Integer colourID);

}
