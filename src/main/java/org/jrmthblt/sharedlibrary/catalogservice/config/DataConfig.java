package org.jrmthblt.sharedlibrary.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
 
// Indicates a class as a source of Spring configuration
@Configuration
@EnableJdbcAuditing
public class DataConfig {}