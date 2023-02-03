package org.jrmthblt.sharedlibrary.catalogservice.domain;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


// In Catalog Service, you already have a Book record representing a domain entity for the application. Depending on the business domain and its complexity, you might want to distinguish the domain entity from the persistent entity, making the domain layer utterly independent of the persistence layer. If you’d like to explore how to model that scenario, I recommend referring to domain-driven design and hexagonal architecture principles.
// Spring Data JPA works with mutating objects, so you can’t use Java records
public record Book (

		@Id
		Long id,

		@Version
		int version,

		@CreatedBy
		String createdBy,
		
		@LastModifiedBy
		String lastModifiedBy,		
		
		@CreatedDate
		Instant createdDate, 

		@LastModifiedDate
		Instant lastModifiedDate,

		@NotBlank(message = "The book ISBN must be defined.") 
		@Pattern(
				regexp = "^([0-9]{10}|[0-9]{13})$", 
				message = "The ISBN format must be valid." 
				)
		String isbn,

		@NotBlank(message = "The book title must be defined.") 
		String title,

		@NotBlank(message = "The book author must be defined.")
		String author
		) {

	public static Book of(String isbn, String title, String author) { 
		// An entity is considered new when the ID is null and the version is 0
		return new Book(null, 0, null, null, null, null, isbn, title, author); 
	} 

}
