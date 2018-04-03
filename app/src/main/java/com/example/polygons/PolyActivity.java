package com.example.polygons;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import java.util.Arrays;
import java.util.List;
import static com.example.polygons.R.id.map;





/**
 * An activity that displays a Google map with polylines to represent paths or routes,
 * and polygons to represent areas.
 */
public class PolyActivity extends AppCompatActivity
        implements
        OnMapReadyCallback,
        GoogleMap.OnPolylineClickListener {

    // Variables for the map
    private static final int COLOR_BLACK_ARGB = 0xff000000;
    private static final int COLOR_GREEN_ARGB = 0xff388E3C;
    private static final int POLYLINE_STROKE_WIDTH_PX = 12;
    private static final int POLYGON_STROKE_WIDTH_PX = 8;
    private static final int PATTERN_DASH_LENGTH_PX = 20;
    private static final int PATTERN_GAP_LENGTH_PX = 20;
    private static final PatternItem DOT = new Dot();
    private static final PatternItem DASH = new Dash(PATTERN_DASH_LENGTH_PX);
    private static final PatternItem GAP = new Gap(PATTERN_GAP_LENGTH_PX);
    private static final List<PatternItem> PATTERN_POLYLINE_DOTTED = Arrays.asList(GAP, DOT);
    private static final List<PatternItem> PATTERN_POLYGON_ALPHA = Arrays.asList(GAP, DASH);

    // Variables for the GPS
    private LocationManager locationManager;
    private LocationListener listener;
    private double Latitude;
    private double Longitude;
    private double EndLatitude;
    private double EndLongitude;
    private String IDreceived;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        //-------------------------------------------------------------------------
        //Retrieve the destination location
        Intent intent = getIntent();
        IDreceived = intent.getStringExtra((Selector.DestinationID));
        Toast.makeText(getApplicationContext(),  IDreceived ,Toast.LENGTH_SHORT).show();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Latitude = location.getLatitude();
                Longitude = location.getLongitude();
                Toast.makeText(getApplicationContext(),  location.getLongitude() + " " + location.getLatitude() ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        configureLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configureLocation();
                break;
            default:
                break;
        }
    }

    void configureLocation(){
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
        }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * In this tutorial, we add polylines and polygons to represent routes and areas on the map.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if(IDreceived.equals("Edificio A")){
            EndLatitude = 14.604637;
            EndLongitude = -90.488796;
        }
        if(IDreceived.equals("Edificio B")){
            EndLatitude = 14.604661;
            EndLongitude = -90.489982;
        }
        if(IDreceived.equals("Edificio C")){
            EndLatitude = 14.605036;
            EndLongitude = -90.489929;
        }
        if(IDreceived.equals("Edificio H")){
            EndLatitude = 14.605279;
            EndLongitude = -90.488834;
        }
        if(IDreceived.equals("Edificio J")){
            EndLatitude = 14.605419;
            EndLongitude = -90.488107;
        }
        if(IDreceived.equals("Cancha Polideportiva")){
            EndLatitude = 14.605089;
            EndLongitude = -90.489544;
        }
        if(IDreceived.equals("Plaza Paiz Riera")){
            EndLatitude = 14.604683;
            EndLongitude = -90.489235;
        }
        if(IDreceived.equals("Auditorio")){
            EndLatitude = 14.604890;
            EndLongitude = -90.4489088;
        }


        // Add polylines to the map.
        // Polylines are useful to show a route or some other connection between points.
        Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(EndLatitude, EndLongitude),
                        new LatLng(Latitude, Longitude),
                        new LatLng(14.604109, -90.488787)));
        // Store a data object with the polyline, used here to indicate an arbitrary type.
        polyline1.setTag("A");
        // Style the polyline.
        stylePolyline(polyline1);


        //Marks the surrounding area of the university.
        // Add polygons to indicate areas on the map.
        Polygon polygon1 = googleMap.addPolygon(new PolygonOptions()
                .clickable(true)
                .add(
                        new LatLng(14.604966, -90.491533),
                        new LatLng(14.603660, -90.489302),
                        new LatLng(14.605561, -90.487607),
                        new LatLng(14.605705, -90.489792)));
        // Store a data object with the polygon, used here to indicate an arbitrary type.
        polygon1.setTag("alpha");
        // Style the polygon.
        stylePolygon(polygon1);

        // Position the map's camera near Alice Springs in the center of the university,
        // and set the zoom factor so most of Australia shows on the screen.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(14.60496, -90.49153), 18));

        // Set listeners for click events.
        googleMap.setOnPolylineClickListener(this);

    }

    /**
     * Styles the polyline, based on type.
     * @param polyline The polyline object that needs styling.
     */
    private void stylePolyline(Polyline polyline) {
        String type = "";
        // Get the data object stored with the polyline.
        if (polyline.getTag() != null) {
            type = polyline.getTag().toString();
        }

        switch (type) {
            // If no type is given, allow the API to use the default.
            case "A":
                // Use a custom bitmap as the cap at the start of the line.
                polyline.setStartCap(
                        new CustomCap(
                                BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10));
                break;
            case "B":
                // Use a round cap at the start of the line.
                polyline.setStartCap(new RoundCap());
                break;
        }

        polyline.setEndCap(new RoundCap());
        polyline.setWidth(POLYLINE_STROKE_WIDTH_PX);
        polyline.setColor(COLOR_BLACK_ARGB);
        polyline.setJointType(JointType.ROUND);
    }

    /**
     * Styles the polygon, based on type.
     * @param polygon The polygon object that needs styling.
     */
    private void stylePolygon(Polygon polygon) {
        polygon.setStrokePattern(PATTERN_POLYGON_ALPHA);
        polygon.setStrokeWidth(POLYGON_STROKE_WIDTH_PX);
        polygon.setStrokeColor(COLOR_GREEN_ARGB);
    }

    /**
     * Listens for clicks on a polyline.
     * @param polyline The polyline object that the user has clicked.
     */
    @Override
    public void onPolylineClick(Polyline polyline) {
        // Flip from solid stroke to dotted stroke pattern.
        if ((polyline.getPattern() == null) || (!polyline.getPattern().contains(DOT))) {
            polyline.setPattern(PATTERN_POLYLINE_DOTTED);
        } else {
            // The default pattern is a solid stroke.
            polyline.setPattern(null);
        }
    }


}


