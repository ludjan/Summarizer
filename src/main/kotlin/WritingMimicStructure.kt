import java.io.File
import java.nio.file.Files

class WritingMimicStructure(
    sourcePath: String,
    targetPath: String,
    val summaryService: SummaryService,

) {
    private var sourceDirectory = File(sourcePath)

    init {
        mimicStructureRecursive(sourceDirectory, targetPath)
    }

    fun mimicStructureRecursive(source: File, mimicRoot: String) {
        if (source.isDirectory) {
            println("This is a directory ${source.name}")
            source.listFiles()?.forEach { file ->
                val relativePath = source
                    .toPath()
                    .relativize(file.toPath())
                    .toString()
                mimicStructureRecursive(file, "$mimicRoot/$relativePath")
            }
        } else {
            mimicFile(source, mimicRoot.dropFromTailUntil("/"))
        }
    }

    fun mimicFile(source: File, mimicRoot: String) {
        if (source.isFile) {
            println("This is a file ${source.name}")
            // Read the content of the source file (Kotlin or Java code)
            val fileContent = Files.readAllBytes(source.toPath())

            // Send the content to an external service for summarization
            val summary = summaryService.summarise(fileContent)

            // Create a new file with the summary content in the mimic directory
            val mimicFile = File(mimicRoot, source.name)
            mimicFile.parentFile.mkdirs()
            mimicFile.writeText(summary)
        }
    }
}