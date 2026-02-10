# Item Management REST API

A RESTful API for managing items with in-memory storage using Spring Boot.

## Features

- ✅ Create and retrieve items
- ✅ In-memory ArrayList storage
- ✅ Input validation
- ✅ Clean RESTful API design
- ✅ Comprehensive error handling
- ✅ Well-documented code

## Technology Stack

- **Java 21**
- **Spring Boot 4.0.2**
- **Maven** (Build Tool)
- **Lombok** (Reduce boilerplate code)
- **Jakarta Validation** (Input validation)

## Project Structure

```
src/main/java/com/demo/item_management/
├── controller/
│   └── ItemController.java          # REST endpoints
├── service/
│   └── ItemService.java             # Business logic & in-memory storage
├── model/
│   └── Item.java                    # Item entity/model
├── exception/
│   └── GlobalExceptionHandler.java  # Validation & error handling
└── ItemManagementApplication.java   # Main application class
```

## Item Model

The `Item` class has the following properties:

| Property     | Type   | Required | Description                          |
|-------------|--------|----------|--------------------------------------|
| id          | Long   | Auto     | Unique identifier (auto-generated)   |
| name        | String | Yes      | Name of the item                     |
| description | String | Yes      | Description of the item              |
| category    | String | No       | Category/type of the item            |
| price       | Double | No       | Price of the item                    |
| createdAt   | Long   | Auto     | Creation timestamp (auto-generated)  |

## API Endpoints

### 1. Add a New Item

**Endpoint:** `POST /api/items`

**Request Body:**
```json
{
  "name": "Laptop",
  "description": "High-performance laptop for development",
  "category": "Electronics",
  "price": 1299.99
}
```

**Success Response (201 Created):**
```json
{
  "success": true,
  "message": "Item created successfully",
  "data": {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance laptop for development",
    "category": "Electronics",
    "price": 1299.99,
    "createdAt": 1707580800000
  }
}
```

**Validation Error Response (400 Bad Request):**
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "name": "Item name is required",
    "description": "Item description is required"
  }
}
```

### 2. Get Item by ID

**Endpoint:** `GET /api/items/{id}`

**Success Response (200 OK):**
```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance laptop for development",
    "category": "Electronics",
    "price": 1299.99,
    "createdAt": 1707580800000
  }
}
```

**Not Found Response (404 Not Found):**
```json
{
  "success": false,
  "message": "Item not found with id: 99"
}
```

### 3. Get All Items (Bonus)

**Endpoint:** `GET /api/items`

**Success Response (200 OK):**
```json
{
  "success": true,
  "count": 2,
  "data": [
    {
      "id": 1,
      "name": "Laptop",
      "description": "High-performance laptop",
      "category": "Electronics",
      "price": 1299.99,
      "createdAt": 1707580800000
    },
    {
      "id": 2,
      "name": "Mouse",
      "description": "Wireless mouse",
      "category": "Electronics",
      "price": 29.99,
      "createdAt": 1707580900000
    }
  ]
}
```

### 4. Health Check

**Endpoint:** `GET /api/items/health`

**Success Response (200 OK):**
```json
{
  "status": "UP",
  "service": "Item Management API",
  "totalItems": 5
}
```

## How to Run the Application

### Prerequisites

- Java 21 or higher
- Maven 3.6 or higher

### Steps

1. **Clone or download the project**

2. **Navigate to the project directory:**
   ```bash
   cd item-management
   ```

3. **Build the project:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the JAR file:
   ```bash
   java -jar target/item-management-0.0.1-SNAPSHOT.jar
   ```

5. **The application will start on port 8080:**
   ```
   Item Management API is running!
   Access the API at: http://localhost:8080
   Health Check: http://localhost:8080/api/items/health
   ```

## Testing the API

### Using cURL

**Add an item:**
```bash
curl -X POST http://localhost:8080/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "High-performance laptop",
    "category": "Electronics",
    "price": 1299.99
  }'
```

**Get an item by ID:**
```bash
curl http://localhost:8080/api/items/1
```

**Get all items:**
```bash
curl http://localhost:8080/api/items
```

**Health check:**
```bash
curl http://localhost:8080/api/items/health
```

### Using Postman

1. Import the API endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. For POST requests, set:
   - Method: POST
   - URL: `http://localhost:8080/api/items`
   - Headers: `Content-Type: application/json`
   - Body: Raw JSON with item data

## Input Validation

The API validates all input data:

- **Name**: Cannot be blank/empty
- **Description**: Cannot be blank/empty
- Invalid requests return HTTP 400 with detailed error messages

Example validation error:
```json
{
  "success": false,
  "message": "Validation failed",
  "errors": {
    "name": "Item name is required",
    "description": "Item description is required"
  }
}
```

## Implementation Details

### In-Memory Storage
- Uses `ArrayList<Item>` for storing items
- Thread-safe ID generation using `AtomicLong`
- Data persists only during application runtime
- Data is lost when the application restarts

### ID Generation
- IDs are auto-generated starting from 1
- Uses `AtomicLong` for thread-safe increment

### Error Handling
- Global exception handler for validation errors
- Proper HTTP status codes (201, 200, 400, 404, 500)
- Consistent error response format

## Deployment

This application can be deployed to various platforms:

### Render
1. Create a new Web Service on Render
2. Connect your GitHub repository
3. Build Command: `mvn clean package`
4. Start Command: `java -jar target/item-management-0.0.1-SNAPSHOT.jar`

### Railway
1. Create a new project on Railway
2. Connect your GitHub repository
3. Railway auto-detects Spring Boot and deploys

### Heroku
1. Create a `Procfile` with: `web: java -jar target/item-management-0.0.1-SNAPSHOT.jar`
2. Deploy using Git or GitHub integration

### AWS Elastic Beanstalk
1. Package as JAR: `mvn clean package`
2. Upload JAR to Elastic Beanstalk
3. Configure Java platform

## API Response Format

All API responses follow a consistent format:

**Success Response:**
```json
{
  "success": true,
  "message": "Optional message",
  "data": { /* response data */ }
}
```

**Error Response:**
```json
{
  "success": false,
  "message": "Error message",
  "errors": { /* validation errors if applicable */ }
}
```

## Future Enhancements

Potential improvements for this API:

- Add UPDATE (PUT/PATCH) endpoint
- Add DELETE endpoint
- Add search/filter functionality
- Add pagination for large datasets
- Implement database persistence (PostgreSQL, MySQL)
- Add authentication & authorization
- Add unit and integration tests
- Add API documentation (Swagger/OpenAPI)
- Add Docker support

## Contact

For questions or issues, please contact: **dsvjavalinux@gmail.com**

## License

This project is for demonstration purposes.

