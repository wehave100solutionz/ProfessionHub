package solutionz.professionhub.activity;

import android.Manifest;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import solutionz.professionhub.R;
import solutionz.professionhub.gps.CurrentLocationService;
import solutionz.professionhub.menu_class.MenuBaseActivity;
import solutionz.professionhub.model.Model_Worker_ListView;

import static com.google.android.gms.R.id.toolbar;

public class MapsActivity extends MenuBaseActivity implements OnMapReadyCallback {

    Toolbar toolbar;
    private GoogleMap mMap;
    private final static int REQUEST_PERMISSION = 3;
    CurrentLocationService currentLocationService;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        toolbar = (Toolbar) findViewById(R.id.toolbar_map);
        setToolBar(toolbar);
        setUpDrawer(toolbar);

        EnableRuntimePermission();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {


        mMap = googleMap;
        currentLocationService = new CurrentLocationService(MapsActivity.this);

        Location newlocation = currentLocationService.getLocation(LocationManager.NETWORK_PROVIDER);

        if (newlocation != null) {
            double curlat = newlocation.getLatitude();
            double curlog = newlocation.getLongitude();

            Log.d("latitude", curlat + "  ----" + curlog);


            LatLng sydney = new LatLng(curlat, curlog);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Your   location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            LatLng coordinate = new LatLng(curlat, curlog);
            CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 10);
            mMap.animateCamera(yourLocation);

            CameraPosition cameraPosition = new CameraPosition.Builder()

                    .target(new LatLng(curlat, curlog))      // Sets the center of the map to location user

                    .zoom(15)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }


    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)) {
            Toast.makeText(MapsActivity.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                Manifest.permission.INTERNET)) {
            Toast.makeText(MapsActivity.this, "READ_EXTERNAL_STORAGE allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(MapsActivity.this, "WRITE_EXTERNAL_STORAGE allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        setSearchViewMap(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected void setSearchViewMap(Menu menu) {

        MenuItem search_item = menu.findItem(R.id.mi_search);
        SearchView searchView = (SearchView) search_item.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                List<Address> addressList = null;

                if (query != null || !query.equals("")) {
                    Log.d("q", query);
                    Geocoder geocoder = new Geocoder(MapsActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(query, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    Log.d("l", latLng + "");
                    String placeName = addressList.get(0).getCountryName();
                    mMap.addMarker(new MarkerOptions().position(latLng).title(placeName));

                     mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));

                    //CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(latLng,5);
                 //   mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));


                }

/*
                List<Address>addressList = null;
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    addressList = geocoder.getFromLocationName(query, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                        Address address =
                if (query != null || !query.equals("")) {
                    for (int i=0;i<addressList.size();i++)
                    {addressList.get(i);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title(query));
                        mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                    }
                }
*/


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

    }
}