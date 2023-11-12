package tr.edu.bilkent.bilsync.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "tr.edu.bilkent.bilsync.repository")
@EntityScan(basePackages = "tr.edu.bilkent.bilsync.entity")
public class DataConfiguration { 
}