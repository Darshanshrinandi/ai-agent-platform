AI Agent Platform 🚀

A minimal Chatbot Platform built using Spring Boot, JWT Authentication, PostgreSQL, and OpenRouter (LLM).
This project allows users to create AI-powered projects (agents), attach prompts, and chat with them securely.

✨ Features

✅ User Registration & Login (JWT-based authentication)

✅ Secure APIs using Spring Security

✅ Create Projects (AI Agents) per user

✅ Add and manage prompts per project

✅ Chat with AI using OpenRouter Completion API

✅ Store chat history in database

✅ Production-ready deployment on Railway

✅ Scalable & extensible backend architecture

🛠️ Tech Stack

Backend: Java 21, Spring Boot 3

Security: Spring Security + JWT

Database: PostgreSQL (Railway)

ORM: Spring Data JPA + Hibernate

AI Integration: OpenRouter API

Build Tool: Maven

Deployment: Railway

📐 Architecture Overview
Controller Layer
↓
Service Layer (Business Logic)
↓
Repository Layer (JPA)
↓
PostgreSQL Database


Controllers handle HTTP & DTO validation

Services contain core business logic

JWT filter secures protected routes

AI service communicates with OpenRouter

Chat history stored for future retrieval

🔐 Authentication Flow

User registers with email & password

Password stored using BCrypt hashing

User logs in → JWT token generated

JWT token required for all protected APIs

Spring Security validates token on every request

📡 API Endpoints (Sample)
Auth
POST /api/auth/register
POST /api/auth/login

Projects
POST /api/projects
GET  /api/projects

Prompts
POST /api/projects/{projectId}/prompts
GET  /api/projects/{projectId}/prompts

Chat with AI
POST /api/chat/{projectId}


Request Body

{
"message": "Explain JWT in simple terms"
}

🤖 AI Integration

Uses OpenRouter Completion API

Model example: mistralai/mistral-7b-instruct

Prompts attached to project are used as context

Responses are stored along with user queries

⚙️ Environment Variables

Set the following variables (Railway / local):

DATABASE_URL
DATABASE_USERNAME
DATABASE_PASSWORD
OPENROUTER_API_KEY
JWT_SECRET
JWT_EXPIRATION

▶️ Run Locally
git clone <repo-url>
cd ai-agent-platform
mvn spring-boot:run


App runs on:

http://localhost:8080

🌍 Live Demo

Backend URL

https://ai-agent-platform-production-8f25.up.railway.app

🎥 Demo

API demo via Postman

Shows user registration, login, project creation, prompt addition, and AI chat

🚀 Future Enhancements

Frontend UI (React)

File upload support (OpenAI Files API)

Chat analytics

Role-based access control

Rate limiting

👤 Author

Darshan S V
Software Engineer Intern Applicant
Backend | Java | Spring Boot