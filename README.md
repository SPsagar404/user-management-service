# User Access Management System

This is a Spring Boot application for user access management. It includes role-based access control and exception handling. Users with the `ADMIN` role can create and delete users, while users with the `USER` role can only view the list of users.

## Technologies Used

- Spring Boot
- Spring Security
- Hibernate
- MySQL
- Maven
- Postman (for API testing)

## Prerequisites

- Java 8 or higher
- Maven
- MySQL

## Setup

### Database Configuration

1. Create a MySQL database named `userdb`.

    ```sql
    CREATE DATABASE userdb;
    ```

2. Update the database configuration in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/userdb
    spring.datasource.username=root
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

### Initial Data

The initial admin user is created using `src/main/resources/data.sql`. This script is executed when the application starts and inserts an admin user into the database.

```sql
INSERT INTO user (username, password, role) VALUES ('admin', '{bcrypt}$2a$10$DOWSD4jGNs9ppG8D8EyKCeZebcTVty6S8aNlf.CyX/je4fPbPMBsy', 'ROLE_ADMIN');
