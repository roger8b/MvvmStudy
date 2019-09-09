package com.rms.mvvmstudy

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.rule.ActivityTestRule
import com.rms.mvvmstudy.imc.MainActivity

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private val height = "2"
    private val weight = "50"
    private val validResult = "12.5"

    @get:Rule
    var activityScenarioRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun imcCalculationFlowTest(){
        onView(withId(R.id.et_weight)).perform(typeText(weight), closeSoftKeyboard())
        onView(withId(R.id.et_height)).perform(typeText(height), closeSoftKeyboard())
        onView(withId(R.id.bt_imc_calc)).perform(click())

        onView(withId(R.id.tv_result)).check(matches(withText(validResult)))
    }
}