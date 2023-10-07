# InvestoBull_Assignment (Stock Management)

## Table of Contents
- [Introduction](#introduction)
- [Demo Video](#demo-video)
- [Features](#features)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
  - [1. Store Candles](#1-store-candles)
  - [2. Get Candle By CandleId](#2-get-candle-by-candleid)
  - [3. Get First And Last Candles](#3-get-first-and-last-candles)
  - [4. First ORB Candle](#4-first-orb-candle)
  - [5. New Interval Candles](#5-new-interval-candles)
- [Swagger Documentation](#swagger-documentation)
- [Technology Stack](#technology-stack)
- [About](#about)

## Introduction
Welcome to the InvestoBull_Assignment project, a Stock Management application developed using Java, Spring Boot, and Spring Data JPA. This application is designed to streamline the processing of financial data. Whether you're working on a solo project or looking to explore Java and Spring technologies, this application provides valuable functionality for managing stock data.

## Demo Video
[Insert a link to your demo video here, if available.]

## Features
- **Data Storage**: Easily store financial data from JSON files in a database.
- **ORB Candle Retrieval**: Retrieve the first Opening Range Breakout (ORB) candle within a specified time frame.
- **Interval Candle Generation**: Generate a list of candles for a given time interval.

## Installation
To run this Spring Boot project locally, follow these steps:

1. Clone this repository:
   ```sh
   git clone https://github.com/your-username/your-project.git
2. Navigate to the project directory: `cd your-project`
3. Build and run the project: `mvn spring-boot:run`

## API Endpoints

### 1. Store Candles
- **HTTP Method**: POST
- **Endpoint**: `http://localhost:8088/investo/save-candles`
- **Request**: None
- **Response**: A status message indicating success or failure
- **Description**: Saves candlestick data from a JSON file to the database.

### 2. Get Candle By CandleId
- **HTTP Method**: GET
- **Endpoint**: `http://localhost:8088/investo/candle/{candleId}`
- **Request**: URL parameter `candleId` (should be between 0 and 75)
- **Response**: JSON array containing stored candles

### 3. Get First And Last Candles
- **HTTP Method**: GET
- **Endpoint**: `http://localhost:8088/investo/first-last-candle`
- **Request**: None
- **Response**: JSON array containing the first and last stored candles

### 4. First ORB Candle
- **HTTP Method**: GET
- **Endpoint**: `http://localhost:8088/investo/ORB/{minutes}`
- **Request**: URL parameter `minutes` (should be divisible by 5)
- **Response**: JSON object containing the time of the first Opening Range Breakout (ORB) candle
- **Description**: Calculates and returns the time of the first ORB candle based on the specified time interval.

### 5. New Interval Candles
- **HTTP Method**: GET
- **Endpoint**: `http://localhost:8088/investo/candles/{minutes}`
- **Request**: URL parameter `minutes` (should be divisible by 5)
- **Response**: JSON object containing newly generated candles
- **Description**: Retrieves and combines candlestick data for the specified time interval.

## Swagger Documentation
Explore the API using Swagger at: [Swagger Documentation](http://localhost:8088/swagger-ui/index.html#/)

## Technology Stack
This project leverages the following technologies:
- Java
- Spring Boot
- Spring Data JPA
- H2 Database

## About
"This project was created as an individual assignment as part of a company's recruitment process. It stands as a tangible demonstration of Java, Spring Boot, and Spring Data JPA capabilities in the context of financial data management and processing."