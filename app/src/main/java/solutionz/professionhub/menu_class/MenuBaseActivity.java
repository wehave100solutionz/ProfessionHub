package solutionz.professionhub.menu_class;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import solutionz.professionhub.adapter.worker_list_adapter.RecyclerAdapter;
import solutionz.professionhub.fragments.WorkerProfileFragments;
import solutionz.professionhub.R;
import solutionz.professionhub.model.Model_Worker_ListView;

/**
 * Created by asad on 12/24/2017.
 */

public class MenuBaseActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    List<Model_Worker_ListView> item;
    Context context;
    GoogleMap mMap;
    protected void setUpRecyclerView() {
        item = new ArrayList<>();
        item = Model_Worker_ListView.getData();

        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        adapter = new RecyclerAdapter(this, item);
        recyclerView.setAdapter(adapter);



        LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context, int spanCount)
        mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
        //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void setUpDrawer(Toolbar toolbar) {

        WorkerProfileFragments drawerFragment = (WorkerProfileFragments) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerFragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);


    }


    /*@Override
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

*/
    public void setToolBar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("PH");
        toolBar.setNavigationIcon(getResources().getDrawable(R.drawable.edit));
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    protected void setSearchView(Menu menu)
    {

        MenuItem search_item = menu.findItem(R.id.mi_search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.d("After", item + "");
                item.clear();
                for (Model_Worker_ListView itemPro : Model_Worker_ListView.getData()) {
                    if (itemPro.getProfession().toLowerCase().startsWith(newText.toLowerCase())) {
                        item.add(itemPro);
                    }
                }


                Log.d("Before", item.size() + "");

                adapter.notifyDataSetChanged();

                return false;
            }
        });

    }


}
