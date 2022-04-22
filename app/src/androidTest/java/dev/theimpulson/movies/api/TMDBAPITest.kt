package dev.theimpulson.movies.api

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.gson.Gson
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dev.theimpulson.movies.MainActivity
import dev.theimpulson.movies.R
import dev.theimpulson.movies.api.data.ShowsData
import dev.theimpulson.movies.topshows.adapter.TopShowsRVAdapter
import dev.theimpulson.movies.utils.CommonUtils
import dev.theimpulson.movies.utils.CountingIdlingResourceSingleton
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TMDBAPITest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var mockWebServer: MockWebServer
    private lateinit var mockResponse: ShowsData

    @Before
    fun setup() {
        hiltAndroidRule.inject()
        val mockResponseString = CommonUtils.readStringFromFile("success_response.json")

        // Initial setup for MockWebServer
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(mockResponseString)
            }
        }

        mockResponse = Gson().fromJson(mockResponseString, ShowsData::class.java)

        IdlingRegistry.getInstance()
            .register(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance()
            .unregister(CountingIdlingResourceSingleton.countingIdlingResource)
    }

    @Test
    fun testSuccessResponseData() {
        mockResponse.results.forEachIndexed { index, show ->
            onView(withId(R.id.topShowsRV))
                .check(matches(isDisplayed()))
                .perform(actionOnItemAtPosition<TopShowsRVAdapter.ViewHolder>(index, click()))
            onView(withId(R.id.showTitleTV)).check(matches(withText(show.name)))
            onView(withId(R.id.showDescTV)).check(matches(withText(show.overview)))
            pressBack()
        }
    }
}
