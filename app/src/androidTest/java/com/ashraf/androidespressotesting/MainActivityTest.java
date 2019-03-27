package com.ashraf.androidespressotesting;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    //valid authentication
    private String userMail = "rafiq.istvn@gmail.com";
    private String userPassword = "aV789##";


    @Test
    public void sigInUiTest()
    {
        // input user name and password on editText field
        Espresso.onView(withId(R.id.userEmailEt)).perform(typeText(userMail));
        Espresso.onView(withId(R.id.userPasswordEt)).perform(typeText(userPassword));

        //close the keyboard
        Espresso.closeSoftKeyboard();
        //perform button click
        Espresso.onView(withId(R.id.signInBtn)).perform(click());
        // match authentication
        Espresso.onView(withId(R.id.userEmailEt)).toString().toLowerCase().equals(withText(userMail).toString().toLowerCase());

    }
}