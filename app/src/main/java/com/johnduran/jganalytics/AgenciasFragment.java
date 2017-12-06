package com.johnduran.jganalytics;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgenciasFragment extends Fragment implements View.OnClickListener {
    //_____________Variables_____________________________
    private String NombrePreferencias="Mis_Preferencias";
    //_____________Eventos_______________________________
    private RadioButton rMedellin, rBogota, rMonteria, rCartagena, rTotal, rBarranquilla, rBucaramanga, rCali, rUraba, rPereira;
    private Button bConsultar, getbConsultarGraficas;
    //_____________Otros________________________________
    SharedPreferences prefs;
    SharedPreferences.Editor editor;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agencias, container, false);
        bConsultar = (Button) v.findViewById(R.id.bConsultar);
        bConsultar.setOnClickListener(this);
        getbConsultarGraficas = (Button) v.findViewById(R.id.bConsultarGraficas);
        getbConsultarGraficas.setOnClickListener(this);

        //Declaro los radiobuttons
        rMedellin = (RadioButton) v.findViewById(R.id.rMedellin);
        rBogota = (RadioButton) v.findViewById(R.id.rBogota);
        rMonteria = (RadioButton) v.findViewById(R.id.rMonteria);
        rCartagena = (RadioButton) v.findViewById(R.id.rCartagena);
        rTotal = (RadioButton) v.findViewById(R.id.rTotal);
        rBarranquilla = (RadioButton) v.findViewById(R.id.rBarranquilla);
        rPereira = (RadioButton) v.findViewById(R.id.rPereira);
        rUraba = (RadioButton) v.findViewById(R.id.rUraba);
        rBucaramanga = (RadioButton) v.findViewById(R.id.rBucaramanga);
        rCali=(RadioButton) v.findViewById(R.id.rCali);
        prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
        editor = prefs.edit();
        editor.putInt("loggeado",1);
        editor.putInt("cerrarSesion",0);
        editor.commit();
        return v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bConsultar:
                if(rTotal.isChecked()){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="General",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if (rMedellin.isChecked() ){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Medellin",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if (rBarranquilla.isChecked()){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Barranquilla",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if( rBogota.isChecked() ){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Bogota",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if( rUraba.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Uraba",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if(rCartagena.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Cartagena",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if( rMonteria.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Monteria",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if(rPereira.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Pereira",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if(rCali.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Cali",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if(rBucaramanga.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Bucaramanga",tipoConsulta="tablas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }

                break;

            case R.id.bConsultarGraficas:
                if(rTotal.isChecked()){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="General",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if (rMedellin.isChecked() ){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Medellin",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if (rBarranquilla.isChecked()){
                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Barranquilla",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("agencia", agencia);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.commit();
                    startActivity(intent);

                }else if( rBogota.isChecked() ){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Bogota",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if( rUraba.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Uraba",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if(rCartagena.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Cartagena",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if( rMonteria.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Monteria",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if(rPereira.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Pereira",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);


                }else if(rCali.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Cali",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }else if(rBucaramanga.isChecked()){

                    Intent intent =new Intent (this.getContext(), NDrawerActivity.class);
                    prefs=this.getActivity().getSharedPreferences(NombrePreferencias, Context.MODE_PRIVATE);
                    editor = prefs.edit();
                    String indicadores="on", agencia="Bucaramanga",tipoConsulta="graficas";
                    editor.putString("indicadores", indicadores);
                    editor.putString("tipoConsulta", tipoConsulta);
                    editor.putString("agencia", agencia);
                    editor.commit();
                    startActivity(intent);
                }
                break;
        }

    }
}
