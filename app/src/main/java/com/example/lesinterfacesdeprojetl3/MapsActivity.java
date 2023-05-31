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
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private FirebaseFirestore db;

    private SearchView searchView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<City> cityList;

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


        db = FirebaseFirestore.getInstance();


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        searchView = findViewById(R.id.localisation);
        listView = findViewById(R.id.listView);
        cityList = new ArrayList<>();

        // Ajouter les villes avec leurs coordonnées géographiques
        cityList.add(new City("Paris", 48.8566, 2.3522));

        cityList.add(new City("Alger", 36.7538, 3.0588));
        cityList.add(new City("Madrid", 40.4168, -3.7038));
        cityList.add(new City("Adrar", 27.8743, -0.2856));
        cityList.add(new City("Chlef", 36.1667, 1.3333));
        cityList.add(new City("Laghouat", 33.8001, 2.8807));
        cityList.add(new City("Oum El Bouaghi", 35.8722, 7.1135));
        cityList.add(new City("Batna", 35.5559, 6.1736));
        cityList.add(new City("Béjaïa", 36.7515, 5.0553));
        cityList.add(new City("Biskra", 34.8501, 5.7280));
        cityList.add(new City("Béchar", 31.6111, -2.2247));
        cityList.add(new City("Blida", 36.4699, 2.8282));
        cityList.add(new City("Bouira", 36.3803, 3.8961));
        cityList.add(new City("Tamanrasset", 22.7850, 5.5228));
        cityList.add(new City("Tébessa", 35.4042, 8.1243));
        cityList.add(new City("Tlemcen", 34.8783, -1.3150));
        cityList.add(new City("Tiaret", 35.3764, 1.3179));
        cityList.add(new City("Tizi Ouzou", 36.7110, 4.0452));
        cityList.add(new City("Alger", 36.7538, 3.0588));
        cityList.add(new City("Djelfa", 34.6742, 3.2508));
        cityList.add(new City("Jijel", 36.8028, 5.7534));
        cityList.add(new City("Sétif", 36.1891, 5.4043));
        cityList.add(new City("Saïda", 34.8408, 0.1457));
        cityList.add(new City("Skikda", 36.8663, 6.9094));
        cityList.add(new City("Sidi Bel Abbès", 35.1947, -0.6508));
        cityList.add(new City("Annaba", 36.9028, 7.7642));
        cityList.add(new City("Guelma", 36.4607, 7.4326));
        cityList.add(new City("Constantine", 36.3650, 6.6147));
        cityList.add(new City("Médéa", 36.2679, 2.7536));
        cityList.add(new City("Mostaganem", 35.9374, 0.0892));
        cityList.add(new City("M'Sila", 35.7050, 4.5417));
        cityList.add(new City("Mascara", 35.3912, 0.1401));
        cityList.add(new City("Ouargla", 31.9497, 5.3230));
        cityList.add(new City("Oran", 35.6911, -0.6417));
        cityList.add(new City("El Bayadh", 33.6936, 1.0138));
        cityList.add(new City("Illizi", 26.4936, 8.4748));



                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getCityNames());
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchLocation(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Implémenter une recherche en temps réel ici si nécessaire
                return false;
            }
        });
















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
        db.collection("parkings")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Récupère les données de la ville
                            String nom = document.getString("Name");
                            GeoPoint localisation = document.getGeoPoint("Localisation");
                            if (localisation != null) {
                                double lat = localisation.getLatitude();
                                double lng = localisation.getLongitude();
                                // Ajoute un marqueur sur la carte pour chaque ville
                                LatLng parkLatLng = new LatLng(lat, lng);
                                mMap.addMarker(new MarkerOptions().position(parkLatLng).title(nom));
                            }
                        }
                    } else {
                        Log.d("MainActivity", "Erreur : ", task.getException());
                    }
                });



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Check if the clicked marker is the user's location marker
                if (marker.getPosition().equals(UserLATlng)) {
                    // Show a toast message with "votre localisation"
                    Toast.makeText(MapsActivity.this, "votre localisation", Toast.LENGTH_SHORT).show();
                    return true;
                } else {
                    String nom = marker.getTitle();
                    Query query = db.collection("parkings").whereEqualTo("Name", nom).limit(1);

                    query.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot snapshot = task.getResult();
                            if (snapshot != null && !snapshot.isEmpty()) {
                                DocumentSnapshot document = snapshot.getDocuments().get(0);
                                Intent intent = new Intent(MapsActivity.this, Reserveeer.class);
                                intent.putExtra("Name", document.getString("Name"));
                                intent.putExtra("Wilaya", document.getString("Wilaya"));
                                intent.putExtra("Nombre de places", document.getLong("Nombre de places"));

                                intent.putExtra("Tarif", document.getLong("Tarif"));
                                intent.putExtra("Hraire d'ouverture", document.getString("Hraire d'ouverture"));
                                intent.putExtra("Heure de ferneture", document.getString("Heure de ferneture"));


                                startActivity(intent);
                            }
                        } else {
                            Log.d("MainActivity", "Erreur : ", task.getException());
                        }
                    });
                    return false;
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






    private void searchLocation(String locationName) {
        for (City city : cityList) {
            if (city.getName().equalsIgnoreCase(locationName)) {
                LatLng cityLatLng = new LatLng(city.getLatitude(), city.getLongitude());

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cityLatLng, 12));
                break;
            }
        }
    }

    private List<String> getCityNames() {
        List<String> cityNames = new ArrayList<>();
        for (City city : cityList) {
            cityNames.add(city.getName());
        }
        return cityNames;
    }

    private static class City {
        private String name;
        private double latitude;
        private double longitude;

        public City(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
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