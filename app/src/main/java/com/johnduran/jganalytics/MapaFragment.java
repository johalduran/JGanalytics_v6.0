package com.johnduran.jganalytics;


import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment implements OnMapReadyCallback{
    MapView mMapView;
    private GoogleMap mMap;
    private  LatLng Medellin = new LatLng(6.164734 , -75.607817);
    private  LatLng Monteria = new LatLng(8.746468 , -75.888712);
    private  LatLng Cartagena = new LatLng(10.379225 , -75.462337);
    private  LatLng Bogota = new LatLng(4.769201 , -74.177177);
    private  LatLng Cali = new LatLng(3.446076 , -76.518524);
    private  LatLng Bucaramanga = new LatLng(7.119847 , -73.122767);
    private  LatLng Pereira = new LatLng(4.814033 , -75.707721);
    private  LatLng Barranquilla = new LatLng(11.009871 , -74.791735);
    private  LatLng Uraba = new LatLng(7.878588 , -76.630864);







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View mView= inflater.inflate(R.layout.fragment_mapa, container, false);
        mMapView= (MapView) mView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        return mView;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        // Add a marker in Sydney and move the camera

        //Se visita la página www.latlong.net
        //Segunda opción, ir a google maps
        ubicaciones();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Medellin, 5));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL); // Muestra el mapa a color
        if (ActivityCompat.checkSelfPermission(this.getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    void ubicaciones(){
        mMap.addMarker(new MarkerOptions().
                position(Monteria).
                title("Comestibles DAN, Sede Montería").
                snippet("Calle 15 # 8A – 29 La Charme").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Medellin).
                title("Comestibles DAN, Sede Itagüi").
                snippet("Carrera 41 # 46-81 ").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Cartagena).
                title("Comestibles DAN, Sede Cartagena").
                snippet("Centro Industrial y Comercial Ternera #2 Local I-2").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Barranquilla).
                title("Comestibles DAN, Sede Barranquilla").
                snippet("Vía 40 # 71-197 Bodega 216 Centro Industrial Marisol").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Bucaramanga).
                title("Comestibles DAN, Sede Bucaramanga").
                snippet("Kilómetro 4 Anillo Vial, vía Florida Blanca- Girón Bodega 3").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Bogota).
                title("Comestibles DAN, Sede Bogota").
                snippet("Autopista Medellín, kilómetro 7 Celta Trade Park Lote 41 Bodega 3 Funza").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Pereira).
                title("Comestibles DAN, Sede Pereira").
                snippet("Carrera 9BIS # 34-65 Esquina").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Uraba).
                title("Comestibles DAN, Sede Uraba").
                snippet("Carrera 96A # 93-28").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        mMap.addMarker(new MarkerOptions().
                position(Cali).
                title("Comestibles DAN, Sede Cali").
                snippet("Carrera 25A # 12-98").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));

    }

}
