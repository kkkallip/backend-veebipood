package net.kkkallip.veebipood.repository;

import net.kkkallip.veebipood.entity.Category;
import net.kkkallip.veebipood.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    //SELECT * FROM person WHERE email =
    Person findByEmail(String email);
}
