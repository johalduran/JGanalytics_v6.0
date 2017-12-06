package com.johnduran.jganalytics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TablaInventariosBucaramangaFragment extends Fragment {



    DatabaseReference myRef;
    Tablas inventarios; //Inventarios usa la misma estructura que Nivel de servicio
    private String Campos[]={"FAMILIA","DEMANDA","DIAS_INV","INV_HOY","META"};
    private TextView tbodega1,tbodega2,tbodega3,tbodega4,tbodega5,
            tdemanda1,tdemanda2,tdemanda3,tdemanda4,tdemanda5,
            tinvhoy1,tinvhoy2,tinvhoy3,tinvhoy4,tinvhoy5,
            tdiasinv1,tdiasinv2,tdiasinv3,tdiasinv4,tdiasinv5,
            tmeta1,tmeta2,tmeta3,tmeta4,tmeta5;
    private String Dato1,Dato2,Dato3,Dato4,Dato5;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabla_inventarios_agencias, container, false);
        int i=0;
        while(i<5){
            Log.d("A","AQUI2: "+i);
            leerDatos(i);
            i++;
        }
        return v;
    }
    public void leerDatos(int id){
        final int i=id;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Inventarios_Bucaramanga");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(Campos[i]).exists()) {
                    inventarios = dataSnapshot.child(Campos[i]).getValue(Tablas.class);
                    obtenerDatos();
                    if(i==0){imprimirDatosBodega();}
                    if(i==1){imprimirDatosDemanda();}
                    if(i==2){imprimirDatosInvHoy();}
                    if(i==3){imprimirDatosDiasInv();}
                    if(i==4){imprimirDatosMeta();}

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void obtenerDatos(){
        Dato1=inventarios.getDato1();
        Dato2=inventarios.getDato2();
        Dato3=inventarios.getDato3();
        Dato4=inventarios.getDato4();
        Dato5=inventarios.getDato5();



    }
    public void imprimirDatosBodega(){
        tbodega1= (TextView) getView().findViewById(R.id.tbodega1);
        tbodega2= (TextView) getView().findViewById(R.id.tbodega2);
        tbodega3= (TextView) getView().findViewById(R.id.tbodega3);
        tbodega4= (TextView) getView().findViewById(R.id.tbodega4);
        tbodega5= (TextView) getView().findViewById(R.id.tbodega5);



        tbodega1.setText(Dato1 );
        tbodega2.setText(Dato2 );
        tbodega3.setText(Dato3 );
        tbodega4.setText(Dato4 );
        tbodega5.setText(Dato5 );


    }
    public void imprimirDatosDemanda(){
        tdemanda1= (TextView) getView().findViewById(R.id.tdemanda1);
        tdemanda2= (TextView) getView().findViewById(R.id.tdemanda2);
        tdemanda3= (TextView) getView().findViewById(R.id.tdemanda3);
        tdemanda4= (TextView) getView().findViewById(R.id.tdemanda4);
        tdemanda5= (TextView) getView().findViewById(R.id.tdemanda5);



        tdemanda1.setText(Dato1 );
        tdemanda2.setText(Dato2 );
        tdemanda3.setText(Dato3 );
        tdemanda4.setText(Dato4 );
        tdemanda5.setText(Dato5 );



    }
    public void imprimirDatosInvHoy(){
        tinvhoy1= (TextView) getView().findViewById(R.id.tinvhoy1);
        tinvhoy2= (TextView) getView().findViewById(R.id.tinvhoy2);
        tinvhoy3= (TextView) getView().findViewById(R.id.tinvhoy3);
        tinvhoy4= (TextView) getView().findViewById(R.id.tinvhoy4);
        tinvhoy5= (TextView) getView().findViewById(R.id.tinvhoy5);



        tinvhoy1.setText(Dato1 );
        tinvhoy2.setText(Dato2 );
        tinvhoy3.setText(Dato3 );
        tinvhoy4.setText(Dato4 );
        tinvhoy5.setText(Dato5 );


    }
    public void imprimirDatosDiasInv(){
        tdiasinv1= (TextView) getView().findViewById(R.id.tdiasinv1);
        tdiasinv2= (TextView) getView().findViewById(R.id.tdiasinv2);
        tdiasinv3= (TextView) getView().findViewById(R.id.tdiasinv3);
        tdiasinv4= (TextView) getView().findViewById(R.id.tdiasinv4);
        tdiasinv5= (TextView) getView().findViewById(R.id.tdiasinv5);



        tdiasinv1.setText(Dato1);
        tdiasinv2.setText(Dato2);
        tdiasinv3.setText(Dato3);
        tdiasinv4.setText(Dato4);
        tdiasinv5.setText(Dato5);



    }

    public void imprimirDatosMeta(){
        tmeta1= (TextView) getView().findViewById(R.id.tmeta1);
        tmeta2= (TextView) getView().findViewById(R.id.tmeta2);
        tmeta3= (TextView) getView().findViewById(R.id.tmeta3);
        tmeta4= (TextView) getView().findViewById(R.id.tmeta4);
        tmeta5= (TextView) getView().findViewById(R.id.tmeta5);



        tmeta1.setText(Dato1 );
        tmeta2.setText(Dato2 );
        tmeta3.setText(Dato3 );
        tmeta4.setText(Dato4 );
        tmeta5.setText(Dato5 );


    }
}
