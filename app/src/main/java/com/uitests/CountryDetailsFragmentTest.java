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
public class CountryDetailsFragmentTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCountryDetailsDisplayed() {
        FragmentScenario<CountryDetailsFragment> scenario = FragmentScenario.launchInContainer(CountryDetailsFragment.class);
        onView(withId(R.id.country_name)).check(matches(isDisplayed()));
        onView(withId(R.id.country_capital)).check(matches(isDisplayed()));
        onView(withId(R.id.country_population)).check(matches(isDisplayed()));
        onView(withId(R.id.country_flag)).check(matches(isDisplayed()));
        onView(withId(R.id.country_name)).check(matches(withText("United States")));
        onView(withId(R.id.country_capital)).check(matches(withText("Washington, D.C.")));
        onView(withId(R.id.country_population)).check(matches(withText("331 million")));
    }

    @Test
    public void testCountryDetailsUpdate() {
        FragmentScenario<CountryDetailsFragment> scenario = FragmentScenario.launchInContainer(CountryDetailsFragment.class);
        onView(withId(R.id.country_list)).perform(click());
        onView(withId(R.id.country_name)).check(matches(withText("Canada")));
        onView(withId(R.id.country_capital)).check(matches(withText("Ottawa")));
        onView(withId(R.id.country_population)).check(matches(withText("37 million")));
    }
}

