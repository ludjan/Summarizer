
fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Usage: KotlinProgram <input_directory_path> <output_directory_path>")
        return
    }
    WritingMimicStructure(args[0], args[1], SummaryService())
}

fun String.dropFromTailUntil(symbol: String): String {
    var i = this.length - 1
    var repr: String = this
    while (i >= 0) {
        if (repr[i].toString() == symbol) return repr
        repr = repr.dropLast(1)
        i--
    }
    return repr
}
