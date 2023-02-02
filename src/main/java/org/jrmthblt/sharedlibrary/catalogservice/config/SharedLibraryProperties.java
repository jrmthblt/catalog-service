package org.jrmthblt.sharedlibrary.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Marks the class as a source for configuration properties starting with the prefix “sharedlibrary”
@ConfigurationProperties(prefix = "sharedlibrary")
public class SharedLibraryProperties {
	/**
	 * A message to welcome users.
	 */
	// Field for the custom sharedlibrary.greeting (prefix + field name) property, parsed as String
	private String greeting;

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}