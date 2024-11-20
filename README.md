# Snowflake ID Generation Demo Application

A robust Spring Boot application demonstrating unique, distributed ID generation using the Snowflake algorithm.

##  Key Features

- **Distributed ID Generation**: Produces globally unique, time-ordered identifiers
- **High Performance**: Low latency ID generation with minimal contention
- **Spring Boot Integration**: Seamless JPA configuration
- **Scalable Architecture**: Supports multiple datacenter and worker configurations

## 🛠 Prerequisites

- Java 17+
- Maven 3.8+
- MySQL Database

##  Project Configuration

### application.properties

```properties
# Snowflake ID Generation Configuration
snowflake.datacenter-id=1
snowflake.worker-id=1

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/snowflake_demo
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

##  Snowflake ID Algorithm Details

### ID Structure

```
| 41-bit (timestamp) | 5-bit (datacenter) | 5-bit (worker) | 12-bit (sequence) |
```

### Key Components

- **Timestamp (41 bits)**: Milliseconds since custom epoch (2024-01-01)
- **Datacenter ID (5 bits)**: Supports 0-31 unique datacenter IDs
- **Worker ID (5 bits)**: Supports 0-31 unique worker IDs
- **Sequence (12 bits)**: Up to 4096 unique IDs per millisecond

## ID Generation Details

- Epoch: January 1, 2024 (1704067200000L)
- Timestamp Bits: 41 (supports ~69 years)
- Datacenter ID Bits: 5 (0-31 range)
- Worker ID Bits: 5 (0-31 range)
- Sequence Bits: 12 (0-4095 per millisecond)

##  Usage Examples

### Entity ID Assignment

```java
@Entity
public class Post {
    @Id
    private Long id;  // Automatically assigned Snowflake ID

    @PrePersist
    public void prePersist() {
        SnowflakeUtil.prePersist(this);
    }
}
```

### Manual ID Parsing

```java
Map<String, Long> idComponents = 
    SnowflakeGenerator.parseId(snowflakeId);
```

## Key Implementation Details

- Singleton pattern for SnowflakeGenerator
- Uses SecureRandom for sequence generation
- Synchronized ID generation
- Clock skew protection
- Random sequence number to prevent consecutive ID collisions

## Performance Considerations

- Low overhead ID generation
- Supports up to 4096 IDs per millisecond
- Minimal synchronization contention

##  Best Practices

- Use unique datacenter and worker IDs
- Implement error handling for ID generation
- Monitor ID generation performance
- Test clock synchronization scenarios

##  Potential Improvements

- Implement distributed ID generation
- Add fallback ID generation strategies
- Create comprehensive test coverage

##  Contributing

1. Configure unique node identifiers
2. Ensure robust error handling
3. Validate ID generation consistency