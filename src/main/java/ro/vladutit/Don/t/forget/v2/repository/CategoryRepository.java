package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findById(Long id);
    Optional<Category> findByNameIgnoreCase(String name);
    List<Category> getByUserId(Long userId);
}
