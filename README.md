# Simon Dice

¡Bienvenido a Simon Dice! Esta es una implementación del clásico juego de memoria desarrollado en Kotlin utilizando Jetpack Compose para la interfaz de usuario.

## Descripción del Proyecto

Simon Dice es un juego de memoria donde el jugador debe repetir una secuencia de colores generada aleatoriamente. Cada ronda incrementa la dificultad al agregar un nuevo color a la secuencia. Si el jugador comete un error, el juego se reinicia.

### Características principales
- Interfaz gráfica intuitiva y colorida.
- Gestión de estado utilizando `ViewModel`.
- Uso de `Jetpack Compose` para un diseño moderno y limpio.
- Secuencia generada aleatoriamente para mayor rejugabilidad.
- Feedback visual y de texto para las acciones del jugador.

## Tecnologías utilizadas
- **Kotlin**: Lenguaje principal del desarrollo.
- **Jetpack Compose**: Framework para la interfaz de usuario.
- **Android ViewModel**: Gestión del estado y lógica del juego.
- **Coroutines**: Manejo de tareas asíncronas.

## Estructura del código
- **`MainActivity`**: Configura la actividad principal y el tema del juego.
- **`ViewModel`**: Gestiona el estado del juego y la lógica de interacción.
- **`UI` y `Greeting`**: Componentes de la interfaz de usuario.
- **`Datos`**: Singleton para el almacenamiento de datos del juego.
- **`Colors`** y **`Estados`**: Enumeraciones para manejar los colores y los estados del juego.

## Cómo jugar
1. Presiona el botón **START** para comenzar.
2. Observa la secuencia de colores que se ilumina en la pantalla.
3. Reproduce la secuencia presionando los botones correspondientes.
4. Si aciertas, avanzarás a la siguiente ronda con una secuencia más larga.
5. Si fallas, el juego se reinicia desde la ronda 0.

## Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/JoelPitaVidal/Simon_Dice.git
