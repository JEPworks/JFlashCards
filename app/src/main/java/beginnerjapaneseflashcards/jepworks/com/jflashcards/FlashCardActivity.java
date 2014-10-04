package beginnerjapaneseflashcards.jepworks.com.jflashcards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FlashCardActivity extends Activity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    public static String SHUFFLE_CARDS = "pref_shuffle";
    public static String JAPANESE_FIRST = "pref_japFirst";

    ViewPager mViewPager;
    public int[] imageArray;
    private boolean isRomaji;
    private ArrayList<FlashCard> flashCardArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card);
        flashCardArray = new ArrayList<FlashCard>();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        Intent i = getIntent();

        if(i != null) {
            switch(i.getIntExtra("flashCardSelection",-1)) {
                case 0 :
                    flashCardArray = new ArrayList<FlashCard>();
                    for(int count = 0; count < getResources().getStringArray(R.array.hirigana_english).length ;count++) {
                        flashCardArray.add(new FlashCard(ImageArrays.hirigana[count],-1,getResources().getStringArray(R.array.hirigana_english)[count],""));
                    }
                    getActionBar().setTitle("Hirigana");
                    if(sharedPref.getBoolean(SHUFFLE_CARDS,true)) {
                        Collections.shuffle(flashCardArray);
                    }

                    isRomaji = false;
                break;
                case 1:
                    flashCardArray = new ArrayList<FlashCard>();
                    for(int count = 0; count < getResources().getStringArray(R.array.Quiz10_6VocabularyRomaji).length ;count++) {
                        flashCardArray.add(new FlashCard(-1,-1,getResources().getStringArray(R.array.Quiz10_6VocabularyEnglish)[count],getResources().getStringArray(R.array.Quiz10_6VocabularyRomaji)[count]));
                    }
                    getActionBar().setTitle("10/6 Vocabulary Quiz");
                    if(sharedPref.getBoolean(SHUFFLE_CARDS,true)) {
                        Collections.shuffle(flashCardArray);
                    }
                    isRomaji = true;

                break;
            }
        }
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public int getPagerPosition() {
        return mViewPager.getCurrentItem();
    }

    public FlashCard getFlashCard(int position) {
        return flashCardArray.get(position);
    }

    public boolean isRomaji() {return isRomaji;};

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
            Fragment fragment = new FlashCardFragment();
            ((FlashCardFragment)fragment).setPosition(position);
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return flashCardArray.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}