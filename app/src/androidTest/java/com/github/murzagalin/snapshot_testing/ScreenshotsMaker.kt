package com.github.murzagalin.snapshot_testing

import android.content.Context
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.murzagalin.snapshot_testing.composable.ScreenshotComposeActivity
import com.github.murzagalin.snapshot_testing.composable.composableTestCases
import com.github.murzagalin.snapshot_testing.views.ScreenshotActivity
import com.github.murzagalin.snapshot_testing.views.viewTestCases
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenshotsMaker {

    private val idlingResource = CountingIdlingResource("Screenshots resource")

    private val context by lazy {
        ApplicationProvider.getApplicationContext<Context>()
    }

    @Before
    fun setupIdlingResource() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun clearIdlingResource() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    private fun waitForIdle() = object : ViewAction {

        override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

        override fun getDescription() = "wait for idle"

        override fun perform(uiController: UiController, view: View?) {
            uiController.loopMainThreadUntilIdle()
        }
    }

    @Test
    fun screenshotViews() {
        viewTestCases.forEach { config ->
            val intent = ScreenshotActivity.newIntent(context, config)
            launchActivity<ScreenshotActivity>(intent).onActivity { activity ->
                idlingResource.increment()

                takeScreenshot(activity, activity.getViewRect()) { bitmap ->
                    storeScreenShot(activity, bitmap, "${config.id}.png")
                    idlingResource.decrement()
                }
            }
            onView(isRoot()).perform(waitForIdle())
        }
    }

    @Test
    fun screenshotComposables() {
        composableTestCases.forEach { config ->
            val intent = ScreenshotComposeActivity.newIntent(context, config)
            launchActivity<ScreenshotComposeActivity>(
                intent
            ).onActivity { activity ->
                idlingResource.increment()

                takeScreenshot(activity, activity.getViewRect()) { bitmap ->
                    storeScreenShot(activity, bitmap, "${config.id}.png")
                    idlingResource.decrement()
                }
            }
            onView(isRoot()).perform(waitForIdle())
        }
    }
}