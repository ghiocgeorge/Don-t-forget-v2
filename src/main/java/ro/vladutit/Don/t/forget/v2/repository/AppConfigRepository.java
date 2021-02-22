package ro.vladutit.Don.t.forget.v2.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.vladutit.Don.t.forget.v2.model.AppConfig;

@Repository
public interface AppConfigRepository extends JpaRepository<AppConfig, String> {

    Optional<AppConfig> findAppConfigByKey(String key);
}
