package nl.novi.TechItEasy.repositories;

import nl.novi.TechItEasy.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

}
