🤖 AI Agent Platform (Backend)

A backend-driven AI Chatbot Platform that allows users to create AI agents (projects), attach prompts, and interact with them securely using JWT authentication and OpenRouter LLM integration.

This project was built as part of a Software Engineer Intern assignment, focusing on security, scalability, and clean backend architecture.

🚀 Live Demo (Backend)

Base URL:

https://ai-agent-platform-production-8f25.up.railway.app


⚠️ This is an API-first backend. Frontend is optional and can be integrated later.

🛠️ Tech Stack

Java 21

Spring Boot

Spring Security + JWT

Hibernate / JPA

PostgreSQL (Railway)

OpenRouter API (LLM Integration)

Maven

Railway (Deployment)

✨ Features
🔐 Authentication & Security

User registration & login

JWT-based stateless authentication

Password encryption using BCrypt

Secure authorization using SecurityContext

📁 Project / Agent Management

Create AI projects (agents)

Each project is strictly owned by the logged-in user

Prevents cross-user access (no privilege escalation)

🧠 Prompt Management

Attach prompts to projects

Prompts are used as context during AI conversations

💬 AI Chat

Chat with AI agents using OpenRouter Completion API

Context-aware conversations using stored prompts

AI responses fetched securely via API key

🗃️ Chat History

Stores user messages and AI responses

Maintains conversation history per project

🧱 Architecture Overview
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
OpenRouter API (LLM)

🔑 Key Design Decisions

User identity is derived only from JWT, never from request parameters

Ownership checks enforced at service layer

DTO-based request handling for stability and validation

Stateless authentication for scalability

📌 API Endpoints
🔑 Auth APIs
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login and receive JWT

📁 Project APIs (JWT Required)
Method	Endpoint	Description
POST	/api/projects	Create a project
GET	/api/projects	Get user projects

📝 Prompt APIs (JWT Required)
Method	Endpoint	Description
POST	/api/projects/{projectId}/prompts	Add prompt to project
GET	/api/projects/{projectId}/prompts	List prompts

💬 Chat API (JWT Required)
Method	Endpoint	Description
POST	/api/chat/{projectId}	Chat with AI agent

Chat Request Body

{
"message": "Explain this project"
}

🔐 Authentication Flow

User logs in with email & password

Credentials validated via AuthenticationManager

JWT token generated only after successful authentication

Token sent in request header:

Authorization: Bearer <JWT_TOKEN>


User identity resolved from JWT for all protected operations

⚙️ Environment Variables

Create the following variables in Railway or .env:

DB_URL=jdbc:postgresql://<host>:5432/<db>
DB_USERNAME=<username>
DB_PASSWORD=<password>

JWT_SECRET=<secure-256-bit-secret>
JWT_EXPIRATION=86400000

OPENROUTER_API_KEY=<your_openrouter_api_key>

▶️ Run Locally
git clone https://github.com/Darshanshrinandi/ai-agent-platform.git
cd ai-agent-platform
mvn spring-boot:run


App runs at:

http://localhost:8080

🧪 Testing Tips

Use Postman

Always send:

Content-Type: application/json
Authorization: Bearer <JWT>


Ensure correct request bodies (no text/plain)

🧠 Key Learnings

Correct JWT authentication using AuthenticationManager

Preventing broken object-level authorization (BOLA)

Importance of ownership validation

Real-world debugging of Spring Security (403/401 issues)

Secure integration with third-party LLM APIs

🔮 Future Enhancements

Frontend UI (React)

Role-based access (Admin/User)

File upload support

Conversation analytics

Rate limiting & caching

👨‍💻 Author

Darshan S V
Backend-focused Java Developer
Built with ❤️ and persistence

📄 License

This project is for educational and evaluation purposes.