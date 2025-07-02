# 💳 Secure Payment Gateway - Full Stack Java Project

A secure and robust full-stack payment gateway system built using **Spring Boot** (Java) for the backend and **React.js** for the frontend. This project supports user registration, login (using Spring Security), and payment transactions with status tracking.

---

##  Tech Stack

### Backend
- Java 17+
- Spring Boot
- Spring Security (form-based authentication)
- RESTful APIs
- MySQL
- Maven

### Frontend
- React.js
- JavaScript
- CSS (custom styling)
- Axios (for API integration)

---

##  Features

- ✅ User Registration and Login (Spring Security)
- ✅ Secure Authentication and Authorization
- ✅ Payment Creation and Status Tracking
- ✅ REST APIs with DTO support
- ✅ Exception Handling and Clean Architecture
- ✅ Modular Code: Controllers, Services, Repositories, Models, DTOs, Utilities

---

## 📁 Project Structure

### Backend (`/project-payment-gateways-backend`)


---

## 🧪 API Endpoints

| Method | Endpoint            | Description               |
|--------|---------------------|---------------------------|
| POST   | `/auth/register`    | Register a new user       |
| POST   | `/auth/login`       | Login user (Spring Sec.)  |
| POST   | `/payment/initiate` | Create a new payment      |
| GET    | `/payment/{txid}`   | Get user's payment history|
| POST   | '/api/auth/me'      | get the current user      |
| GET    |'/api/payments/history'|get transaction details  |

> All secured routes require authentication.

---

## 📦 How to Run

### Backend

```bash
cd project-payment-gateways-backend
./mvnw spring-boot:run
cd project-payment-gateways-UI
npm install
npm start
Contribution
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.
License
This project is open-source and free to use.
Author
Durga Sai Kodali
GitHub: https://github.com/DurgaSaiKodali
