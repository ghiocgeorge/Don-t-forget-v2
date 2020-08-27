package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import ro.vladutit.Don.t.forget.v2.model.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findById(Long id);
    Optional<Category> findByNameIgnoreCase(String name);

}
