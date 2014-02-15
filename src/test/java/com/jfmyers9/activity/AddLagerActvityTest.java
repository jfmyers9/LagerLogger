package com.jfmyers9.activity;

import android.content.Intent;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.os.Environment;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import com.jfmyers9.LagerDatabaseHelper;
import com.jfmyers9.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowEnvironment;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.tester.android.view.TestMenuItem;
import org.robolectric.util.ActivityController;

import roboguice.RoboGuice;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class AddLagerActvityTest {
    private AddLagerActivity activity;
    private ActivityController<AddLagerActivity> controller;
    @Mock LagerDatabaseHelper fakeLagerDatabaseHelper;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        RoboGuice.setBaseApplicationInjector(Robolectric.application,
                RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(new TestModule()));
        controller = Robolectric.buildActivity(AddLagerActivity.class);
        activity = controller.create().start().get();
        ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);
    }

    @Test
    public void testThatClickingTheCameraMenuItemLaunchesTheCamera() throws Exception {
        MenuItem item = new TestMenuItem(R.id.add_picture_button);
        activity.onOptionsItemSelected(item);

        ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = Robolectric.shadowOf(startedIntent);

        assertThat(shadowIntent.getAction(), equalTo(MediaStore.ACTION_IMAGE_CAPTURE));
    }

    @Test
    public void testThatClickingTheHomeButtonFinishesTheActivityWithoutSaving() throws Exception {
        MenuItem item = new TestMenuItem(android.R.id.home);
        activity.onOptionsItemSelected(item);
        assertTrue(activity.isFinishing());
    }

    @Test
    public void testThatClickingTheCancelButtonFinishesTheActivityWithoutSaving() throws Exception {
        MenuItem item = new TestMenuItem(R.id.cancel_button);
        activity.onOptionsItemSelected(item);
        assertTrue(activity.isFinishing());
    }

    @Test
    public void testThatClickingTheSaveButtonFinishesTheActivityWithSaving() throws Exception {
        MenuItem item = new TestMenuItem(R.id.save_button);
        activity.onOptionsItemSelected(item);
        verify(fakeLagerDatabaseHelper).addLagerEntry("", "", "", "", "0", "");
    }

    /* Private Methods and classes */
    private class TestModule implements Module {
        @Override
        public void configure(Binder binder) {
            binder.bind(LagerDatabaseHelper.class).toInstance(fakeLagerDatabaseHelper);
        }
    }
}
