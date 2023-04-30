package com.example.lesinterfacesdeprojetl3;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.lesinterfacesdeprojetl3.databinding.ActivityMapsBinding;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    public class LocationInfo {
        private double latitude;
        private double longitude;
        private String name;
        private String wilaya;
        private int nmbrplc;
        public LocationInfo(double latitude, double longitude, String name, String wilaya ,int nmbrplc) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = name;
            this.wilaya = wilaya;
            this.nmbrplc = nmbrplc;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getName() {
            return name;
        }

        public String getWilaya() {
            return wilaya;
        }
        public int getnmbrplc() {
            return nmbrplc;
        }
    }















    LocationManager locationManager;
    LocationListener locationListener;
    LatLng UserLATlng;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final LinearLayout layoutticket = findViewById(R.id.layoutticket);
        final LinearLayout layoutmenu = findViewById(R.id.layoutmenu);

        layoutmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intnt = new Intent(MapsActivity.this, MainActivity.class);
                startActivity(intnt);
            }
        });


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        List<LocationInfo> locations = new ArrayList<>();
        locations.add(new LocationInfo(35.638209, -0.589343, "centre comercial senia", "oran",12));
        locations.add(new LocationInfo(35.700602, -0.647636, "parking saida", "oran",23));
        locations.add(new LocationInfo(35.703801, -0.640537, "parking beliala", "oran",03));


        for (LocationInfo location : locations) {
           // BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.pin3);w bah ndirha ndir icon(icon) thta
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(location.getName())
                    .snippet(location.getWilaya()));



            
        }




        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Check if the clicked marker is the user's location marker
                if (marker.getPosition().equals(UserLATlng)) {
                    // Show a toast message with "votre localisation"
                    Toast.makeText(MapsActivity.this, "votre localisation", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    // Launch a new activity to display the location details
                    Intent intent = new Intent(MapsActivity.this, Reserveeer.class);
                    // Pass the necessary data as extras in the intent
                    intent.putExtra("location_name", marker.getTitle());


                    startActivity(intent);
                    return true;
                }
            }
        });













        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location l) {


                mMap.addMarker(new MarkerOptions().position(UserLATlng).title("your location"));




            }
            @Override
            public void onStatusChanged(@NonNull String provider, int status, Bundle extras){

            }
            @Override
            public void onProviderDisabled(@NonNull String provider) {
                // handle location provider being disabled
            }

            @Override
            public void onProviderEnabled(@NonNull String provider){

        }
        };

        asKLocationpermition();
        final LinearLayout layouthome = findViewById(R.id.layouthome);

        layouthome.setOnClickListener(new View.OnClickListener() {
            @Override




            public void onClick(View v) {




    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(UserLATlng, 15));
    mMap.addMarker(new MarkerOptions().position(UserLATlng).title("your location"));





            }




        });

    }

    private void asKLocationpermition() {
        Dexter.withActivity(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location lastlocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);

                if (lastlocation == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Toast.makeText(MapsActivity.this, "activer votre localisation", Toast.LENGTH_SHORT).show();
                }
                    if (lastlocation != null){
                        UserLATlng = new LatLng(lastlocation.getLatitude(), lastlocation.getLongitude());

                        mMap.addMarker(new MarkerOptions().position(UserLATlng).title("your location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UserLATlng,14 ));

                    }



            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();

    }






























}