package org.jrmthblt.sharedlibrary.catalogservice.web;

import org.jrmthblt.sharedlibrary.catalogservice.config.SharedLibraryProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	// Bean to access the custom properties injected via constructor autowiring
	private final SharedLibraryProperties sharedLibraryProperties;

	public HomeController(SharedLibraryProperties sharedLibraryProperties) {
		super();
		this.sharedLibraryProperties = sharedLibraryProperties;
	}



	@GetMapping("/")
	public String getGreeting() {
		return this.sharedLibraryProperties.getGreeting();
	}
}
