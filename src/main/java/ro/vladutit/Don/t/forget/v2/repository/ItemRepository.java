package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ro.vladutit.Don.t.forget.v2.model.Item;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
