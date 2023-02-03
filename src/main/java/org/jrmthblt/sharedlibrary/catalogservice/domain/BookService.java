package org.jrmthblt.sharedlibrary.catalogservice.domain;

import org.jrmthblt.sharedlibrary.catalogservice.BookRepository;
import org.springframework.stereotype.Service;

// Stereotype annotation that marks a class to be a service managed by Spring
@Service
public class BookService {
	
	// BookRepository is provided through constructor autowiring.
	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		super();
		this.bookRepository = bookRepository;
	}

	public Iterable<Book> viewBookList() {
		return bookRepository.findAll();
	}

	// When trying to view a book that doesn’t exist, a dedicated exception is thrown.
	public Book viewBookDetails(String isbn) {
		return bookRepository.findByIsbn(isbn)
				.orElseThrow(() -> new BookNotFoundException(isbn));
	}

	// When adding the same book to the catalog multiple times, a dedicated exception is thrown.
	public Book addBookToCatalog(Book book) {
		if (bookRepository.existsByIsbn(book.isbn())) {
			throw new BookAlreadyExistsException(book.isbn());
		}
		return bookRepository.save(book);
	}

	public void removeBookFromCatalog(String isbn) {
		bookRepository.deleteByIsbn(isbn);
	}

	// When editing the book, all the Book fields can be updated except the ISBN code, because it’s the entity identifier.
	// When changing the details for a book not in the catalog yet, create a new book.
	public Book editBookDetails(String isbn, Book book) {
		return bookRepository.findByIsbn(isbn)
				.map(existingBook -> {
					var bookToUpdate = new Book(
							existingBook.id(),
							existingBook.version(),
							existingBook.createdBy(),
							existingBook.lastModifiedBy(),
							existingBook.createdDate(),
							existingBook.lastModifiedDate(),
							existingBook.isbn(),
							book.title(),
							book.author());
					return bookRepository.save(bookToUpdate);
				})
				.orElseGet(() -> addBookToCatalog(book));
	}
}
