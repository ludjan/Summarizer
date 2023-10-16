import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset
import java.util.*

class SummaryService {
    fun summarise(toSummarise: String) =
        summarise(toSummarise.toByteArray(Charset.defaultCharset()))

    fun summarise(toSummarise: ByteArray) =
        toSummarise
            .toString(Charset.defaultCharset())
            .filterIndexed { index, _ -> index % 2 == 0 }

    fun summarizeFileContent(content: ByteArray): String {
        // Convert content to Base64 for sending to an external service (You may use other methods)
        val base64Content = Base64.getEncoder().encodeToString(content)

        // Replace this URL with the actual external service endpoint for summarization
        val serviceUrl = "http://localhost:54321/summarize"

        val url = URL(serviceUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true

        val writer = OutputStreamWriter(connection.outputStream)
        writer.write(base64Content)
        writer.flush()

        val responseCode = connection.responseCode
        if (responseCode == 200) {
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = reader.readText()
            reader.close()
            return response
        }
        return "Failed to summarize the content"
    }
}