# 🌐 Frontend - Farmacia San Marcos

Frontend simple y elegante para el sistema de gestión de farmacia.
Construido con **HTML + CSS + JavaScript puro** (sin frameworks, sin instalaciones).

---

## 🚀 Cómo usar (3 pasos)

### 1️⃣ Asegúrate de tener los microservicios corriendo
Los 5 microservicios deben estar arriba en los puertos:
- Eureka: 8000
- Laboratorio: 8001
- Distribuidor: 8002
- Farmacia: 8003
- Reportes: 8004

### 2️⃣ Habilita CORS en los microservicios (⚠️ PASO OBLIGATORIO)

Por seguridad, los navegadores **bloquean** las peticiones desde archivos HTML locales hacia servidores. Hay que habilitar CORS en cada microservicio. Para esto, copia la clase `CorsConfig.java` (que te incluyo abajo) en cada microservicio.

👉 **Sigue las instrucciones en `INSTRUCCIONES_CORS.md`** (muy importante).

### 3️⃣ Abrir el frontend
Simplemente haz **doble click** en el archivo `index.html` y se abrirá en tu navegador.

O click derecho → Abrir con → Chrome/Edge/Firefox.

---

## 🔐 Credenciales para iniciar sesión

- **Usuario**: `distribuidor`
- **Contraseña**: `farmacia2025`

El login utiliza el endpoint `POST /auth/login` del microservicio de distribuidor, que devuelve un token JWT que se usa en toda la aplicación.

---

## 🎨 Funcionalidades

### Pestañas del sistema:

1. **📊 Dashboard** — Resumen general (total ventas, ingresos, unidades, lotes disponibles) + estado de microservicios.

2. **📚 Catálogo** — Lista de medicamentos del endpoint público `/farmacia/public/catalogo` (sin auth).

3. **🧪 Laboratorio** — Ver lotes producidos, crear nuevos lotes y enviarlos al distribuidor.

4. **🚚 Distribuidor** — Ver inventario y despachar lotes a farmacia (comunicación interna vía Feign).

5. **💊 Farmacia (Stock)** — Ver medicamentos disponibles y registrar ventas.

6. **🧾 Ventas** — Historial completo de ventas registradas.

7. **📈 Reportes** — Resumen consolidado + enlaces al XML y al WSDL (SOAP).

---

## 🎯 Cómo demostrar el flujo completo en la sustentación

1. Inicia sesión con `distribuidor / farmacia2025`
2. Ve al **Dashboard** — muestra los microservicios
3. Ve a **Catálogo** — el único endpoint público (sin auth)
4. Ve a **Laboratorio** → producir lote nuevo → **Enviar al distribuidor**
5. Ve a **Distribuidor** → ver el lote recién recibido → **Despachar a farmacia**
6. Ve a **Farmacia** → ver el lote en stock → **Vender**
7. Ve a **Ventas** → la venta aparece
8. Ve a **Reportes** → los totales se actualizan automáticamente (comunicación vía Feign)
9. Haz click en **"Ver reporte XML"** → abre el XML generado
10. Haz click en **"Ver WSDL"** → abre el contrato SOAP

---

## 💡 Puntos clave para explicar en la presentación

✅ **Frontend totalmente desacoplado** — solo usa las APIs REST
✅ **Consume los 5 microservicios** desde una sola interfaz
✅ **Usa autenticación JWT** real (obtiene token y lo envía en cada petición)
✅ **Maneja diferentes esquemas de seguridad**:
  - Basic Auth para laboratorio
  - JWT para distribuidor y farmacia
  - Endpoint público sin autenticación (catálogo)
✅ **Muestra los dos protocolos**: REST (todas las pantallas) y SOAP (enlace a WSDL)

---

## 📂 Archivos

- `index.html` — La aplicación completa (todo en un solo archivo)
- `INSTRUCCIONES_CORS.md` — Cómo habilitar CORS
- `CorsConfig.java` — La clase que hay que copiar a cada microservicio
- `README.md` — Este archivo
