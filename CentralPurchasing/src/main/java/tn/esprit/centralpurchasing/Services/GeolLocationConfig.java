package tn.esprit.centralpurchasing.Services;

import com.maxmind.db.Reader;
import com.maxmind.geoip2.DatabaseReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class GeolLocationConfig {
    private static DatabaseReader reader = null;
    @Autowired
    private final ResourceLoader resourceLoader;

    public GeolLocationConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public DatabaseReader databaseReader() {
        try {
            log.info("GeoLocation Config: Trying to load GeoLite2-City database...");

            Resource resource = resourceLoader.getResource("classpath:maximind/GeoLite2-City.mmdb");
            InputStream dbAsStream = resource.getInputStream();
            log.info("GeoLocationConfig: Database was loaded successfully.");
            return reader = new DatabaseReader.Builder(dbAsStream)
                    .fileMode(Reader.FileMode.MEMORY)
                    .build();
        } catch (IOException | NullPointerException e) {
            log.error("Database reader could not be initialized.", e);

            return null;
        }
    }


}