# Meteorological Sensor REST API

This project is a Spring Boot-based REST API for managing data sent from Meteorological sensors.

## Features
- **Sensor Registration**: Add new sensors to the system.
- **Data Submission**: Record temperature and rainfall measurements from registered sensors.
- **Data Retrieval**: Access all sensors, measurements, and total days with rain.
- **Validation**: Ensures data integrity with validation rules.

## Endpoints

### 1. Sensor Registration
**POST** `/sensors/registration`  
Registers a new sensor.  
**Request Body (JSON):**
```json
{
  "name": "SensorName"
}
```

#### Validation

- `name` must be unique
- `name` must be between 3 and 30 characters.

### 2. Add Measurement
**POST** `/measurements/add`  
Adds a new measurement.  
**Request Body (JSON):**
```json
{
  "value": 22.5,
  "raining": false,
  "sensor": {
    "name": "SensorName"
  }
}
```

#### Validation

- `value` must be between -100 and 100.
- `sensor` must be registered.

### 3. Get all measurements
**GET** `/measurements`  
Returns all the measurements.  
**Response example:**
```json
[
    {
        "value": 24.7,
        "raining": false,
        "sensor": {
            "name": "test sensor1"
        }
    },
    {
        "value": 26.3,
        "raining": true,
        "sensor": {
            "name": "test sensor2"
        }
    }
]
```

### 4. Get all sensors
**GET** `/sensors`  
Returns all the sensors.  
**Response example:**
```json
[
    {
        "name": "test sensor1"
    },
    {
        "name": "test sensor2"
    }
]
```

### 5. Get total days with rain
**GET** `/measurements/rainyDaysCount`  
Returns total number of rainy days. 
**Response example:**
```json
{
    "rainyDaysCount": 251
}
```
