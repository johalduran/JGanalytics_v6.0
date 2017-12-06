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
public class TablaVentasFragment extends Fragment {

    DatabaseReference myRef;
    Tablas ventas_diarias;
    private String Campos[]={"SEGUIMIENTO_VENTA","VALOR"};
    private TextView tFecha1,tFecha2,tFecha3,tFecha4,tFecha5,tFecha6,tFecha7,tFecha8,tFecha9,tFecha10,
            tMeta1,tMeta2,tMeta3,tMeta4,tMeta5,tMeta6,tMeta7,tMeta8,tMeta9,tMeta10;
    private String Dato1,Dato2,Dato3,Dato4,Dato5,Dato6,Dato7,Dato8,Dato9,Dato10,
            Dato11,Dato12,Dato13,Dato14,Dato15,Dato16,Dato17,Dato18,Dato19,Dato20,Dato21;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabla_ventas_general, container, false);


        int i=0;
        while(i<2){
            leerDatos(i);
            i++;
        }

        return v;
    }
    public void leerDatos(int id){
        final int i=id;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Ventas_Diarias_Tabla");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.d("A","AQUI: "+Campos[i]+ "  "+ i);
                if (dataSnapshot.child(Campos[i]).exists()) {
                    ventas_diarias = dataSnapshot.child(Campos[i]).getValue(Tablas.class);
                    obtenerDatos();
                    if(i==0){imprimirDatosFechas();}
                    if(i==1){imprimirDatosMeta();}

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void obtenerDatos(){
        Dato1=ventas_diarias.getDato1();
        Dato2=ventas_diarias.getDato2();
        Dato3=ventas_diarias.getDato3();
        Dato4=ventas_diarias.getDato4();
        Dato5=ventas_diarias.getDato5();
        Dato6=ventas_diarias.getDato6();
        Dato7=ventas_diarias.getDato7();
        Dato8=ventas_diarias.getDato8();
        Dato9=ventas_diarias.getDato9();
        Dato10=ventas_diarias.getDato10();
        Dato11=ventas_diarias.getDato11();
        Dato12=ventas_diarias.getDato12();
        Dato13=ventas_diarias.getDato13();
        Dato14=ventas_diarias.getDato14();
        Dato15=ventas_diarias.getDato15();
        Dato16=ventas_diarias.getDato16();
        Dato17=ventas_diarias.getDato17();
        Dato18=ventas_diarias.getDato18();
        Dato19=ventas_diarias.getDato19();
        Dato20=ventas_diarias.getDato20();
        Dato21=ventas_diarias.getDato21();

    }
    public void imprimirDatosFechas(){
        tFecha1= (TextView) getView().findViewById(R.id.fecha1);
        tFecha2= (TextView) getView().findViewById(R.id.fecha2);
        tFecha3= (TextView) getView().findViewById(R.id.fecha3);
        tFecha4= (TextView) getView().findViewById(R.id.fecha4);
        tFecha5= (TextView) getView().findViewById(R.id.fecha5);
        tFecha6= (TextView) getView().findViewById(R.id.fecha6);
        tFecha7= (TextView) getView().findViewById(R.id.fecha7);
        tFecha8= (TextView) getView().findViewById(R.id.fecha8);
        tFecha9= (TextView) getView().findViewById(R.id.fecha9);
        tFecha10= (TextView) getView().findViewById(R.id.fecha10);


        tFecha1.setText(Dato1 );
        tFecha2.setText(Dato2 );
        tFecha3.setText(Dato3 );
        tFecha4.setText(Dato4 );
        tFecha5.setText(Dato5 );
        tFecha6.setText(Dato6 );
        tFecha7.setText(Dato7 );
        tFecha8.setText(Dato8 );
        tFecha9.setText(Dato9 );
        tFecha10.setText(Dato10 );


    }
    public void imprimirDatosMeta(){
        tMeta1= (TextView) getView().findViewById(R.id.meta1);
        tMeta2= (TextView) getView().findViewById(R.id.meta2);
        tMeta3= (TextView) getView().findViewById(R.id.meta3);
        tMeta4= (TextView) getView().findViewById(R.id.meta4);
        tMeta5= (TextView) getView().findViewById(R.id.meta5);
        tMeta6= (TextView) getView().findViewById(R.id.meta6);
        tMeta7= (TextView) getView().findViewById(R.id.meta7);
        tMeta8= (TextView) getView().findViewById(R.id.meta8);
        tMeta9= (TextView) getView().findViewById(R.id.meta9);
        tMeta10= (TextView) getView().findViewById(R.id.meta10);


        tMeta1.setText("$"+Dato1 );
        tMeta2.setText("$"+Dato2 );
        tMeta3.setText("$"+Dato3 );
        tMeta4.setText("$"+Dato4 );
        tMeta5.setText("$"+Dato5 );
        tMeta6.setText("$"+Dato6 );
        tMeta7.setText("$"+Dato7 );
        tMeta8.setText("$"+Dato8 );
        tMeta9.setText("$"+Dato9 );
        tMeta10.setText("$"+Dato10 );


    }

}
