Book Management and Web Service

This project consists of two services:

Management Service: Handles the core backend logic for managing books.
Web Service: Provides a user interface and interacts with the Management Service API.

Prerequisites
To run the services via the compiled JAR files, ensure the following requirements are met:

System Requirements
Java 17: Required to run the JAR files.
Maven: (Optional) Needed only if you want to build the project yourself from source.

Installing Java 17
Windows:
  Download the JDK installer from Oracle or AdoptOpenJDK.
  Install and set the JAVA_HOME environment variable.

Linux/Mac:
  Use your package manager to install Java 17 
Verify installation:

  java -version

*How to Run the Project*
Step 1: Run the Management Service
  Navigate to the directory containing the Management Service JAR file and open command line there then type in the below command to start the service.
  
  java -jar Book-Management-Service.jar
  
Verify the service is running:
Open a browser and visit: http://localhost:8080/books/getAllBooks.
You should see two dummy books preloaded in the system:


Step 2: Run the Web Service
  Navigate to the directory containing the Web Service JAR file and open command line there then type in the below command to start the service.
  
  java -jar Book-Web-Service.jar

Access the web interface:
Open a browser and visit: http://localhost:8081/books.


*How to Use the Services*
1. Web Service
This service provides a user-friendly interface to interact with the Book Management System.

Available Actions
i) View All Books: Visit http://localhost:8081/books to see a list of all books
ii) Add a New Book: Click on the "Add New Book" button or visit http://localhost:8081/books/add.
iii) Edit a Book: Use the "Edit" button next to a book in the list.
iv) Delete a Book: Use the "Delete" button next to a book in the list.


2. Management Service API
The Web Service communicates with the Management Service API. Advanced users can directly interact with the API:

i) List All Books: GET http://localhost:8080/books/getAllBooks
ii) Add a Book: POST http://localhost:8080/books/addBook
iii) Edit a Book: PUT http://localhost:8080/books/updateBook/{id}
iv) Delete a Book: DELETE http://localhost:8080/books/deleteBook/{id}
Note: All Web Service operations depend on the Management Service being active.

Key Notes
Inter-Service Communication: The Web Service interacts with the Management Service via RESTful APIs. Ensure the Management Service is started before launching the Web Service.

URL Structure:
  Management Service: http://localhost:8080
  Web Service: http://localhost:8081

Testing:
Both Unit Tests and Integration Tests have been carried out for the Book Management Service to ensure functionality and reliability.

Key Actions for Seamless Use
Ensure Proper Order: Start the Management Service before the Web Service to avoid errors.
Browser Interactions: Use http://localhost:8081/books as the entry point for all interactions.
API Access:
If debugging is needed, use tools like Postman to interact directly with the Management Service API.
