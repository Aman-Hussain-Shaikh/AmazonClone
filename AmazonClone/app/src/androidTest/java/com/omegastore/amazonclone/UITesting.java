package com.omegastore.amazonclone;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UITesting {

    @Rule
    public ActivityScenarioRule<SplashScreen> mActivityScenarioRule =
            new ActivityScenarioRule<>(SplashScreen.class);

    @Test
    public void uITesting() {
        ViewInteraction materialRadioButton = onView(
allOf(withId(R.id.bottom_profile), withText("Profile"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
4),
isDisplayed()));
        materialRadioButton.perform(click());

        ViewInteraction materialRadioButton2 = onView(
allOf(withId(R.id.bottom_cart), withText("My Cart"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
3),
isDisplayed()));
        materialRadioButton2.perform(click());

        ViewInteraction materialRadioButton3 = onView(
allOf(withId(R.id.bottom_cart), withText("My Cart"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
3),
isDisplayed()));
        materialRadioButton3.perform(click());

        ViewInteraction materialRadioButton4 = onView(
allOf(withId(R.id.bottom_search), withText("Search"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
2),
isDisplayed()));
        materialRadioButton4.perform(click());

        ViewInteraction materialRadioButton5 = onView(
allOf(withId(R.id.bottom_addprod), withText("Add Product"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
1),
isDisplayed()));
        materialRadioButton5.perform(click());

        ViewInteraction materialRadioButton6 = onView(
allOf(withId(R.id.bottom_home), withText("Home"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
0),
isDisplayed()));
        materialRadioButton6.perform(click());

        ViewInteraction materialTextView = onView(
allOf(withId(R.id.viewAllProducts), withText("VIEW ALL"),
childAtPosition(
allOf(withId(R.id.home_layout),
childAtPosition(
withClassName(is("androidx.core.widget.NestedScrollView")),
0)),
6),
isDisplayed()));
        materialTextView.perform(click());

        ViewInteraction materialTextView2 = onView(
allOf(withId(R.id.viewAllProducts), withText("VIEW ALL"),
childAtPosition(
allOf(withId(R.id.home_layout),
childAtPosition(
withClassName(is("androidx.core.widget.NestedScrollView")),
0)),
6),
isDisplayed()));
        materialTextView2.perform(click());

        ViewInteraction appCompatImageView = onView(
allOf(withId(R.id.backHome),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
0),
isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction cardView = onView(
allOf(withId(R.id.shoes1),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.HorizontalScrollView")),
0),
0)));
        cardView.perform(scrollTo(), click());

        ViewInteraction appCompatButton = onView(
allOf(withId(R.id.order), withText("Add to Cart"),
childAtPosition(
childAtPosition(
withClassName(is("androidx.core.widget.NestedScrollView")),
0),
7),
isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction materialRadioButton7 = onView(
allOf(withId(R.id.bottom_cart), withText("My Cart"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
3),
isDisplayed()));
        materialRadioButton7.perform(click());

        ViewInteraction appCompatButton2 = onView(
allOf(withId(R.id.next_button), withText("BUY NOW"),
childAtPosition(
allOf(withId(R.id.llBottom),
childAtPosition(
withClassName(is("android.widget.RelativeLayout")),
3)),
0),
isDisplayed()));
        appCompatButton2.perform(click());

        pressBack();

        ViewInteraction materialRadioButton8 = onView(
allOf(withId(R.id.bottom_search), withText("Search"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
2),
isDisplayed()));
        materialRadioButton8.perform(click());

        ViewInteraction appCompatEditText = onView(
allOf(withId(R.id.searchEditText),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
1),
isDisplayed()));
        appCompatEditText.perform(replaceText("one"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
allOf(withId(R.id.searchEditText), withText("one"),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
1),
isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatButton3 = onView(
allOf(withId(R.id.searchBtn), withText("SEARCH"),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
2),
isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText3 = onView(
allOf(withId(R.id.searchEditText), withText("one"),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
1),
isDisplayed()));
        appCompatEditText3.perform(replaceText(""));

        ViewInteraction appCompatEditText4 = onView(
allOf(withId(R.id.searchEditText),
childAtPosition(
childAtPosition(
withId(R.id.viewtoolbar),
0),
1),
isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialRadioButton9 = onView(
allOf(withId(R.id.bottom_search), withText("Search"),
childAtPosition(
allOf(withId(R.id.radioGroup1),
childAtPosition(
withId(R.id.bottomNavBar),
0)),
2),
isDisplayed()));
        materialRadioButton9.perform(click());
        }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
