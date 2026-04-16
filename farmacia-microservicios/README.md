# FarmaciaMicroservicios

## Sistema de Gestión de Farmacia con Microservicios

Proyecto de arquitectura de microservicios para gestionar la cadena de suministro de medicamentos:
**Laboratorio → Distribuidor → Farmacia → Reportes (SOAP/WSDL)**

### Arquitectura

```
┌─────────────────────────────────────────────────────┐
│              EUREKA SERVER (:8000)                   │
│              Registro de Servicios                   │
└──────┬──────────┬──────────┬──────────┬──────────────┘
       │          │          │          │
  ┌────┴────┐ ┌───┴───┐ ┌───┴───┐ ┌───┴─────────┐
  │  LAB    │ │ DIST  │ │ FARM  │ │ REPORTES    │
  │ :8001   │→│ :8002 │→│ :8003 │→│ :8004       │
  │BasicAuth│ │  JWT  │ │OAuth2 │ │ SOAP/WSDL   │
  └─────────┘ └───────┘ └───────┘ └─────────────┘
    Feign →     Feign →    Feign →
```

### Microservicios

| Servicio | Puerto | Seguridad | Descripción |
|----------|--------|-----------|-------------|
| eureka-server | 8000 | - | Registro y descubrimiento de servicios |
| ms-laboratorio | 8001 | Basic Auth | Producción de lotes de medicamentos |
| ms-distribuidor | 8002 | JWT | Distribución y almacenamiento |
| ms-farmacia | 8003 | OAuth2 | Venta al cliente final |
| ms-reportes-soap | 8004 | SOAP/WSDL | Reportes y estadísticas via SOAP |

### Tecnologías

- Java 17
- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Netflix Eureka (Service Discovery)
- OpenFeign (Comunicación entre servicios)
- Spring Security (Basic Auth, JWT, OAuth2)
- Spring Web Services (SOAP/WSDL)
- JJWT (JSON Web Tokens)

### Cómo Ejecutar

```bash
# 1. Eureka Server
cd eureka-server && ./mvnw spring-boot:run

# 2. Laboratorio
cd ms-laboratorio && ./mvnw spring-boot:run

# 3. Distribuidor
cd ms-distribuidor && ./mvnw spring-boot:run

# 4. Farmacia
cd ms-farmacia && ./mvnw spring-boot:run

# 5. Reportes SOAP
cd ms-reportes-soap && ./mvnw spring-boot:run
```

### Credenciales

- **Laboratorio**: usuario `laboratorio`, contraseña `farmacia2025`
- **Distribuidor**: usuario `distribuidor`, contraseña `farmacia2025`
- **WSDL**: `http://localhost:8004/ws/reportes.wsdl`

### Endpoints SOAP

- WSDL: `http://localhost:8004/ws/reportes.wsdl`
- Endpoint SOAP: `http://localhost:8004/ws`
- Reporte XML: `GET http://localhost:8004/reportes/xml`
