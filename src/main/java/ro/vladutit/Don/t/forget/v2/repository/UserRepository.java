package ro.vladutit.Don.t.forget.v2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
