---

# 🛒 Product & User Management API

## 📌 Overview

This project is a **Spring Boot application** for managing **Users** and **Products** with a `Many-To-Many` relationship.

* Each user can have multiple products.
* Each product can belong to multiple users.
* Provides APIs for **user registration, login, product management, and assigning products to users**.
* Secured using **Spring Security + JWT**.
* Database managed with **Liquibase + PostgreSQL**.

---

## 🗄️ Database Structure

### Users Table

| Column   | Type      | Description         |
| -------- | --------- | ------------------- |
| id       | Long (PK) | User ID             |
| name     | String    | User’s name         |
| email    | String    | Unique email        |
| password | String    | Encrypted password  |
| role     | String    | Role (USER / ADMIN) |

### Products Table

| Column      | Type      | Description         |
| ----------- | --------- | ------------------- |
| id          | Long (PK) | Product ID          |
| name        | String    | Product name        |
| description | String    | Product description |
| price       | Double    | Product price       |

### Users ↔ Products

* `ManyToMany` relationship stored in a join table:

  ```sql
  product_users_buyers (user_id, product_id)
  ```

---

## 🚀 API Endpoints

### 👤 User

* **Register User**
  `POST /api/user/register`

  ```json
  {
    "name": "Khaled",
    "email": "khaled@example.com",
    "password": "12345",
    "role": "USER"
  }
  ```

* **Login**
  `POST /api/v1/login`

  ```json
  {
    "email": "khaled@example.com",
    "password": "12345"
  }
  ```

  👉 Returns a **JWT token**.

---

### 🛒 Product

* **Add Product (Admin)**
  `POST /api/v1/product/add`

  ```json
  {
    "name": "Nokia",
    "description": "Smartphone",
    "price": 1500.0
  }
  ```

* **Get Product by Name**
  `GET /api/v1/product?name=Nokia`

---

### 🔗 Assign Product to User

* **Add a Product to Logged-in User**
  `POST /api/v1/user/addProduct/{productName}`

  Requires **JWT Token** in headers:

  ```
  Authorization: Bearer <your_token>
  ```

  ✅ If product exists and user hasn’t added it before → it will be saved to their list.

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot 3 (Web, Security, Data JPA)
* PostgreSQL
* JWT
* Lombok

---
