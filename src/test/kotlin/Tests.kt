import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Tests {
    @Test
    fun testDropFromTailUntil() {
        println("this/is/test".dropFromTailUntil("/"))
        assert("this/is/test".dropFromTailUntil("/") == "this/is/")
    }

    @Test
    fun summariseTest() {
        val summaryService = SummaryService()
        assertEquals("Ti sasrn", summaryService.summarise("This is a string"))
    }
}