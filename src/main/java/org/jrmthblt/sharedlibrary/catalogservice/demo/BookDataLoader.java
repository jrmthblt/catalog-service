package org.jrmthblt.sharedlibrary.catalogservice.demo;

import org.jrmthblt.sharedlibrary.catalogservice.BookRepository;
import org.jrmthblt.sharedlibrary.catalogservice.domain.Book;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
// Assigns the class to the testdata profile. It will be registered only when the testdata profile is active.
@Profile("testdata")
public class BookDataLoader {
	private final BookRepository bookRepository;
	public BookDataLoader(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	// The test data generation is triggered when an ApplicationReadyEvent is sent—that is when the application startup phase is completed.
	@EventListener(ApplicationReadyEvent.class)
	public void loadBookTestData() {
		var book1 = new Book("1234567891", "Northern Lights",
				"Lyra Silverstar");
		var book2 = new Book("1234567892", "Polar Journey",
				"Iorek Polarson");
		bookRepository.save(book1);
		bookRepository.save(book2);
	}
}