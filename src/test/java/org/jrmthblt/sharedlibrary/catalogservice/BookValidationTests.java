package org.jrmthblt.sharedlibrary.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.jrmthblt.sharedlibrary.catalogservice.domain.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

// Unit Tests
class BookValidationTests {
	private static Validator validator;

	// Identifies a block of code executed before all tests in the class
	@BeforeAll
	static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Identifies a test case
	// Creates a book with a valid ISBN
	@Test
	void whenAllFieldsCorrectThenValidationSucceeds() {
		var book =
				Book.of("1234567890", "Title", "Author");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		// Asserts that there is no validation error
		assertThat(violations).isEmpty();
	}

	// Creates a book with a non-valid ISBN code
	@Test
	void whenIsbnDefinedButIncorrectThenValidationFails() {
		var book =
				Book.of("a234567890", "Title", "Author");
		Set<ConstraintViolation<Book>> violations = validator.validate(book);
		// Asserts that the violated validation constraint is about the incorrect ISBN
		assertThat(violations).hasSize(1);
		assertThat(violations.iterator().next().getMessage())
		.isEqualTo("The ISBN format must be valid.");
	}
}
