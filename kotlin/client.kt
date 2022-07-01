import java.net.Socket
import kotlin.concurrent.thread

fun main()
{
    val client = Socket("localhost", 5000)

    val clientOutStream = client.getOutputStream()
    val clientInStream = client.getInputStream()

    thread {
        while(true)
        {
            var nextByte = clientInStream.read()    // Loop que permite leer los datos ingresados al socket del cliente e imprimirlos en consola
            print(nextByte.toChar())
        }
    }

    thread {
        while(true)
        {
            var input = readLine()!!                // Loop que permite escribir datos en el socket del servidor e imprimirlos en consola
            val hola = input.toByteArray(Charsets.UTF_8) + '\n'.toByte()
            clientOutStream.write(hola)
        }
    }
}