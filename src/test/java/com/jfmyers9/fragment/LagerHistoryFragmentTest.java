package com.jfmyers9.fragment;

import android.content.Intent;
import android.view.MenuItem;

import com.jfmyers9.R;
import com.jfmyers9.activity.AddLagerActivity;
import com.jfmyers9.activity.DrawerActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class LagerHistoryFragmentTest {
    private ActivityController<DrawerActivity> controller;
    private DrawerActivity activity;
    private LagerHistoryFragment fragment;
    @Mock private MenuItem menuItem;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        menuItem = mock(MenuItem.class);

        controller = Robolectric.buildActivity(DrawerActivity.class);
        activity = controller.create().get();
        controller.start().resume();
        fragment = (LagerHistoryFragment) activity.getSupportFragmentManager().findFragmentById(R.id.content_frame);
    }

    @Test
    public void clickingTheAddEntryButtonOpensTheAddLagerActivity() throws Exception {
        stub(menuItem.getItemId()).toReturn(R.id.add_lager);
        fragment.onOptionsItemSelected(menuItem);
        Intent expected = new Intent(activity, AddLagerActivity.class);

        Intent next = shadowOf(activity).getNextStartedActivity();
        assertTrue(next.equals(expected));
    }
}
