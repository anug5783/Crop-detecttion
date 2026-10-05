# Crop Disease Detection App

A full-stack college project using:
- Java 17
- Spring Boot
- REST API
- Spring Data JPA / Hibernate
- MySQL
- React + Vite
- Mock prediction service

## Features
1. Farmer enters crop type and location.
2. Farmer uploads a crop/leaf image.
3. React sends multipart/form-data to Spring Boot.
4. Spring Boot validates input.
5. MockPredictionService returns disease/healthy status, confidence, diagnosis and recommended action.
6. Result is stored in MySQL using JPA/Hibernate.
7. React displays the result.
8. Previous predictions can be viewed from History.

## Project Structure

crop-disease-detection-app/
├── backend/
│   ├── pom.xml
│   └── src/main/java/com/example/cropdisease/
│       ├── CropDiseaseApplication.java
│       ├── controller/DiseaseController.java
│       ├── entity/DiseasePrediction.java
│       ├── repository/DiseasePredictionRepository.java
│       └── service/
│           ├── DiseasePredictionService.java
│           └── MockPredictionService.java
│   └── src/main/resources/application.properties
├── frontend/
│   ├── package.json
│   ├── index.html
│   └── src/
│       ├── main.jsx
│       ├── App.jsx
│       └── App.css
└── database/schema.sql

## Database Setup

Create a MySQL database:

CREATE DATABASE crop_disease_db;

Then update backend/src/main/resources/application.properties if your MySQL username/password is different.

Default:
username=root
password=root

## Run Backend

cd backend
mvn spring-boot:run

Backend:
http://localhost:8080

## Run Frontend

cd frontend
npm install
npm run dev

Frontend:
http://localhost:5173

## API

POST /api/disease/predict
Content-Type: multipart/form-data

Fields:
- image: image file
- cropType: e.g. Tomato
- location: e.g. Tirupati

GET /api/disease/history

GET /api/disease/health

## Important

This project uses a MOCK prediction service for demonstration. It is not a real ML model. The MockPredictionService can later be replaced by a Python ML model, TensorFlow model, or external prediction API.
