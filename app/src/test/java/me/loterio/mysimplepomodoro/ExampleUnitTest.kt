package me.loterio.mysimplepomodoro

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.regex.Pattern

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {



    @Test
    fun addition_isCorrect() {
        val text = "Time Remaining: 0"
        val matcher = Pattern.compile("Time Remaining").matcher(text)
        assertEquals(matcher.find(), true)
    }

}