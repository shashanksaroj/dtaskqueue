# Distributed Fault-Tolerant Task Queue

A scalable backend system for **asynchronous task processing** using **Spring Boot** and **RabbitMQ**.  
This project demonstrates **distributed task processing**, **fault-tolerance**, **retry mechanisms**, and **persistent task status tracking**

---

## Tech Stack

- **Backend:** Java, Spring Boot, Spring AMQP  
- **Queue:** RabbitMQ  
- **Persistence:** H2 / MySQL (via JPA)  
- **Build:** Maven / Gradle  
- **Optional:** Lombok  

---

## Features

- **Distributed Task Processing:** Multiple concurrent workers consume tasks from RabbitMQ.  
- **Fault-Tolerance & Retry:** Automatic retries with max attempts and dead-letter queue for failed tasks.  
- **Persistent Task Tracking:** Stores task status in DB (`PENDING`, `PROCESSING`, `COMPLETED`, `FAILED`).  
- **REST API:** Submit tasks, track status, and list all tasks.  
- **Scalable Architecture:** Supports horizontal scaling of workers.  

---

## API Endpoints

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/api/tasks` | POST | Enqueue a new task (payload in JSON) |
| `/api/tasks` | GET  | List all tasks with status |
| `/api/tasks/{id}` | GET | Get specific task status (optional) |

**Example request:**

```bash
POST /api/tasks
Content-Type: application/json

{
  "payload": "Process this task"
}
