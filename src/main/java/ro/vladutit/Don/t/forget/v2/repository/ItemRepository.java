package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> getByCategoryId (Long categoryId);
    List<Item> getByUserId(Long userId);
}
