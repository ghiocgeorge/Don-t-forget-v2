package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.Category;
import ro.vladutit.Don.t.forget.v2.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> getByCategoryId (Long categoryId);
    List<Item> getByUserId(Long userId);
}
