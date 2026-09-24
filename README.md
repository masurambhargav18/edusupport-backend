# EduSupport

## Student Support & Ticket Management System

EduSupport is a full-stack student support and ticket management application.

Students can create support tickets and communicate through comments. Staff members can manage ticket status, priority, and assignment. Administrators can view overall ticket management information.

## Features

### Authentication
- JWT-based authentication
- Student, Staff, and Admin login
- Role-based dashboards
- BCrypt password hashing
- Stateless authentication

### Student
- Login
- View own tickets
- Create support tickets
- Select category and priority
- View ticket details
- Add comments
- View activity history

### Staff
- Login
- View all tickets
- View ticket details
- Assign tickets
- Change ticket status
- Change ticket priority
- Add comments
- View activity history
- View management statistics

### Admin
- Login
- View all tickets
- View ticket details
- View management statistics
- View assignment information
- View comments and activity history
- Manage ticket status, priority, and assignment

## Technology Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

### Frontend
- React
- Vite
- Axios
- JavaScript
- CSS

### Database
- PostgreSQL
- Neon PostgreSQL

## Architecture

React/Vite Frontend
        |
        | REST API + JWT
        v
Spring Boot Backend
        |
        | JPA / Hibernate
        v
Neon PostgreSQL

## Database Entities

### Users
- User ID
- Name
- Email
- Password
- Role

Roles:
- STUDENT
- STAFF
- ADMIN

### Tickets
- Ticket ID
- Ticket number
- Student
- Assigned staff
- Category
- Subject
- Description
- Priority
- Status
- Created time
- Updated time
- Due time
- Resolved time
- Closed time

### Ticket Comments
- Comment ID
- Ticket
- Author
- Message
- Created time

### Ticket Activity
Records ticket history such as:
- TICKET_CREATED
- TICKET_UPDATED
- COMMENT_ADDED

## Ticket Status

- OPEN
- IN_PROGRESS
- RESOLVED
- CLOSED

## Ticket Priority

- LOW
- MEDIUM
- HIGH
- CRITICAL

## SLA Logic

CRITICAL -> 4 hours
HIGH -> 8 hours
MEDIUM -> 24 hours
LOW -> 72 hours

## API Endpoints

### Authentication

POST /api/auth/login

### Tickets

POST /api/tickets
GET /api/tickets
GET /api/tickets/{id}
GET /api/tickets/student/{id}
PUT /api/tickets/{id}

### Comments

POST /api/tickets/{id}/comments
GET /api/tickets/{id}/comments

### Activity

GET /api/tickets/{id}/activities

## Demo Accounts

Student:
student@edusupport.com
student123

Staff:
staff@edusupport.com
staff123

Admin:
admin@edusupport.com
admin123

## Running the Backend

Open the backend project in IntelliJ IDEA.

Make sure the Neon PostgreSQL configuration is available in:

src/main/resources/application.properties

Run using IntelliJ Maven:

Maven
-> Plugins
-> spring-boot
-> spring-boot:run

Backend:

http://localhost:8080

## Running the Frontend

Open:

EduSupport_Frontend_Full

Install dependencies:

npm install

Start the development server:

npm run dev

Frontend:

http://localhost:5173

## Project Structure

Backend:

src/
  main/
    java/
      com/
        edusupport/
          config/
          controller/
          dto/
          entity/
          repository/
          service/
    resources/
      application.properties

Frontend:

src/
  main.jsx
  styles.css

## Security

The application uses:

- Spring Security
- JWT authentication
- BCrypt password hashing
- Stateless authentication
- Bearer token authorization

## Testing Performed

### Student
- Login
- Ticket listing
- Ticket creation
- Ticket details
- Comment creation
- Activity history

### Staff
- Login
- View all tickets
- Ticket assignment
- Status update
- Priority update
- Comment creation
- Activity history
- Management statistics

### Admin
- Login
- View all tickets
- Management dashboard
- Ticket details
- Assignment information
- Status and priority management

### Database

Verified PostgreSQL tables:

- users
- tickets
- ticket_comments
- ticket_activity

## Example Ticket Workflow

Student creates ticket
        |
        v
Ticket status = OPEN
        |
        v
Staff assigns ticket
        |
        v
Priority/status updated
        |
        v
Status = IN_PROGRESS
        |
        v
Staff adds comments
        |
        v
Activity history recorded
        |
        v
Status = RESOLVED
        |
        v
Ticket can be CLOSED

## Future Improvements

- HttpOnly cookie-based authentication
- Refresh tokens
- Fine-grained backend authorization
- Pagination and filtering
- Search functionality
- File attachments
- Email notifications
- SLA reminder notifications
- Automated tests
- Docker deployment
- Production environment variables
- Additional audit logging

## AI-Assisted Development

AI tools were used during development for:

- Project scaffolding
- Code generation assistance
- Debugging
- API troubleshooting
- Frontend implementation
- JWT authentication implementation
- Database configuration assistance
- Testing guidance
- Documentation preparation

A separate AI Usage Report is included with the submission.

## Author

EduSupport Assignment Project

Built using:

Java + Spring Boot
React + Vite
PostgreSQL + Neon
JWT + Spring Security
