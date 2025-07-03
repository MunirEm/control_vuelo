# ✈️ Flight Cabin – Simulación de Control de Vuelo en Java

Este proyecto simula el proceso completo de despegue de un avión desde una cabina virtual. Desarrollado en Java con Swing, incluye control visual, acceso a bases de datos Access y lógica de vuelo escalonada según destino.

---

## 🧩 Características principales

- Interfaz gráfica construida con **Java Swing + WindowBuilder**
- Conexión a bases de datos Access (`Origen.accdb` y `Destino.accdb`) mediante **UCanAccess**
- Simulación del **movimiento del avión** en pista y en aire
- Control de parámetros del vuelo: velocidad, altitud, flaps, slats, piloto automático, etc.
- Registro en consola de eventos de vuelo (inicio, carga, configuración, despegue, finalizado)
- Estados de vuelo actualizados en BD: *En tránsito*, *Finalizado*, etc.
- Personalización estética con **JTattoo (AeroLookAndFeel)**

---

## 📂 Estructura de la base de datos

### `Origen.accdb`
Contiene:
- `Destino`
- `Temperatura interior`
- `Temperatura exterior`
- `Combustible`
- `Hora de salida`
- `Hora de llegada`
- `Duración del vuelo`

### `Destino.accdb`
Registra:
- `Destino`
- `Modo` (Cabecera / Despegue)
- `Estado` (En tránsito / Finalizado)
- `Velocidad`
- `Altitud`
- `Tren de aterrizaje` (true/false)
- `Piloto automático` (true/false)
- `Slats` (true/false)
- `Flaps`
- `Fecha`

---

## 🛠 Tecnologías usadas

| Tecnología        | Uso principal                                |
|------------------|----------------------------------------------|
| Java 17          | Lógica principal del proyecto                 |
| Swing            | Interfaz gráfica con `JFrame`, `JPanel`, etc |
| UCanAccess       | Conexión JDBC a bases Access (`.accdb`)      |
| JTattoo (Aero)   | Look and feel personalizado                  |
| Maven            | Gestión de dependencias                      |

---

## 🚀 Instrucciones de ejecución

### 📌 Requisitos previos
- Java 17 instalado
- Eclipse con WindowBuilder
- Archivo `.accdb` de origen y destino en la ruta correcta
- Maven configurado con estas dependencias:

```xml
<dependency>
    <groupId>net.sf.ucanaccess</groupId>
    <artifactId>ucanaccess</artifactId>
    <version>5.0.1</version>
</dependency>
<dependency>
    <groupId>com.jtattoo</groupId>
    <artifactId>JTattoo</artifactId>
    <version>1.6.11</version>
</dependency>

👤 Autor
Munir El Moussaoui
📧 mounircorreos@gmail.com
