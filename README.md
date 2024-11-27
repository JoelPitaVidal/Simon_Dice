# Simon Dice - Proyecto Kotlin

Este repositorio contiene una implementación del juego **Simon Dice** desarrollado en Kotlin utilizando Jetpack Compose y el patrón de arquitectura **MVVM** (Model-View-ViewModel). Además, incluye el uso de corutinas y el patrón de diseño **Observer** para manejar el estado de la aplicación de manera eficiente.

---

## **Estructura del Proyecto**

El proyecto está estructurado siguiendo los principios de **MVVM** para mantener la separación de responsabilidades, mejorar la testabilidad y facilitar la escalabilidad.

### **1. Modelo (Model)**
Se define la clase `Datos` para almacenar el estado del juego como un **singleton**, asegurando que exista una única instancia accesible en todo el programa:
- **Estado del juego:** Número de ronda, secuencia generada, secuencia introducida, y mensajes mostrados al usuario.
- Uso de `mutableStateOf` para que los cambios sean observables por la UI.

### **2. Vista (View)**
Se implementa una interfaz gráfica interactiva con Jetpack Compose:
- **Colores dinámicos:** Los botones cambian de color al ser seleccionados, gracias al uso de corutinas para manejar la animación.
- **Botón START:** Inicia el juego y actualiza dinámicamente la ronda.

### **3. Vista-Modelo (ViewModel)**
La clase `ViewModel` encapsula la lógica del juego y el estado observable:
- Controla las transiciones entre estados (`ESPERANDO`, `GENERANDO`, `JUGANDO`).
- Maneja eventos como la generación de la secuencia y la verificación de la entrada del usuario.
- Implementa el patrón **Observer** mediante el uso de `LiveData`.

---

## **Características Implementadas**

### **1. Arquitectura MVVM**
Las responsabilidades están claramente separadas:
- El **modelo** gestiona los datos del juego.
- La **vista** representa la interfaz gráfica y la interacción del usuario.
- El **ViewModel** actúa como intermediario, gestionando la lógica y comunicándose con la vista.

### **2. Corutinas**
- Las corutinas se utilizan para manejar tareas asincrónicas, como el cambio de colores de los botones al mostrar la secuencia generada.
- Facilitan la suspensión y reanudación de tareas sin bloquear el hilo principal.

### **3. Patrón Observer**
- Los cambios en el estado del juego se reflejan automáticamente en la UI gracias a `LiveData` y `mutableStateOf`.
- Esto asegura que la interfaz siempre esté sincronizada con los datos subyacentes.

## **Instalación y Ejecución**

1. Clonar este repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd SimonDice
