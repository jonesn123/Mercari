package com.example.simhyunyong.minimercaru

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.v7.widget.RecyclerView
import com.example.simhyunyong.minimercaru.view.MainActivity
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    lateinit var context: Context

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun useAppContext() {
        assertEquals("com.example.simhyunyong.minimercaru", context.packageName)
    }

    @Test
    fun testMainActivity() {

        waitForUIThread(5000)
        // test click tab
        // check TabLayout title
        onView(allOf(withId(R.id.tabs))).check(matches(isDisplayed()))

        clickTab("MEN")
        clickTab("WOMEN")
        clickTab("BABY")
        clickTab("ELECTRIC")
        clickTab("SHOES")
        clickTab("CLOTHES")
        clickTab("CARS")
        clickTab("FURNITURE")
        clickTab("HOUSE")
        clickTab("COMPUTER")
        clickTab("ETC")

        // click floating button
        onView(allOf(withId(R.id.fab))).perform(click())
        onView(withText(R.string.snackbar_message)).check(matches(isDisplayed()))
    }

    private fun clickTab(tabName: String) {
        val tab = onView(allOf(withText(tabName), isDescendantOfA((withId(R.id.tabs)))))
        tab.perform(scrollTo()).check(matches(isDisplayed()))
        tab.perform(click())
        waitForUIThread(1000)
    }

    private fun waitForUIThread(millis: Int) {
        try {
            Thread.sleep(millis.toLong())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}
