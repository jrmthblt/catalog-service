package org.jrmthblt.sharedlibrary.catalogservice;

import static org.assertj.core.api.Assertions.assertThat;

import org.jrmthblt.sharedlibrary.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
 
@JsonTest
class BookJsonTests {
 
  @Autowired
  private JacksonTester<Book> json;
 
  @Test
  void testSerialize() throws Exception {
    var book = new Book("1234567890", "Title", "Author");
    var jsonContent = json.write(book);
    assertThat(jsonContent).extractingJsonPathStringValue("@.isbn")
      .isEqualTo(book.isbn());
    assertThat(jsonContent).extractingJsonPathStringValue("@.title")
      .isEqualTo(book.title());
    assertThat(jsonContent).extractingJsonPathStringValue("@.author")
      .isEqualTo(book.author());
  }
 
  @Test
  void testDeserialize() throws Exception {
    var content = """
      {
        "isbn": "1234567890",
        "title": "Title",
        "author": "Author"
      }
      """;
    assertThat(json.parse(content))
      .usingRecursiveComparison()
      .isEqualTo(new Book("1234567890", "Title", "Author"));
  }
}