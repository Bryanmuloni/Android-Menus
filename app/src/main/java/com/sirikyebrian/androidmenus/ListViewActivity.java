package com.sirikyebrian.androidmenus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListViewActivity extends AppCompatActivity {
    private ListView mainListView;
    private ArrayAdapter<String> listAdapter;
    private ArrayList<String> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
//        Find the ListView Resource
        mainListView = findViewById(R.id.listView);

//        Create and populate list of planet names
        String[] planets = new String[]{"Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn",
                "Uranus", "Neptune"};
        planetList = new ArrayList<>();
        planetList.addAll(Arrays.asList(planets));


//      Create ArrayAdapter using  the planet list
        listAdapter = new ArrayAdapter<String>(this, R.layout.list_simple_row, planetList);

//      Set ArrayAdapter as the ListViews adapter
        mainListView.setAdapter(listAdapter);

        mainListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        mainListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.listview_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.listDelete:
                        deleteSelectedItem();
                        mode.finish();
                        return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });


    }

    private void deleteSelectedItem() {
//        Getting the checked items from the ListView
        SparseBooleanArray checkedItemPositions = mainListView.getCheckedItemPositions();
        int itemCount = mainListView.getCount();

        for (int i = itemCount - 1; i > 0; i--) {
            if (checkedItemPositions.get(i)) {
                listAdapter.remove(planetList.get(i));
            }
        }
        checkedItemPositions.clear();
        listAdapter.notifyDataSetChanged();
    }
}
