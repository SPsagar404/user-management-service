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
```

### Postman Collection

A Postman collection is provided to test the API endpoints. Import the following JSON file into Postman to get started.

Postman Collection JSON

Save the following JSON into a file named  resources/User-Managament-System-UseCase.postman_collection.json and import it into Postman:

```api testing using postman
{
	"info": {
		"_postman_id": "e7daa5a4-2f62-40bc-ac95-4357c45e03f1",
		"name": "User Managament System UseCase",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "21368735"
	},
	"item": [
		{
			"name": "Create New User - ADMIN",
			"request": {
				"auth": {
					"type": "basic",
					"basic": [
						{
							"key": "password",
							"value": "admin",
							"type": "string"
						},
						{
							"key": "username",
							"value": "admin",
							"type": "string"
						}
					]
				},
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"username\" :\"rakesh\",\r\n    \"password\":\"rakesh\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/admin/users",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"admin",
						"users"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete User By User Id - ADMIN",
			"request": {
				"auth": {
					"type": "basic",
					"basic": [
						{
							"key": "password",
							"value": "admin",
							"type": "string"
						},
						{
							"key": "username",
							"value": "admin",
							"type": "string"
						}
					]
				},
				"method": "DELETE",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/admin/users/5",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"admin",
						"users",
						"5"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get All User List - ADMIN / USER",
			"request": {
				"auth": {
					"type": "basic",
					"basic": [
						{
							"key": "password",
							"value": "admin",
							"type": "string"
						},
						{
							"key": "username",
							"value": "admin",
							"type": "string"
						}
					]
				},
				"method": "GET",
				"header": [],
				"url": {
					"raw": "http://localhost:8080/api/user/users",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"user",
						"users"
					]
				}
			},
			"response": []
		}
	]
}
```
