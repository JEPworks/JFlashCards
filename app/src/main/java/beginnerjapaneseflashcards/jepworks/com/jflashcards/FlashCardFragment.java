package beginnerjapaneseflashcards.jepworks.com.jflashcards;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by James on 10/1/2014.
 */
public class FlashCardFragment extends Fragment {
    boolean japaneseShown;
    int position;


    public FlashCardFragment() {
        japaneseShown = false;
    }

    public void setPosition(int position) {
       this.position = position;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final View rootView = (View) inflater.inflate(R.layout.fragment_flashcard,null);
        TextView englishText = (TextView) rootView.findViewById(R.id.englishText);
        TextView japaneseCharacter = (TextView) rootView.findViewById(R.id.japaneseCharacter);
        TextView japaneseRomaji = (TextView) rootView.findViewById(R.id.romajiText);
        TextView romajiEnglishSide = (TextView) rootView.findViewById(R.id.romajiTextEnglish);

        englishText.setText(getParentActivity().getFlashCard(position).getEnglish());
        japaneseRomaji.setText(getParentActivity().getFlashCard(position).getRomaji());
        japaneseCharacter.setText(getParentActivity().getFlashCard(position).getHirigana());
        romajiEnglishSide.setText(getParentActivity().getFlashCard(position).getRomaji());

        if(!getParentActivity().isShowRomaji()) {
            japaneseRomaji.setVisibility(View.GONE);
        }

        if(!getParentActivity().isRomajiOnEnglishSide()) {
            romajiEnglishSide.setVisibility(View.GONE);
        }

        if(sharedPref.getBoolean(FlashCardActivity.JAPANESE_FIRST,false)) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flipCardReverse(rootView);
                }
            });
            View englishTextContainer = (View) rootView.findViewById(R.id.englishTextContainer);
            englishTextContainer.setVisibility(View.GONE);
            View japaneseCharacterContainer = (View) rootView.findViewById(R.id.japaneseCharacterContainer);
            japaneseCharacterContainer.setVisibility(View.VISIBLE);
        } else {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flipCard(rootView);
                }
            });
        }
        return rootView;
    }

    private void flipCard(View container) {

        View englishTextContainer = (View) container.findViewById(R.id.englishTextContainer);

        View japaneseCharacterContainer = (View) container.findViewById(R.id.japaneseCharacterContainer);

        FlipAnimation flipAnimation = new FlipAnimation(englishTextContainer, japaneseCharacterContainer);

        if(englishTextContainer.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        container.startAnimation(flipAnimation);
    }

    private void flipCardReverse(View container) {

        View englishTextContainer = (View) container.findViewById(R.id.englishTextContainer);

        View japaneseCharacterContainer = (View) container.findViewById(R.id.japaneseCharacterContainer);

        FlipAnimation flipAnimation = new FlipAnimation(japaneseCharacterContainer, englishTextContainer);

        if(japaneseCharacterContainer.getVisibility() == View.GONE) {
            flipAnimation.reverse();
        }
        container.startAnimation(flipAnimation);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public FlashCardActivity getParentActivity() {
        return ((FlashCardActivity)getActivity());
    }
}
