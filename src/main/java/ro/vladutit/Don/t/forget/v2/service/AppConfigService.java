package ro.vladutit.Don.t.forget.v2.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.vladutit.Don.t.forget.v2.model.AppConfig;
import ro.vladutit.Don.t.forget.v2.repository.AppConfigRepository;

@Service
public class AppConfigService {

    @Autowired
    AppConfigRepository appConfigRepository;

    public AppConfig getAppConfigByKey(String key) {
        Optional<AppConfig> optional = appConfigRepository.findAppConfigByKey(key);
        AppConfig appConfig = null;
        if(optional.isPresent()) {
            appConfig = optional.get();
        } else {
            throw new RuntimeException("AppConfig not found for key: " + key);
        }
        return appConfig;
    }
}