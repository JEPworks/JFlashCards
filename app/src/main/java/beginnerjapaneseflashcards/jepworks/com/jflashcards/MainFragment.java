package beginnerjapaneseflashcards.jepworks.com.jflashcards;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by James on 10/3/2014.
 */
public class MainFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_list,null);
        ListView startingList = (ListView) rootView.findViewById(R.id.lvcardSelection);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1
                ,android.R.id.text1,getResources().getStringArray(R.array.startingCardSelection));
        startingList.setAdapter(adapter);

        startingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), FlashCardActivity.class);
                intent.putExtra("flashCardSelection",i);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
