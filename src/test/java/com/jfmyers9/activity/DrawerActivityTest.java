package com.jfmyers9.activity;
import android.app.Activity;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;


import com.jfmyers9.R;
import com.jfmyers9.fragment.LagerHistoryFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

@RunWith(RobolectricTestRunner.class)
public class DrawerActivityTest {
    private static final int ID_HOME = 0x0102002c;

    private ActivityController<DrawerActivity> controller;
    private DrawerActivity activity;
    private DrawerLayout drawerLayout;

    @Mock private MenuItem menuItem;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        menuItem = mock(MenuItem.class);
        controller = Robolectric.buildActivity(DrawerActivity.class);
        activity = controller.create().get();
        drawerLayout = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
    }

    @Test
    public void testThatTheTitleIsCorrect() throws Exception {
        Activity activity = Robolectric.buildActivity(DrawerActivity.class).create().get();
        assertTrue(activity.getTitle().equals("Lager Logger"));
    }

    @Test
    public void clickingOnTheMenuIconOpensAndClosesTheDrawer() throws Exception {
        controller.start().resume();
        stub(menuItem.getItemId()).toReturn(ID_HOME);
        assertFalse(drawerLayout.isDrawerOpen(activity.findViewById(R.id.left_drawer)));
        activity.onOptionsItemSelected(menuItem);
        assertTrue(drawerLayout.isDrawerOpen(activity.findViewById(R.id.left_drawer)));
        activity.onOptionsItemSelected(menuItem);
        assertFalse(drawerLayout.isDrawerOpen(activity.findViewById(R.id.left_drawer)));
    }

    @Test
    public void onResumeItPopulatesTheContentFrameWithALagerHistoryFragment() throws Exception {
        controller.start().resume();
        assertTrue(activity.getSupportFragmentManager().findFragmentById(R.id.content_frame) instanceof LagerHistoryFragment);
    }
}
