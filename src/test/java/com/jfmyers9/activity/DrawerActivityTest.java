package com.jfmyers9.activity;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class DrawerActivityTest {

    @Test
    public void testThatTheTitleIsCorrect() throws Exception {
        Activity activity = Robolectric.buildActivity(DrawerActivity.class).create().get();
        assertTrue(activity.getTitle().equals("Lager Logger"));
    }
}
