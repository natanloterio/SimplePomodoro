package me.loterio.mysimplepomodoro

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.*
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

//    @ClassRule
//    var sActivityTestRule: MainActivityTestRule = ChromeTabbedActivityTestRule()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(appContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent)
        val mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.wait(Until.hasObject(By.pkg("me.loterio.mysimplepomodoro").depth(0)), 5000);

        //mDevice.pressHome()
    }
}