
# Simon Dice - Proyecto Kotlin

Este proyecto consiste eb ek juego **Simon Dice** desarrollado en Kotlin utilizando Jetpack Compose y el patrón de arquitectura **MVVM** (Model-View-ViewModel). 

---

## **Estructura del Proyecto**

El proyecto está diseñado para seguir los principios del patrón **MVVM**, asegurando una clara separación de responsabilidades, vamos a separar todas las partes importantes.

### **1. Modelo (Model)**

El modelo es responsable de almacenar y gestionar el estado del juego. En este caso, se utiliza la clase `Datos` como un **singleton**, lo que asegura que exista una única instancia compartida en todo el programa. Esto permite centralizar los datos y sincronizarlos con la UI.

#### Ejemplo de Código
```kotlin
object Datos {
    var ronda = mutableStateOf(0) // Número de ronda actual (observable).
    val secuenciaGenerada = mutableListOf<Int>() // Secuencia generada por el sistema.
    val secuenciaIntroducida = mutableListOf<Int>() // Secuencia introducida por el jugador.
    var mensajeMostrado = "" // Mensaje a mostrar al usuario.
}
```

**Explicación:**

- **mutableStateOf**: Permite que los cambios en `ronda` sean observados automáticamente por la interfaz gráfica. Esto forma parte del patrón Observer.
- **Listas Mutable**: `secuenciaGenerada` y `secuenciaIntroducida` almacenan las combinaciones de colores/números para verificar si el usuario sigue correctamente la secuencia.
- **Singleton**: Al usar un objeto único, se garantiza que los datos sean accesibles de forma global y se mantengan sincronizados entre los diferentes componentes.

### **2. Vista (View)**

La vista utiliza Jetpack Compose para crear una interfaz de usuario moderna e interactiva. Incluye los siguientes elementos principales:

- **Botones de colores**: Cada botón representa un color del juego. Cambian de color al ser seleccionados o durante la reproducción de la secuencia generada.
- **Botón START**: Inicia el juego y muestra la ronda actual.
- **Estado reactivo**: La interfaz se actualiza automáticamente según el estado del juego.

#### Ejemplo de Código
```kotlin
Button(
    onClick = { myViewModel.click(Colors.RED.id, context) },
    modifier = Modifier
        .padding(10.dp)
        .size(150.dp, 100.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = redButtonColor.value,
        contentColor = Color.White
    )
) {
    Text(text = Colors.RED.nombre)
}
```

**Explicación:**

- **Interacción dinámica**: El botón detecta los clics del usuario y llama al método `click` del ViewModel.
- **Diseño adaptable**: El tamaño y los colores de los botones se ajustan dinámicamente con base en el estado actual del juego.
- **Reactividad**: Los colores de los botones cambian de acuerdo con los valores de `redButtonColor`, los cuales son modificados por el ViewModel mediante corutinas.

### **3. Vista-Modelo (ViewModel)**

El ViewModel es el intermediario entre la vista y el modelo.Maneja la lógica del juego, como la generación de la secuencia, la validación de la entrada del usuario, y los cambios de estado.

#### Ejemplo de Código
```kotlin
fun click(buttonId: Int, context: Context) {
    secuenciaIntroducida.add(buttonId) // Agrega el botón pulsado a la secuencia introducida.
    comprobarSecuencia(context) // Verifica si la secuencia introducida es correcta.
}

private fun comprobarSecuencia(context: Context) {
    if (secuenciaGenerada == secuenciaIntroducida) {
        secuenciaIntroducida.clear() // Limpia la secuencia introducida.
        establecerTexto("Ronda " + ronda.value + " superada") // Mensaje de éxito.
        Toast.makeText(context, mensajeMostrado, Toast.LENGTH_SHORT).show()
        juegoEnEspera() // Vuelve al estado inicial.
    } else if (secuenciaGenerada.subList(0, secuenciaIntroducida.size) == secuenciaIntroducida) {
        Log.d("cicloVida", "Secuencia Correcta hasta ahora")
    } else {
        reiniciarJuego(context)
    }
}
```

**Explicación:**

- **Validación dinámica**: El ViewModel compara la entrada del usuario con la secuencia generada para determinar si es correcta.
- **Manejo de errores**: Si la secuencia es incorrecta, se reinician los datos del juego y se informa al usuario mediante un Toast.
- **Estado observable**: Utiliza `MutableLiveData` para que los cambios en el estado del juego se reflejen automáticamente en la vista.

### **4. Estados del Juego**

El juego tiene tres estados principales representados mediante la clase `Estados`:

- **ESPERANDO**: La aplicación espera a que el usuario inicie una nueva ronda.
- **GENERANDO**: Se genera una nueva secuencia aleatoria.
- **JUGANDO**: El usuario debe reproducir la secuencia generada.

#### Ejemplo de Código
```kotlin
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean) {
    ESPERANDO(start_activo = true, boton_activo = false),
    GENERANDO(start_activo = false, boton_activo = false),
    JUGANDO(start_activo = false, boton_activo = true);
}
```

**Explicación:**

- Los estados controlan la interacción del usuario con la UI. Por ejemplo, en el estado `GENERANDO`, los botones están desactivados.
- Permiten mantener una lógica clara y predecible en el flujo del juego.

### **5. Corutinas para Animaciones**

Se utilizan corutinas para implementar animaciones fluidas al mostrar la secuencia generada. Esto evita bloquear el hilo principal mientras se realizan las animaciones.

#### Ejemplo de Código
```kotlin
suspend fun colorearSecuencia() {
    for (i in secuenciaGenerada) {
        delay(300)
        when (i) {
            Colors.RED.id -> {
                redButtonColor.value = Colors.RED.colorPressed
                delay(1000)
                redButtonColor.value = Colors.RED.color
            }
        }
    }
    myViewModel.jugandoJuego() // Cambia al estado "jugando" después de la animación.
}
```

**Explicación:**

- **Suspensión temporal**: Con `delay`, se pausa la ejecución para simular la animación.
- **Sin bloqueo**: El uso de corutinas asegura que la interfaz de usuario siga siendo responsiva mientras se ejecutan las animaciones.
- **Transiciones suaves**: Una vez finalizada la secuencia, el juego cambia automáticamente al estado `JUGANDO`.
