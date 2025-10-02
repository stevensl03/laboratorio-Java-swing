# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is an academic management system built with Java Swing, implementing a complete MVC architecture with multiple design patterns. The application manages students, professors, courses, faculties, programs, and enrollments with support for H2, MySQL, and Oracle databases.

## Development Commands

### Build and Run
```powershell
# Build the project
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.mycompany.aplicacionpoo.AplicacionPOO"

# Create executable JAR
mvn clean package

# Run the JAR file
java -jar target/AplicacionPOO-1.0-SNAPSHOT.jar
```

### Testing
```powershell
# Run all tests
mvn test

# Run single test class
mvn test -Dtest=ClassName

# Run tests with preview features
mvn test -Dargline="--enable-preview"
```

### Database Operations
```powershell
# Start MySQL and Oracle containers
docker-compose up -d

# Stop containers
docker-compose down

# View logs
docker-compose logs mysql
docker-compose logs oracle-lab
```

### Database Switching
Edit `src/main/resources/database-selector.properties`:
```properties
# Switch between: H2, MYSQL, ORACLE
db.type=H2
```

## Architecture Overview

### Core Design Patterns

1. **Singleton Pattern**: ApplicationContext, DatabaseAdapter, and factories ensure single instances
2. **Factory Pattern**: ExternalFactory and InternalFactory create objects without exposing instantiation logic
3. **Adapter Pattern**: DatabaseAdapter provides unified interface across different database systems
4. **Dependency Injection**: Custom annotation-based DI system with @Autowired, @Component, @Service
5. **DAO Pattern**: Generic DAO interface with database-specific implementations (H2, MySQL, Oracle)
6. **MVC Pattern**: Clear separation between Model, View, and Controller layers
7. **DTO Pattern**: Data Transfer Objects for safe data transmission between layers

### Package Structure

- **`adapter/`**: Database connection abstraction layer
- **`annotation/`**: Custom annotations for dependency injection (@Autowired, @Component, @Service)
- **`context/`**: Application context and dependency injection container
- **`Controller/`**: MVC controllers handling business logic coordination
- **`dao/`**: Data access layer with generic interface and database-specific implementations
  - `impl/h2/`, `impl/mysql/`, `impl/oracle/`: Database-specific DAO implementations
- **`dto/`**: Data transfer objects for safe data handling
- **`factory/`**: Object creation factories (External and Internal)
- **`Model/`**: Entity classes (Persona, Estudiante, Profesor, Curso, etc.)
- **`service/`**: Business logic services layer
- **`View/`**: Swing GUI components and windows

### Database Architecture

The system uses a unified database adapter that supports three databases:
- **H2**: Default embedded database for development (`./data/testdb`)
- **MySQL**: Full-featured database for production
- **Oracle**: Enterprise database support

All databases share the same schema with 8 core tables:
- `persona` (base for students and professors)
- `facultad`, `programa`, `profesor`, `estudiante`
- `curso`, `inscripcion`, `cursoprofesor`

### Dependency Injection System

The application implements a custom DI container similar to Spring:
- **ApplicationContext**: Central bean registry and lifecycle manager
- **@Component**: Marks classes as beans for automatic registration
- **@Service**: Specialized component annotation for service layer
- **@Autowired**: Field injection annotation with optional/required support

### Key Application Components

1. **ApplicationContext**: Manages object lifecycle and dependencies
2. **DatabaseAdapter**: Handles database connections and SQL dialect differences
3. **ExternalFactory**: Creates DTOs, Controllers, and Views
4. **InternalFactory**: Creates Models and Services
5. **VentanaPrincipal**: Main application window coordinating all views

## Configuration Files

- **`database-selector.properties`**: Choose active database (H2/MYSQL/ORACLE)
- **`database-config.properties`**: Connection settings for all databases
- **`docker-compose.yml`**: MySQL and Oracle container definitions
- **`init.sql`**: MySQL database schema and sample data
- **`init-oracle.sql`**: Oracle-specific initialization
- **`nbactions.xml`**: NetBeans IDE integration with Java preview features

## Key Features

- **Multi-database support**: Switch between H2, MySQL, Oracle without code changes
- **Custom DI framework**: Annotation-driven dependency injection
- **Generic DAO pattern**: Type-safe database operations
- **Factory pattern**: Centralized object creation
- **Swing GUI**: Complete desktop application interface
- **Docker integration**: Containerized database services
- **Java 21**: Modern Java features with preview support

## Main Entry Points

- **`AplicacionPOO.java`**: Main application entry point
- **`VentanaPrincipal.java`**: Primary GUI window
- **`ApplicationContext.getInstance()`**: Access to DI container
- **`DatabaseAdapter.getInstance()`**: Database connection management

## Development Notes

- The project uses Java 21 with preview features enabled
- Database switching requires only property file changes
- The custom DI system mimics Spring's behavior with @Component and @Autowired
- All database implementations follow the same GenericDao interface
- DTOs provide safe data transfer between layers with validation
- The factory pattern isolates object creation concerns
- Swing components use the MVC pattern for clean separation

## Environment Setup

1. Ensure Java 21+ is installed
2. Docker Desktop for database containers (optional)
3. IDE with Maven support (NetBeans configurations included)
4. Configure database connections in `database-config.properties`
5. Choose target database in `database-selector.properties`

The application will automatically initialize the selected database and create sample data on first run.