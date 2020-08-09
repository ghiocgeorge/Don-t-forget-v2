package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import ro.vladutit.Don.t.forget.v2.model.Item;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    public List<Item> getByCategoryName (String categoryName);
}
