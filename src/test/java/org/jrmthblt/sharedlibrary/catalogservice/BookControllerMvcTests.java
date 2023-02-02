package org.jrmthblt.sharedlibrary.catalogservice;

import org.jrmthblt.sharedlibrary.catalogservice.domain.BookNotFoundException;
import org.jrmthblt.sharedlibrary.catalogservice.domain.BookService;
import org.jrmthblt.sharedlibrary.catalogservice.web.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

// Imports added by hand : why was it necessary ?
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Identifies a test class that focuses on Spring MVC components, explicitly targeting BookController
@WebMvcTest(BookController.class)
class BookControllerMvcTests {

	// Utility class to test the web layer in a mock environment
	@Autowired
	private MockMvc mockMvc;

	// Adds a mock of BookService to the Spring application context
	@MockBean
	private BookService bookService;

	@Test
	void whenGetBookNotExistingThenShouldReturn404() throws Exception {
		// Defines the expected behavior for the BookService mock bean
		String isbn = "73737313940";
		given(bookService.viewBookDetails(isbn))
		.willThrow(BookNotFoundException.class);
		
		// MockMvc is used to perform an HTTP GET request and verify the result
		mockMvc
		.perform(get("/books/" + isbn))
		.andExpect(status().isNotFound());
	}
}
