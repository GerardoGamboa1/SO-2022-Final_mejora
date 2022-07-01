import java.net.ServerSocket
import kotlin.concurrent.thread


fun main()
{
    val server = ServerSocket(5000)

    val client = server.accept()

    val clientOutStream = client.getOutputStream()
    val clientInStream = client.getInputStream()

    thread {
        while(true)
        {
            var nextByte = clientInStream.read()
            print(nextByte.toChar())
        }
    }                                               // La ejecución del mismo código permite la conexión (aunque no ideal) de ambos programas

    thread {
        while(true)
        {
            var input = readLine()!!
            val hola = input.toByteArray(Charsets.UTF_8) + '\n'.toByte()
            clientOutStream.write(hola)
        }
    }
}