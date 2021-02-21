package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);
    Optional<Category> findByNameIgnoreCaseAndUserId(String name, Long user_id);
    List<Category> getByUserId(Long userId);
    Optional<Category> findByNameIgnoreCaseAndId(String name, Long id);
}