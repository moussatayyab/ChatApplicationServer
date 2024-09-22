This chat application is designed to allow users to communicate with one another via chat boxes. It supports multiple participants in chat rooms, allows message exchanges, and stores user and chat data in a database. The application is built using Spring Boot, providing a robust and scalable backend structure, and utilizes a MySQL database for data persistence.

Technologies Used:
Spring Boot: A Java-based framework for building web applications. It simplifies the development process by providing embedded servers, dependency management, and auto-configuration.
Spring Data JPA: A part of Spring Data that handles the data persistence layer. It simplifies database interaction with Java Persistence API (JPA).
MySQL: A relational database management system used to store user information, messages, chat boxes, and other data.
Jakarta Persistence API (JPA): Used for mapping Java objects to database tables and vice versa.
Lombok: A Java library used to minimize boilerplate code, like getters, setters, and constructors.
Maven: A build automation tool used for managing dependencies, building, and packaging the application.
Jakarta Servlet API: Handles web requests and file uploads, such as managing multipart file data.
JWT (JSON Web Token): Used for managing authentication and authorization, allowing secure and stateless communication between client and server.
Key Dependencies:
spring-boot-starter-data-jpa:
Provides integration with Spring Data JPA to easily manage data persistence and database transactions.
spring-boot-starter-web:
Includes all the necessary dependencies for building RESTful web services and web applications with Spring Boot.
mysql-connector-j:
Provides MySQL database connectivity, enabling the application to communicate with a MySQL database.
jakarta.persistence-api:
Provides the Java Persistence API, necessary for JPA-based interactions.
jakarta.servlet-api:
Handles servlets and file uploads (e.g., MultipartFile).
lombok:
Reduces boilerplate code by generating getter, setter, toString(), and other common methods automatically.
jjwt-jackson:
Provides JSON Web Token (JWT) capabilities for user authentication and authorization.
spring-security-test:
Provides tools for testing Spring Security configurations and flows.
Application Features:
User Management: Users can register, log in, and are assigned roles (such as regular users).
Chat Rooms (Chat Boxes): Users can create and join chat rooms with other participants.
Messaging: Users can send and receive messages in chat rooms.
Authorization and Authentication: JWT is used to securely authenticate users.
Database Storage: All user and message data is stored in a MySQL database, using Spring Data JPA for ORM and persistence.
