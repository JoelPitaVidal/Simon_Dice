package com.example.simon_dice

import android.content.Context            // Importa la clase Context para interactuar con la aplicación.
import android.util.Log                   // Importa la clase Log para registrar mensajes en la consola.
import android.widget.Toast              // Importa la clase Toast para mostrar mensajes emergentes.
import androidx.lifecycle.MutableLiveData // Importa MutableLiveData para observar el estado del juego.
import androidx.lifecycle.ViewModel        // Importa ViewModel para gestionar la lógica de la UI.
import com.example.simon_dice.Datos.ronda          // Importa la variable ronda de la clase Datos.
import com.example.simon_dice.Datos.secuenciaIntroducida  // Importa la variable secuenciaIntroducida de la clase Datos.
import com.example.simon_dice.Datos.secuenciaGenerada   // Importa la variable secuenciaGenerada de la clase Datos.
import com.example.simon_dice.Datos.mensajeMostrado   // Importa la variable mensajeMostrado de la clase Datos.
import com.example.simon_dice.Estados.*   // Importa los estados del juego de la clase Estados.

// Clase ViewModel que gestiona la lógica del juego.
class ViewModel : ViewModel() {
    // Estado actual del juego, inicializado en ESPERANDO.
    var estadoActual: MutableLiveData<Estados> = MutableLiveData(ESPERANDO)

    // Función privada para aumentar el número de ronda.
    private fun subirDeRonda() {
        // Incrementa el valor de la ronda en 1.
        ronda.value += 1
        // Registra un mensaje indicando que la ronda ha aumentado.
        Log.i("cicloVida", "Ronda aumentada")
    }

    // Función para generar una nueva secuencia de números.
    fun generarSecuencia() {
        // Llama a la función subirDeRonda para aumentar la ronda.
        subirDeRonda()
        // Agrega un número aleatorio entre 0 y 3 a la secuencia generada.
        secuenciaGenerada.add((0..3).random())
        // Cambia el estado del juego a GENERANDO.
        generandoNumeros()
        // Registra la secuencia generada.
        Log.i("cicloVida", "Secuencia generada: $secuenciaGenerada")
    }

    // Función que se ejecuta cuando se pulsa un botón.
    fun click(buttonId: Int, context: Context) {
        // Agrega el ID del botón pulsado a la secuencia introducida.
        secuenciaIntroducida.add(buttonId)
        // Llama a la función comprobarSecuencia para verificar la secuencia.
        comprobarSecuencia(context)
    }

    // Función privada para verificar la secuencia introducida.
    private fun comprobarSecuencia(context: Context) {
        // Si la secuencia generada es igual a la introducida...
        if (secuenciaGenerada == secuenciaIntroducida){
            // Limpia la secuencia introducida.
            secuenciaIntroducida.clear()
            // Registra un mensaje indicando que la secuencia es correcta.
            Log.d("cicloVida", "Secuencia Correcta")
            // Establece el mensaje a mostrar.
            establecerTexto("Ronda " + ronda.value + " superada")
            // Muestra un mensaje emergente.
            Toast.makeText(context, mensajeMostrado, Toast.LENGTH_SHORT).show()
            // Cambia el estado del juego a ESPERANDO.
            juegoEnEspera()
        }
        // Si la parte inicial de la secuencia generada coincide con la introducida...
        else if (secuenciaGenerada.subList(0, secuenciaIntroducida.size) == secuenciaIntroducida){
            // Registra un mensaje indicando que la secuencia es correcta hasta el momento.
            Log.d("cicloVida", "Secuencia Correcta")
        }
        // Si la secuencia es incorrecta...
        else{
            // Limpia la secuencia introducida.
            secuenciaIntroducida.clear()
            // Limpia la secuencia generada.
            secuenciaGenerada.clear()
            // Reinicia la ronda a 0.
            ronda.value = 0
            // Registra un mensaje indicando que la secuencia es incorrecta.
            Log.d("cicloVida", "Secuencia Inorrecta")
            // Establece el mensaje a mostrar.
            establecerTexto("Ronda perdida :(")
            // Muestra un mensaje emergente.
            Toast.makeText(context, mensajeMostrado, Toast.LENGTH_SHORT).show()
            // Cambia el estado del juego a ESPERANDO.
            juegoEnEspera()
        }
    }

    // Función privada para establecer el mensaje a mostrar.
    private fun establecerTexto(text: String) {
        // Asigna el texto proporcionado a la variable mensajeMostrado.
        mensajeMostrado = text
        // Registra el mensaje que se mostrará.
        Log.d("cicloVida", "Mensaje mostrado: $mensajeMostrado")
    }

    // Función privada para cambiar el estado a GENERANDO.
    private fun generandoNumeros() {
        // Asigna el estado GENERANDO a la variable estadoActual.
        estadoActual.value = GENERANDO
        // Registra el cambio de estado.
        Log.d("cicloVida", "Estado actual: Generando")
    }

    // Función para cambiar el estado a JUGANDO.
    fun jugandoJuego() {
        // Asigna el estado JUGANDO a la variable estadoActual.
        estadoActual.value = JUGANDO
        // Registra el cambio de estado.
        Log.d("cicloVida", "Estado actual: Jugando")
    }

    // Función privada para cambiar el estado a ESPERANDO.
    private fun juegoEnEspera() {
        // Asigna el estado ESPERANDO a la variable estadoActual.
        estadoActual.value = ESPERANDO
        // Registra el cambio de estado.
        Log.d("cicloVida", "Estado actual: Esperando")
    }
    // Objeto companion para acceder a miembros estáticos de la clase.
    companion object
}