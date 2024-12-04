package com.felix.Book.Management.Service;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class BookControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(){

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;


    }

    @Test

    void testGetAllBooks_WithExistingBooks() {
        // Arrange: Add books programmatically
        String book1 = """
        {
            "name": "Book 1",
            "isbn": "1234567890",
            "publishDate": "2024-11-30",
            "price": 100.00,
            "bookType": "HARDCOVER"
        }
    """;

        String book2 = """
        {
            "name": "Book 2",
            "isbn": "0987654321",
            "publishDate": "2023-05-15",
            "price": 200.00,
            "bookType": "EBOOK"
        }
    """;

        given()
                .contentType(ContentType.JSON)
                .body(book1)
                .post("/books/addBook")
                .then()
                .statusCode(201); // First book added

        given()
                .contentType(ContentType.JSON)
                .body(book2)
                .post("/books/addBook")
                .then()
                .statusCode(201); // Second book added

        // Act: Get all books
        given()
                .when()
                .get("/books/getAllBooks")
                .then()
                .statusCode(200) // Assert: Successful response
                .body("$", hasSize(2)) // Assert: Two books are returned
                .body("[0].name", equalTo("Book 1")) // Assert: Check first book details
                .body("[1].name", equalTo("Book 2")); // Assert: Check second book details
    }

    @Test
    void testAddBook(){
        String newBook = """
            {
                "name": "PayU Book",
                "isbn": "123456789",
                "publishDate": "2024-11-30",
                "price": 200.00,
                "bookType": "EBOOK"
            }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(newBook)
                .when()
                .post("/books/addBook")
                .then()
                .statusCode(201)
                .body("name", equalTo("PayU Book"))
                .body("isbn", equalTo("123456789"));
    }
    @Test
    void testGetBookById() {
        // First, add a book
        String newBook = """
            {
                "name": "Find Me",
                "isbn": "987654321",
                "publishDate": "2024-11-30",
                "price": 300.00,
                "bookType": "HARDCOVER"
            }
        """;

        Integer bookId = given()
                .contentType(ContentType.JSON)
                .body(newBook)
                .when()
                .post("/books/addBook")
                .then()
                .statusCode(201)
                .log().all()
                .extract()
                .path("id");

        // Retrieve the added book
        given()
                .when()
                .get("/books/getABook/" + bookId)
                .then()
                .statusCode(200)
                .log().all()
                .body("name", equalTo("Find Me"))
                .body("isbn", equalTo("987654321"));
    }

    @Test
    void testDeleteBook() {
        // First, add a book
        String newBook = """
            {
                "name": "Delete Me",
                "isbn": "111222333",
                "publishDate": "2024-11-30",
                "price": 150.00,
                "bookType": "SOFTCOVER"
            }
        """;

        Integer bookId = given()
                .contentType(ContentType.JSON)
                .body(newBook)
                .when()
                .post("/books/addBook")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Delete the book
        given()
                .when()
                .delete("/books/deleteBook/" + bookId)
                .then()
                .statusCode(204);

        // Verify the book no longer exists
        given()
                .when()
                .get("/books/getABook/" + bookId)
                .then()
                .statusCode(404);
    }

    @AfterEach
    void cleanDatabase() {
        given()
                .when()
                .delete("/books/clearDatabase");
    }
}
