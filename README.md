## 🚧 Project Status

This project is currently under development.

# 🚀 Redis Virality Engine

A production-ready backend system built using **Spring Boot** and **Redis** that simulates a real-time social media virality engine with strict guardrails to prevent spam and uncontrolled bot activity.

---

## 🔥 Features

### ⚡ Real-time Virality Score

* Bot Reply → **+1**
* Human Like → **+20**
* Human Comment → **+50**

---

### 🚫 Bot Rate Limiting (Horizontal Cap)

* Each post allows a maximum of **100 bot replies**
* Ensures fair usage and prevents bot flooding

---

### ⏱️ Cooldown System (Anti-Spam)

* A bot cannot interact with the same user more than **once every 10 minutes**
* Implemented using Redis TTL (Time-To-Live)

---

### 🧱 Comment Depth Control (Vertical Cap)

* Nested comments limited to **20 levels**
* Prevents deep recursive threads

---

### 🔒 Redis-Based Atomic Operations

* Ensures thread-safe updates
* Handles concurrent requests efficiently

---

## 🛠️ Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Data JPA**
* **Redis**
* **MySQL**
* **MapStruct**
* **Lombok**

---

## 📦 API Endpoints

### ➤ Create Comment

POST /api/comments/{postId}

---

### ➤ Get Comments by Post

GET /api/comments/{postId}

---

### ➤ (Optional) Like Post

POST /api/posts/{postId}/like

---

## ⚙️ Redis Keys Structure

post:{id}:virality_score
post:{id}:bot_count
cooldown:bot:{botId}:human:{userId}

---

## 🧪 Example Request

### Create Bot Comment

```json
{
  "authorId": 1,
  "authorType": "BOT",
  "targetUserId": 2,
  "content": "AI generated reply"
}
```

---

## 🚀 How to Run

1. Clone the repository
2. Start Redis server
3. Configure MySQL database
4. Run the Spring Boot application

---

## 🧠 Key Learnings

* Designing real-time systems using Redis
* Implementing rate limiting and cooldown mechanisms
* Handling concurrent requests safely
* Building scalable backend architectures

---

## 👨‍💻 Author

**Nabeel Khan**

---

## ⭐ If you found this project useful, consider giving it a star!
