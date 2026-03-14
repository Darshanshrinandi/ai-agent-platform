# 🤖 AI Agent Platform (Backend)

A backend-driven **AI Chatbot Platform** that allows users to create AI agents (projects), attach prompts, and interact with them securely using **JWT authentication** and **Grok LLM integration**.

This project was built as part of a **Software Engineer Intern assignment**, focusing on **security, scalability, and clean backend architecture**.

---

# 🚀 Live Demo (Backend)

Base URL:

```
https://ai-agent-platform-production-8f25.up.railway.app
```

⚠️ This is an **API-first backend**. A frontend (React, etc.) can be integrated later.

---

# 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Security + JWT
* Hibernate / JPA
* PostgreSQL (Railway)
* Grok API (LLM Integration)
* Maven
* Railway (Deployment)

---

# ✨ Features

## 🔐 Authentication & Security

* User registration & login
* JWT-based stateless authentication
* Password encryption using BCrypt
* Secure authorization using Spring SecurityContext

---

## 📁 Project / Agent Management

* Create AI projects (agents)
* Each project is strictly owned by the logged-in user
* Prevents cross-user access (no privilege escalation)

---

## 🧠 Prompt Management

* Attach prompts to projects
* Prompts are used as context during AI conversations

---

## 💬 AI Chat

* Chat with AI agents using **Grok API**
* Context-aware conversations using stored prompts
* AI responses fetched securely via API key

---

## 🗃️ Chat History

* Stores user messages and AI responses
* Maintains conversation history per project

---

# 🧱 Architecture Overview

```
Client
|
|  JWT Token
v
Spring Security Filter (JWT)
|
Controller Layer
|
Service Layer (Business Logic + Ownership Validation)
|
Repository Layer (JPA)
|
PostgreSQL Database
|
Grok API (LLM)
```

---

# 🔑 Key Design Decisions

* User identity is derived **only from JWT**, never from request parameters
* Ownership checks enforced at **service layer**
* DTO-based request handling for **validation and stability**
* Stateless authentication for **scalability**
* Clean layered architecture for maintainability

---

# 📌 API Endpoints

## 🔑 Auth APIs

| Method | Endpoint           | Description           |
| ------ | ------------------ | --------------------- |
| POST   | /api/auth/register | Register a new user   |
| POST   | /api/auth/login    | Login and receive JWT |

---

## 📁 Project APIs (JWT Required)

| Method | Endpoint      | Description       |
| ------ | ------------- | ----------------- |
| POST   | /api/projects | Create a project  |
| GET    | /api/projects | Get user projects |

---

## 📝 Prompt APIs (JWT Required)

| Method | Endpoint                          | Description           |
| ------ | --------------------------------- | --------------------- |
| POST   | /api/projects/{projectId}/prompts | Add prompt to project |
| GET    | /api/projects/{projectId}/prompts | List prompts          |

---

## 💬 Chat API (JWT Required)

| Method | Endpoint              | Description        |
| ------ | --------------------- | ------------------ |
| POST   | /api/chat/{projectId} | Chat with AI agent |

### Chat Request Body

```
{
  "message": "Explain this project"
}
```

---

# 🔐 Authentication Flow

1. User logs in with **email & password**
2. Credentials validated via **AuthenticationManager**
3. JWT token generated after successful authentication
4. Token sent in request header:

```
Authorization: Bearer <JWT_TOKEN>
```

5. User identity resolved from **JWT for all protected operations**

---

# ⚙️ Environment Variables

Set these variables in **Railway or `.env`**

```
DB_URL=jdbc:postgresql://<host>:5432/<db>
DB_USERNAME=<username>
DB_PASSWORD=<password>

JWT_SECRET=<secure-256-bit-secret>
JWT_EXPIRATION=86400000

GROK_API_KEY=<your_grok_api_key>
```

---

# ▶️ Run Locally

Clone the repository:

```
git clone https://github.com/Darshanshrinandi/ai-agent-platform.git
```

Navigate to the project:

```
cd ai-agent-platform
```

Run the application:

```
mvn spring-boot:run
```

App runs at:

```
http://localhost:8080
```

---

# 🧪 Testing Tips

Use **Postman** or any API client.

Always send headers:

```
Content-Type: application/json
Authorization: Bearer <JWT_TOKEN>
```

Ensure request bodies are **JSON**, not `text/plain`.

---

# 🧠 Key Learnings

* Correct JWT authentication using **AuthenticationManager**
* Preventing **Broken Object Level Authorization (BOLA)**
* Importance of **ownership validation**
* Real-world debugging of **Spring Security (401 / 403 issues)**
* Secure integration with **external LLM APIs**
* Designing **scalable backend architectures**

---

# 🔮 Future Enhancements

* React Frontend UI
* Role-based access (Admin/User)
* File upload support
* Conversation analytics
* Rate limiting & caching
* Multi-model LLM support

---

# 👨‍💻 Author

**Darshan S V**
Backend-focused **Java Developer**

Built with ❤️ and persistence.

---

# 📄 License

This project is for **educational and evaluation purposes**.
