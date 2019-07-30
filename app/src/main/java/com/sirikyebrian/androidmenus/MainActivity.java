package com.sirikyebrian.androidmenus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private TextView myText;
    ActionMode mActionMode;
    private ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {

            getMenuInflater().inflate(R.menu.action_mode_context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.action_mode1) {
                Toast.makeText(MainActivity.this, "Action Mode 1 Clicked", Toast.LENGTH_SHORT).show();
                actionMode.finish();
                return true;
            } else if (itemId == R.id.action_mode2) {
                Toast.makeText(MainActivity.this, "Action Mode 2 Clicked", Toast.LENGTH_SHORT).show();
                actionMode.finish();
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            mActionMode = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = findViewById(R.id.myText);
        this.registerForContextMenu(myText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout the menu course app");
        intent.putExtra(Intent.EXTRA_EMAIL,"nsubugahassan@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT,"Intent Based Menu Items");

        menu.addIntentOptions(R.id.intent_group, 0, 0, this.getComponentName(), null, intent, 0, null);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.isChecked()) item.setChecked(false);
        else item.setChecked(true);

        int itemId = item.getItemId();
        if (itemId == R.id.action_settings) {
            Toast.makeText(this, getString(R.string.setting_toast_message), Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.action_profile) {
            Toast.makeText(this, getString(R.string.profile_toast_message), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.floating_context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.action_share:
                Toast.makeText(this, "Share Item Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_download:
                Toast.makeText(this, "Download Item Clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_preview:
                Toast.makeText(this, "Preview Item Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void showActionModeMenu(View view) {
        if (mActionMode != null) {
            return;
        }
        mActionMode = startSupportActionMode(callback);
    }

    public void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.item_1) {
            Toast.makeText(this, "Item 1 Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item_2) {
            Toast.makeText(this, "Item 2 Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.item_3) {
            Toast.makeText(this, "Item 3 Clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

    public void showListView(View view) {
        startActivity(new Intent(this, ListViewActivity.class));
    }
}
