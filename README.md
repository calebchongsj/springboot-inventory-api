# Inventory Management REST API
A backend-only inventory management application developed using Java 21, Spring Boot 4 and MongoDB Atlas.

The application provides REST endpoints for creating inventory items, retrieving inventory records and updating inventory quantities.

## Technologies
- Java 21
- Spring Boot 4
- Spring Web
- Spring Data MongoDB
- MongoDB Atlas
- Maven
- JUnit 5 and Mockito

## Architecture

The application follows a layered backend architecture:
**InventoryController → InventoryService → InventoryRepository → MongoDB Atlas**

- **Controller:** Handles HTTP requests and responses.
- **Service:** Implements inventory validation and business logic using imperative Java programming.
- **Repository:** Provides MongoDB persistence operations through Spring Data MongoDB.
- **Model:** Represents inventory items stored as MongoDB documents.
- **Exception handlers:** Return appropriate HTTP error responses.

## REST API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/items` | Create an inventory item |
| GET | `/api/items` | Retrieve all inventory items |
| PATCH | `/api/items/{id}/quantity?quantity=15` | Update an item's quantity |

### Example POST Request

```bash
curl -i -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","quantity":10}'
```

### Example JSON Response

```json
{
  "name": "Laptop",
  "quantity": 10,
  "id": "GENERATED_MONGODB_ID"
}
```

## Input Validation and Error Handling
The application validates inventory data before persistence.
- Rejects negative inventory quantities.
- Rejects empty inventory item names.
- Rejects client-supplied IDs during item creation.
- Returns HTTP 400 for invalid inputs.
- Returns HTTP 404 when an inventory item does not exist.

## Local Setup

### Prerequisites
- Java 21
- MongoDB Atlas database and database user
- Network access to the Atlas cluster

### Configuration

Create a `.env` file in the repository root:

```dotenv
MONGODB_URI='mongodb+srv://USERNAME:PASSWORD@ATLAS_HOST/inventory_db'
```

Replace the placeholders with your own MongoDB Atlas connection details.

The `.env` file is excluded from Git tracking.

### Run the Application

On Ubuntu, from the project root:

```bash
set -a
source .env
set +a
./mvnw spring-boot:run
```

The application runs at `http://localhost:8080`.

### Run Unit Tests

```bash
./mvnw -Dtest=InventoryServiceTest test
```

## Testing

JUnit 5 and Mockito unit tests verify service-layer behavior, including:

- Rejecting inventory items with negative quantities.
- Updating the quantity of an existing inventory item.

REST endpoints were also manually tested using curl, with MongoDB Atlas used to verify persistence.

## Project Scope

This is a learning project focused on enterprise Java backend fundamentals, including Spring Boot dependency injection, REST APIs, layered architecture, imperative business logic and MongoDB integration.

It does not include a frontend, authentication, deployment infrastructure or production-grade concurrency controls.