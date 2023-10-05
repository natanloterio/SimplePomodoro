package me.loterio.mysimplepomodoro

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    private lateinit var device: UiDevice
    private val LAUNCH_TIMEOUT: Long = 3000
    private val SAMPLE_PACKAGE = "me.loterio.mysimplepomodoro"

    @Before
    fun  setup() {
        // Arrange
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intent)
        // Wait for the app to appear
//        device.wait(Until.hasObject(By.pkg(SAMPLE_PACKAGE).depth(0)), LAUNCH_TIMEOUT)
        device.wait(Until.hasObject(By.clazz(MainActivity::class.java).depth(0)), LAUNCH_TIMEOUT)

    }

    @Test
    fun spinner_shows_25minutes() {
        // Act
        val selectedTimeTV: UiObject2? = device.wait(Until.findObject(By.textContains("Time Remaining")), LAUNCH_TIMEOUT)
        assertThat(selectedTimeTV, notNullValue())

        // Act
        val spinner: UiObject2? = device.wait(Until.findObject(By.text("Select Time")), LAUNCH_TIMEOUT)
        assertThat(spinner, notNullValue())

        spinner?.click()

        val spinnerOption: UiObject2? = device.wait(Until.findObject(By.text("25")), LAUNCH_TIMEOUT)
        assertThat(spinnerOption, notNullValue())

        spinnerOption?.click()

        val spinner35 = device.wait(Until.findObject(By.text("25")), LAUNCH_TIMEOUT)
        assertThat(spinner35, notNullValue())
    }


    @Test
    fun spinner_shows_55minutes() {
        // Arrange
        val selectedTimeTV: UiObject2? = device.wait(Until.findObject(By.textContains("Time Remaining")), LAUNCH_TIMEOUT)
        assertThat(selectedTimeTV, notNullValue())

        // Act
        val spinner: UiObject2? = device.wait(Until.findObject(By.text("Select Time")), LAUNCH_TIMEOUT)
        assertThat(spinner, notNullValue())

        spinner?.click()

        val spinnerOption: UiObject2? = device.wait(Until.findObject(By.text("55")), LAUNCH_TIMEOUT)
        assertThat(spinnerOption, notNullValue())

        spinnerOption?.click()

        assertThat(selectedTimeTV!!.text, `is`("55"))
    }

}