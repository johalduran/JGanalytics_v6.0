package com.johnduran.jganalytics;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class NDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    private String NombrePreferencias="Mis_Preferencias", indicadores="off",
            urlDefault="http://www.freeiconspng.com/uploads/profile-icon-9.png",nombre="",BotonBack="",
            correoL="",name="", email="", url="", uid="",tipoConsulta="",agencia="";
    private int optLog=0;
    //Eventos
    private NDrawerActivity.SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private LinearLayout Lineartabs, Linearbottom;
    private ImageView fotoPerfil;
    private TextView tCorreoNaviD, tNombreNaviD;
    private BottomNavigationView bottom;
    //Otros
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    GoogleApiClient mGoogleApiClient;
    //Para firebase
    private FirebaseAuth firebaseAuth;
    private GoogleMap mMap;
    private int numeroTabs=3;
    private String tituloTab0="Ventas", tituloTab1="Nivel de Servicio",tituloTab2="Inventarios";




    //____________ para el bottom navigation drawer
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm= getSupportFragmentManager();
            FragmentTransaction ft= fm.beginTransaction();
            if(indicadores=="off") {
                Linearbottom.setVisibility(View.VISIBLE);
                Lineartabs.setVisibility(View.GONE);
            }
            switch (item.getItemId()) {
                case R.id.navigation_ubicacion:
                    MapaFragment fragment= new MapaFragment();
                    ft.replace(R.id.content_frame, fragment).commit();
                    return true;
                case R.id.navigation_agencias:
                    AgenciasFragment fragment2= new AgenciasFragment();
                    ft.replace(R.id.content_frame, fragment2).commit();
                    return true;
                case R.id.navigation_contactanos:
                    ContactenosFragment fragment3= new ContactenosFragment();
                    ft.replace(R.id.content_frame, fragment3).commit();
                    return true;
            }
            return false;
        }

    };
    //_______________


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndrawer);
        bottom= (BottomNavigationView) findViewById(R.id.navigation);
        Lineartabs= (LinearLayout) findViewById(R.id.Lineartabs);
        Linearbottom= (LinearLayout) findViewById(R.id.Linearbottom);

        //_____________ Para navigation drawer________________
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true); //selecciona la opcion cero por defecto (Agencias)


        //________________________________________________________
        prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
        optLog= prefs.getInt("optLog",0);
        correoL= prefs.getString("correoL","");
        nombre= prefs.getString("nombre","");

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        tNombreNaviD= (TextView)hView.findViewById(R.id.tNombreNaviD);
        tCorreoNaviD= (TextView)hView.findViewById(R.id.tCorreoNaviD);
        fotoPerfil = (ImageView) hView.findViewById(R.id.imageView);

        if(optLog==2 || optLog==3){
            //_________________Obtengo los datos de firebase para facebook y google
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser user= firebaseAuth.getCurrentUser();
            name = user.getDisplayName();
            email= user.getEmail();
            uid = user.getUid();
            url= user.getPhotoUrl().toString();
            tNombreNaviD.setText(name);
            if(email!=null){tCorreoNaviD.setText(email);}
            cargarImagendeURL(url,fotoPerfil); //Carga la url de la foto segun el tipo de login
        }else if(optLog==1){
            tNombreNaviD.setText(nombre);
            tCorreoNaviD.setText(correoL);
            cargarImagendeURL(urlDefault,fotoPerfil); //Carga la url de la foto segun el tipo de login
        }


        //Configuro el correo y el nombre en el N D
        prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
        optLog= prefs.getInt("optLog",0);
        /// Para el bottom navigation drawer
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.getMenu().getItem(1).setChecked(true); //selecciona la opción cero por defecto (Agencias)

        //____________________________________google___________________________
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //.requestIdToken(getString(R.string.default_web_client_id)) // Cuando se conecte a un servidor
                .requestProfile()
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(getApplicationContext(),"Error en login", Toast.LENGTH_LONG).show();
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //____________________________________________________________

        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        AgenciasFragment fragment= new AgenciasFragment();
        ft.replace(R.id.content_frame, fragment).commit();
        indicadores= prefs.getString("indicadores","off");

        if(indicadores=="on"){
            BotonBack="on";
            prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
            editor = prefs.edit();
            editor.putString("indicadores","off");
            agencia= prefs.getString("agencia","");
            tipoConsulta=prefs.getString("tipoConsulta","");
            editor.commit();

            Log.d("Ndrawer", "ANTES: "+tipoConsulta+"  "+agencia+" "+numeroTabs+"  ");
//______________________________________________
            if(!agencia.equals("General") && tipoConsulta.equals("graficas")){
                numeroTabs=1;
            }else if(!agencia.equals("General") && tipoConsulta.equals("tablas")){
                numeroTabs=2;
                tituloTab0="Nivel de Servicio";
                tituloTab1="Inventarios";
            }else if(agencia.equals("General") && tipoConsulta.equals("tablas")){
                numeroTabs=3;
                tituloTab0="Ventas";
                tituloTab1="Nivel de Servicio";
                tituloTab2="Inventarios";
            }else if(agencia.equals("General") && tipoConsulta.equals("graficas")){
                numeroTabs=2;
                tituloTab0="Ventas";
                tituloTab1="Nivel de Servicio";

            }
//_______________________________________________

            Linearbottom.setVisibility(View.GONE);
            Lineartabs.setVisibility(View.VISIBLE);

            //______________Para swipe tabs_____________________________
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new NDrawerActivity.SectionsPagerAdapter(getSupportFragmentManager());
            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            //Toolbar toolbar_tabs = (Toolbar) findViewById(R.id.toolbar);
            //setSupportActionBar(toolbar_tabs);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);


            //__________________________________________________________



        } else{
            Linearbottom.setVisibility(View.VISIBLE);
            Lineartabs.setVisibility(View.GONE);
        }


    }

    @Override
    public void onBackPressed() {
        //Toast.makeText(getApplicationContext(),"Back", Toast.LENGTH_LONG).show();
        if(BotonBack=="on"){
            BotonBack="off";
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }

        }else {
            prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
            editor = prefs.edit();
            editor.putString("MataApp","si");
            editor.commit();
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent, intent2;
        BotonBack="off";
        prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
        indicadores= prefs.getString("indicadores","off");
        editor = prefs.edit();
        if(indicadores=="off") {
            Linearbottom.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
            Lineartabs.setVisibility(View.GONE);
        }
        editor.commit();


        switch(id){
            case R.id.mPerfil:
                bottom.setVisibility(View.GONE);

                FragmentManager fm= getSupportFragmentManager();
                FragmentTransaction ft= fm.beginTransaction();

                PerfilFragment fragment= new PerfilFragment();
                ft.replace(R.id.content_frame, fragment).commit();
                break;
            case R.id.mCerrar:
                prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                editor = prefs.edit();
                editor.putInt("cerrarSesion",1);
                editor.putInt("loggeado",0);
                editor.putString("correoL","none");
                editor.putString("contrasenaL","none");
                editor.commit();
                if(optLog==1){ //Para correo
                    LoginManager.getInstance().logOut();
                    intent =  new Intent(NDrawerActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else if(optLog==2){
                    LoginManager.getInstance().logOut(); // Logout con facebook
                    FirebaseAuth.getInstance().signOut(); // Logout con firebase +fb
                    intent2 =  new Intent(NDrawerActivity.this, LoginActivity.class);
                    startActivity(intent2);
                    finish();
                }else if(optLog==3){
                    //__________________logout con google_______________
                    FirebaseAuth.getInstance().signOut(); // logout de firebase + gogle
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                            new ResultCallback<Status>() {
                                @Override
                                public void onResult(Status status) {
                                    // ...
                                }
                            });
                    //________________________________________________________
                    intent2 =  new Intent(NDrawerActivity.this, LoginActivity.class);
                    startActivity(intent2);
                    finish();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fm= getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();
        BotonBack="off";
        prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
        indicadores= prefs.getString("indicadores","off");
        editor = prefs.edit();
        if(indicadores=="off") {
            Linearbottom.setVisibility(View.VISIBLE);
            bottom.setVisibility(View.VISIBLE);
            Lineartabs.setVisibility(View.GONE);
        }
        editor.commit();

        int id = item.getItemId();

        if (id == R.id.nav_agencias) {
            bottom.setVisibility(View.VISIBLE);
            AgenciasFragment fragment= new AgenciasFragment();
            ft.replace(R.id.content_frame, fragment).commit();
        } else if (id == R.id.nav_perfil) {
            bottom.setVisibility(View.GONE);
            PerfilFragment fragment= new PerfilFragment();
            ft.replace(R.id.content_frame, fragment).commit();
        }  else if (id == R.id.nav_ayuda) {
            bottom.setVisibility(View.GONE);
            AyudaFragment fragment= new AyudaFragment();
            ft.replace(R.id.content_frame, fragment).commit();

        } else if (id == R.id.nav_cerrar_sesion) {
            Intent intent,intent2;
            prefs=getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
            editor = prefs.edit();
            editor.putInt("cerrarSesion",1);
            editor.putInt("loggeado",0);
            editor.putString("correoL","none");
            editor.putString("contrasenaL","none");
            editor.commit();
            if(optLog==1){
                LoginManager.getInstance().logOut();
                intent =  new Intent(NDrawerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else if(optLog==2){
                LoginManager.getInstance().logOut(); // Logout con facebook
                FirebaseAuth.getInstance().signOut(); // Logout con firebase +fb
                intent2 =  new Intent(NDrawerActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
            }else if(optLog==3){
                //__________________logout con google_______________
                FirebaseAuth.getInstance().signOut(); // logout de firebase + gogle

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...
                            }
                        });
                //________________________________________________________
                intent2 =  new Intent(NDrawerActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //_______________________Para los swipe tabs________________________
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {




            switch (position) {
                case 0: //Ventas
                    //TablaVentasFragment tab0 = new TablaVentasFragment();return tab0;

                    switch (agencia){
                        case "General":
                            if(tipoConsulta.equals("tablas")){TablaVentasFragment tab0 = new TablaVentasFragment();return tab0;}
                            if(tipoConsulta.equals("graficas")){ GraficaVentasFragment tab0 = new GraficaVentasFragment();return tab0;}
                        case "Medellin":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasMedellinFragment tab0 = new GraficaVentasMedellinFragment();return tab0;}
                        case "Bogota":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasBogotaFragment tab0 = new GraficaVentasBogotaFragment();return tab0;}
                        case "Cali":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasCaliFragment tab0 = new GraficaVentasCaliFragment();return tab0;}
                        case "Bucaramanga":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasBucaramangaFragment tab0 = new GraficaVentasBucaramangaFragment();return tab0;}
                        case "Monteria":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasMonteriaFragment tab0 = new GraficaVentasMonteriaFragment();return tab0;}
                        case "Uraba":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasUrabaFragment tab0 = new GraficaVentasUrabaFragment();return tab0;}
                        case "Barranquilla":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasBarranquillaFragment tab0 = new GraficaVentasBarranquillaFragment();return tab0;}
                        case "Pereira":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasPereiraFragment tab0 = new GraficaVentasPereiraFragment();return tab0;}
                        case "Cartagena":
                            if(tipoConsulta.equals("graficas")){ GraficaVentasCartagenaFragment tab0 = new GraficaVentasCartagenaFragment();return tab0;}
                    }


                case 1: //Nivel servicio
                   // TablaVentasFragment tab1 = new TablaVentasFragment();return tab1;


                    switch (agencia){

                        case "General":
                            if(tipoConsulta.equals("tablas")){TablaNivelServicioFragment tab1 = new TablaNivelServicioFragment();return tab1;}
                            if(tipoConsulta.equals("graficas")){ GraficaNivelServicioFragment tab1 = new GraficaNivelServicioFragment();return tab1;}
                        case "Medellin":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioMedellinFragment tab1 = new TablaNivelServicioMedellinFragment();return tab1;}
                        case "Bogota":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioBogotaFragment tab1 = new TablaNivelServicioBogotaFragment();return tab1;}
                        case "Cali":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioCaliFragment tab1 = new TablaNivelServicioCaliFragment();return tab1;}
                        case "Bucaramanga":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioBucaramangaFragment tab1 = new TablaNivelServicioBucaramangaFragment();return tab1;}
                        case "Monteria":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioMonteriaFragment tab1 = new TablaNivelServicioMonteriaFragment();return tab1;}
                        case "Uraba":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioUrabaFragment tab1 = new TablaNivelServicioUrabaFragment();return tab1;}
                        case "Barranquilla":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioBarranquillaFragment tab1 = new TablaNivelServicioBarranquillaFragment();return tab1;}
                        case "Pereira":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioPereiraFragment tab1 = new TablaNivelServicioPereiraFragment();return tab1;}
                        case "Cartagena":
                            if(tipoConsulta.equals("tablas")){ TablaNivelServicioCartagenaFragment tab1 = new TablaNivelServicioCartagenaFragment();return tab1;}
                    }

                case 2: //Inventarios
                    //TablaVentasFragment tab2 = new TablaVentasFragment();return tab2;


                    switch (agencia){

                        case "General":
                            if(tipoConsulta.equals("tablas")){InventariosFragment tab2 = new InventariosFragment();return tab2;}
                        case "Medellin":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosMedellinFragment tab2 = new TablaInventariosMedellinFragment();return tab2;}
                        case "Bogota":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosBogotaFragment tab2 = new TablaInventariosBogotaFragment();return tab2;}
                        case "Cali":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosCaliFragment tab2 = new TablaInventariosCaliFragment();return tab2;}
                        case "Bucaramanga":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosBucaramangaFragment tab2 = new TablaInventariosBucaramangaFragment();return tab2;}
                        case "Monteria":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosMonteriaFragment tab2 = new TablaInventariosMonteriaFragment();return tab2;}
                        case "Uraba":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosUrabaFragment tab2 = new TablaInventariosUrabaFragment();return tab2;}
                        case "Barranquilla":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosBarranquillaFragment tab2 = new TablaInventariosBarranquillaFragment();return tab2;}
                        case "Pereira":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosPereiraFragment tab2 = new TablaInventariosPereiraFragment();return tab2;}
                        case "Cartagena":
                            if(tipoConsulta.equals("tablas")){ TablaInventariosCartagenaFragment tab2 = new TablaInventariosCartagenaFragment();return tab2;}
                    }

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return numeroTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if(numeroTabs==1 || numeroTabs==2 ||numeroTabs==3){return tituloTab0;}
                    //return "Ventas";
                case 1:
                    if(numeroTabs==2 ||numeroTabs==3){return tituloTab1;}
                    //return "Nivel de Servicio";
                case 2:
                    if(numeroTabs==3){return tituloTab2;}
                    //return "Inventarios";
            }
            return null;
        }
    }

    //_______________________________________________________

    private void cargarImagendeURL(String url, ImageView imageView) {
        Picasso.with(NDrawerActivity.this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView,new com.squareup.picasso.Callback(){
                    @Override
                    public void onSuccess(){}
                    @Override
                    public void onError(){}
                });
    }




}
