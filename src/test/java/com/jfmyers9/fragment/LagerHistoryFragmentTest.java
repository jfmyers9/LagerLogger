package com.jfmyers9.fragment;

import com.jfmyers9.R;
import com.jfmyers9.activity.DrawerActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class LagerHistoryFragmentTest {
    private ActivityController<DrawerActivity> controller;
    private DrawerActivity activity;
    private LagerHistoryFragment fragment;

    @Before
    public void setup() throws Exception {
        controller = Robolectric.buildActivity(DrawerActivity.class);
        activity = controller.create().get();
        controller.start().resume();
        fragment = (LagerHistoryFragment) activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    @Test
    public void testNothingPlaceholder() throws Exception {
        assertTrue(true);
    }
}