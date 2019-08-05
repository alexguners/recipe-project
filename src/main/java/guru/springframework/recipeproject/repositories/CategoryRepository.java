package guru.springframework.recipeproject.repositories;

import guru.springframework.recipeproject.domain.Category;
import org.hibernate.Criteria;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category,Long> {

    Optional<Category> findByDescription(String Description);

}
