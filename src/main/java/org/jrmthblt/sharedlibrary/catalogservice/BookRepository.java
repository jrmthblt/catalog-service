package org.jrmthblt.sharedlibrary.catalogservice;

import java.util.Optional;

import org.jrmthblt.sharedlibrary.catalogservice.domain.Book;

public interface BookRepository {
	Iterable<Book> findAll();
	Optional<Book> findByIsbn(String isbn);
	boolean existsByIsbn(String isbn);
	Book save(Book book);
	void deleteByIsbn(String isbn);
}