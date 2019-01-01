package com.example.sanderbeazar.sportinaalst
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.hamcrest.CoreMatchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class MainActivityTest{

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun FullSearchProcess_CorrectSportclubName() {
        //navigeer naar de zoekpagina
        onView(withId(R.id.action_search)).perform(click())

        //zoek sporten voor meisjes
        onView(withId(R.id.rb_meisje)).perform(click())

        // in Moorsel
        onView(withId(R.id.spinner_postcode)).perform(click())
        onData(anything()).atPosition(4).perform(click());

        //selecteer alle sporten
        onView(withId(R.id.spinner_sport)).perform(click())
        onData(anything()).atPosition(0).perform(click());

        //zoek
        onView(withId(R.id.btn_zoek)).perform(click())

        // open de badmintonclub
        onView(withId(R.id.lijst_fragment))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        onView(withId(R.id.tv_naamSportclub)).check(matches(withText("Aalsterse Badminton Club")))

    }


    @Test
    fun ShortProcess_CorrectSportclubName() {

        onView(withId(R.id.action_search)).perform(click())

        // doe een search zonder parameters
        onView(withId(R.id.btn_zoek)).perform(click())

        // open KSK Erembodegem
        onView(withId(R.id.lijst_fragment))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()));

      //  onView(withId(R.id.tv_naamSportclub)).check(matches(withText("Aalsterse Badminton Club")))
        onView(withId(R.id.tv_naamSportclub)).check(matches(withText("KSK Erembodegem")))
        onView(withId(R.id.tv_sport)).check(matches(withText("Voetbal")))
        onView(withId(R.id.tv_adres)).check(matches(withText("Gerstenstraat 12 9320 Erembodegem")))

    }

}

