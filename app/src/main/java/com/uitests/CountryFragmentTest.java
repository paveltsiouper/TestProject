package com.uitests;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

@RunWith(AndroidJUnit4.class)
@MediumTest
@Config(sdk = 28)
public class CountryFragmentTest {

    @Test
    public void testCountryListDisplayed() {
        FragmentScenario<CountryFragment> scenario = FragmentScenario.launchInContainer(CountryFragment.class);
        onView(withId(R.id.country_list)).check(matches(isDisplayed()));
        onView(withId(R.id.search_bar)).check(matches(isDisplayed()));
        onView(withText("United States")).check(matches(isDisplayed()));
        onView(withId(R.id.refresh_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testSearchFunctionality() {
        FragmentScenario<CountryFragment> scenario = FragmentScenario.launchInContainer(CountryFragment.class);
   onView(withId(R.id.search_bar)).perform(typeText("Canada"));
      onView(withText("Canada")).check(matches(isDisplayed()));
    }

    @Test
    public void testRefreshFunctionality() {
        FragmentScenario<CountryFragment> scenario = FragmentScenario.launchInContainer(CountryFragment.class);
        onView(withId(R.id.refresh_button)).perform(click());
        onView(withText("United States")).check(matches(isDisplayed()));
        onView(withText("Canada")).check(matches(isDisplayed()));
    }
}

