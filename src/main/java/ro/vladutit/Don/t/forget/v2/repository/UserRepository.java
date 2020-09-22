package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import ro.vladutit.Don.t.forget.v2.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

}
