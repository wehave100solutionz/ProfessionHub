package solutionz.professionhub.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import solutionz.professionhub.menu_class.MenuBaseActivity;
import solutionz.professionhub.R;

public class ActivityShowWorkerList extends MenuBaseActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_worker_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolBar(toolbar);
        setUpRecyclerView();
        setUpDrawer(toolbar);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        setSearchView(menu);
//       setSearchViewMap(menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

        }

        Toast.makeText(this, "you clicked", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

}