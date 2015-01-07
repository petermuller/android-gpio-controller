package io.github.petermuller.raspberrypigpiocontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements ActionBar.TabListener {

    /**
     * Socket client for communication with the phone server.
     * Does not need multiple instances. It gets passed into
     * each different handler's class.
     */
    private SocketClient sc = null;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            Fragment frag;
            switch (position){
                case 0:
                    frag = new Login();
                    break;
                case 1:
                    frag = new Pins();
                    break;
                case 2:
                    frag = new Output();
                    break;
                case 3:
                    frag = new Input();
                    break;
                default:
                    frag = new Pins();
                    break;
            }
            return frag;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section0).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.tabbed_view, container, false);
        }
    }

    /**
     * Creates the 'Connection' tab
     */
    public static class Login extends Fragment{
        public Login() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.login_screen,
                    container, false);
        }
    }

    /**
     * Creates the 'Pin Direction' tab
     */
    public static class Pins extends Fragment{
        public Pins() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.pins,
                    container, false);
        }
    }

    /**
     * Creates the 'Output' tab
     */
    public static class Input extends Fragment{
        public Input() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.input_mon,
                    container, false);
        }
    }

    /**
     * Creates the 'Input Monitor' tab
     */
    public static class Output extends Fragment{
        public Output() {}

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            return inflater.inflate(R.layout.output_mon,
                    container, false);
        }
    }

    /**
     * Handles all user interactions within pins.xml
     * @param v The modified UI element (on/off switch)
     */
    public void pinHandler(View v){
        PinHandler p = new PinHandler(sc);
        Button b = (Button)v;
        String direction = (String)b.getText();
        int pin = 8; //invalid pin to start out. Should never hit this.
        if (v == findViewById(R.id.switch0)){
            pin = 0;
        } else if (v == findViewById(R.id.switch1)){
            pin = 1;
        } else if (v == findViewById(R.id.switch2)){
            pin = 2;
        } else if (v == findViewById(R.id.switch3)){
            pin = 3;
        } else if (v == findViewById(R.id.switch4)){
            pin = 4;
        } else if (v == findViewById(R.id.switch5)){
            pin = 5;
        } else if (v == findViewById(R.id.switch6)){
            pin = 6;
        } else if (v == findViewById(R.id.switch7)){
            pin = 7;
        }
        p.handlePins(pin,direction);
    }

    /**
     * Handles all input interactions
     * TODO figure out how to handle the input pins
     * @param v The modified UI element (if there is one)
     */
    public void inputHandler(View v){}

    /**
     * Handles all user interactions from output_mon.xml
     * @param v The modified UI element (SeekBar element)
     */
    public void outputHandler(View v){
        OutputHandler o = new OutputHandler(sc);
        SeekBar s = (SeekBar)v;
        int val = s.getProgress();
        int pin = 8; //invalid pin to start out. Should never hit this.
        if (v == findViewById(R.id.seek0)) {
            pin = 0;
        } else if (v == findViewById(R.id.seek1)){
            pin = 1;
        } else if (v == findViewById(R.id.seek2)){
            pin = 2;
        } else if (v == findViewById(R.id.seek3)){
            pin = 3;
        } else if (v == findViewById(R.id.seek4)){
            pin = 4;
        } else if (v == findViewById(R.id.seek5)){
            pin = 5;
        } else if (v == findViewById(R.id.seek6)){
            pin = 6;
        } else if (v == findViewById(R.id.seek7)){
            pin = 7;
        }
        o.handleOutput(pin,val);
    }

    /**
     * Handles all user interactions from output_mon.xml
     * Sets the SocketClient variable for using with other methods
     * @param v The modified UI element (submit button)
     */
    public void loginHandler(View v){
        String addr = ((EditText)findViewById(R.id.address)).getText().toString();
        int port = Integer.parseInt(((EditText) findViewById(R.id.port)).getText().toString());
        System.out.println(addr + " " + Integer.toString(port));
        try {
            sc = new SocketClient(addr, port);
        } catch (UnknownHostException e){
            //TODO handle UnknownHostException
        } catch (IOException e){
            //TODO handle IOException
        }
        //SocketClient sc is now set if no exceptions!
    }

}
