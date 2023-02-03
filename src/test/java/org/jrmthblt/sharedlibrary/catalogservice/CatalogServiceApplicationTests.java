package org.jrmthblt.sharedlibrary.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.jrmthblt.sharedlibrary.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

// Integration Tests
// Loads a full Spring web application context and a Servlet container listening on a random port
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
		)
class CatalogServiceApplicationTests {


	@Autowired
	// Utility to perform REST calls for testing
	private WebTestClient webTestClient;

	@Test
	void whenPostRequestThenBookCreated() {
		var expectedBook = Book.of("1231231231", "Title", "Author");

		webTestClient
		.post()
		.uri("/books")
		.bodyValue(expectedBook)
		.exchange()
		.expectStatus().isCreated()
		.expectBody(Book.class).value(actualBook -> {
			assertThat(actualBook).isNotNull();
			assertThat(actualBook.isbn())
			.isEqualTo(expectedBook.isbn());
		});
	}

	@Test
	void contextLoads() {
	}

}
