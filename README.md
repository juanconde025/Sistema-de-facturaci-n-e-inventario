# 📦 Sistema de Facturación e Inventario

Este proyecto es un **Sistema de Facturación e Inventario** desarrollado con **Spring Boot** para backend, con el objetivo de facilitar el registro de ventas, control de artículos en stock y gestión de clientes por medio de NIT. Es una solución ideal para pequeñas y medianas empresas que buscan llevar un control efectivo y automatizado de su operación comercial.

---

## 📚 Tabla de Contenido

- [🚀 Características](#-características)
- [🧱 Arquitectura](#-arquitectura)
- [🛠️ Tecnologías](#️-tecnologías)
- [📂 Estructura del Proyecto](#-estructura-del-proyecto)
- [🔁 Endpoints API](#-endpoints-api)
- [💾 Base de Datos](#-base-de-datos)
- [🧪 Cómo Probar](#-cómo-probar)
- [📈 Futuras Mejoras](#-futuras-mejoras)
- [🧑‍💻 Autor](#-autor)
- [📹 Video Presentacion](#-video-presentación)

---

## 🚀 Características

- Gestión de clientes por número de NIT.
- Registro de facturas con control de fechas de vencimiento.
- Asociación entre facturas y artículos.
- Gestión de artículos (laboratorio, precio, costo, saldo).
- Cálculo automático de totales y verificación de precios mayores a costos.
- Visualización de disponibilidad y cartera del cliente.
- Integración con Kardex para control de inventario por método PEPS.

---

## 🧱 Arquitectura

El sistema sigue una arquitectura **Spring Boot MVC** con acceso a datos a través de **JPA (Hibernate)**:

Controller → Service → Repository → Database (MySQL)


---

## 🛠️ Tecnologías

- **Java 17+**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL / PostgreSQL**
- **Lombok (opcional)**
- **Maven**

---

## 📂 Estructura del Proyecto

La estructura del código fuente se organiza de la siguiente manera:

```
src/
├── controller/          # Controladores REST que manejan las peticiones HTTP
├── service/             # Lógica de negocio de la aplicación
├── model/               # Entidades JPA que representan las tablas de la base de datos
├── repository/          # Interfaces JPA para acceso a datos
├── SistemaApplication.java # Clase principal que arranca la aplicación Spring Boot
└── resources/
    └── static/
            └── app.js      # Interactividad con el front
            └── styles.css  # Estilos del front
    └── templates/
            └── index.html  # Estructura del front
    └── application.properties # Configuración de la base de datos y otros ajustes
```

---

## 🔁 Endpoints API

### 🔹 Nit (Clientes)
| Método | Endpoint              | Descripción                       |
|--------|------------------------|-----------------------------------|
| GET    | `/api/nits/{nitDoc}`   | Obtener cliente por NIT           |
| POST   | `/api/nits`            | Guardar o actualizar cliente      |

### 🔹 Artículos
| Método | Endpoint              | Descripción                        |
|--------|------------------------|------------------------------------|
| GET    | `/api/articulos/{cod}` | Obtener artículo por código        |
| POST   | `/api/articulos`       | Guardar o actualizar artículo      |

### 🔹 Facturas
| Método | Endpoint                      | Descripción                             |
|--------|-------------------------------|-----------------------------------------|
| GET    | `/api/facturas/{facNum}`      | Obtener factura por número              |
| GET    | `/api/facturas/nit/{nitDoc}`  | Obtener facturas asociadas al cliente   |
| POST   | `/api/facturas`               | Guardar factura con control de PEPS     |

---

## 💾 Base de Datos

- Tabla `nit`: Clientes
- Tabla `articulos`: Productos disponibles
- Tabla `factura`: Registro de ventas
- Tabla `kardex`: Control de inventario con método PEPS

**Campos Clave:**
- `nitDoc`: Identificación única de cliente
- `artCod`: Código del artículo
- `facNum`: Número de factura

---

## 🧪 Cómo Probar

1. Clonar el repositorio:
```bash
git clone https://github.com/juanconde025/Sistema-de-facturaci-n-e-inventario.git
```
2. Crear la base de datos pruebatecnica en tu gestor SQL.

3. Configurar las credenciales en application.properties.

4. Ejecutar el proyecto desde tu IDE o con:
```bash
mvn spring-boot:run
```

5. Probar endpoints en Postman o Swagger (si está configurado).

---

## 📈 Futuras Mejoras

1. Sistema de autenticación de usuarios.

2. Exportación de reportes (Excel / PDF).

3. Historial de facturas vencidas y alertas.

4. Validaciones más robustas en stock y ventas.

---

## 🧑‍💻 Autor

**Juan David Conde Martínez**  
Desarrollador Backend Junior  


---

## 📹 Video Presentación

https://www.loom.com/share/b840511abc144bb38c6f005a6c03711c?sid=75b2a66f-3a9b-450d-85bd-ed07faf4b338
