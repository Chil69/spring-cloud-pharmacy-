# ⚠️ Cómo habilitar CORS en los microservicios

**¿Por qué?** Los navegadores bloquean por seguridad las peticiones desde un archivo HTML local (`file://`) hacia servidores en `http://localhost:8001`, etc. Hay que decirle explícitamente al backend que "permita" estas peticiones.

**Esto se hace UNA sola vez y queda configurado para siempre.**

---

## 🎯 Pasos (repetir para cada microservicio)

Debes crear la clase `CorsConfig.java` en **cada microservicio** (excepto eureka-server). Son **4 veces**: laboratorio, distribuidor, farmacia y reportes.

### Ejemplo: ms-laboratorio

1. En IntelliJ, navega a:
   ```
   ms-laboratorio/src/main/java/com/farmacia/laboratorio/config/
   ```

2. Click derecho sobre la carpeta `config` → **New → Java Class** → nombre: `CorsConfig`

3. Pega este código (reemplaza la palabra `PAQUETE` con `laboratorio`):

```java
package com.farmacia.laboratorio.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

### Repetir para los otros 3 microservicios

Crea la misma clase en estas ubicaciones, cambiando solo el `package`:

| Microservicio | Ruta | Package |
|---|---|---|
| **ms-laboratorio** | `com/farmacia/laboratorio/config/CorsConfig.java` | `com.farmacia.laboratorio.config` |
| **ms-distribuidor** | `com/farmacia/distribuidor/config/CorsConfig.java` | `com.farmacia.distribuidor.config` |
| **ms-farmacia** | `com/farmacia/farmacia/config/CorsConfig.java` | `com.farmacia.farmacia.config` |
| **ms-reportes-soap** | `com/farmacia/reportes/config/CorsConfig.java` | `com.farmacia.reportes.config` |

### ⚠️ Para ms-laboratorio y ms-distribuidor (adicional)

Como estos tienen Spring Security con Basic Auth/JWT, también hay que permitir las peticiones OPTIONS (preflight) en el SecurityConfig.

#### En `SecurityConfig.java` de **ms-laboratorio**:

Busca el método `filterChain`, que luce así:

```java
http.csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth
        .anyRequest().authenticated())
    .httpBasic(...);
```

Y cámbialo a esto (agrega la línea con OPTIONS):

```java
http.csrf(csrf -> csrf.disable())
    .cors(cors -> {})  // ← habilitar CORS
    .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ← permitir preflight
        .anyRequest().authenticated())
    .httpBasic(...);
```

Y agrega los imports arriba del archivo:
```java
import org.springframework.http.HttpMethod;
```

#### En `SecurityConfig.java` de **ms-distribuidor** (mismo procedimiento):

```java
http.csrf(csrf -> csrf.disable())
    .cors(cors -> {})  // ← agregar
    .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ← agregar
        .requestMatchers("/auth/**").permitAll()
        .anyRequest().authenticated())
    ...
```

#### En `SecurityConfig.java` de **ms-farmacia**:

```java
http.csrf(csrf -> csrf.disable())
    .cors(cors -> {})  // ← agregar
    .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // ← agregar
        .requestMatchers("/farmacia/public/**").permitAll()
        .requestMatchers("/farmacia/**").authenticated()
        .anyRequest().permitAll())
    ...
```

---

## 🔁 Paso final

1. Reinicia los 4 microservicios (laboratorio, distribuidor, farmacia, reportes) en IntelliJ.
2. Abre el `index.html` del frontend → ya debería funcionar.

---

## ❌ Cómo saber que CORS no está configurado

Si abres el frontend y ves errores en la consola del navegador (F12) como:

```
Access to fetch at 'http://localhost:8002/auth/login' from origin 'null'
has been blocked by CORS policy
```

Es que CORS no está bien. Revisa que creaste `CorsConfig.java` en los 4 microservicios.

---

## 💡 Tip

Si te da pereza editar tanto, puedes abrir el `index.html` usando un servidor local simple desde la terminal:

```bash
cd frontend-farmacia
python -m http.server 8080
```

Luego abre `http://localhost:8080` en el navegador. Pero **aún así tendrás que configurar CORS** porque seguirá siendo un origen distinto.
