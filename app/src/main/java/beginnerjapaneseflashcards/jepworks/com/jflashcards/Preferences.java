package beginnerjapaneseflashcards.jepworks.com.jflashcards;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Preferences extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragment_preferences);
    }


}
