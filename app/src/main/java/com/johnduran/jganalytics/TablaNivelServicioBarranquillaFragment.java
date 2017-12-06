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
public class TablaNivelServicioBarranquillaFragment extends Fragment {
    DatabaseReference myRef;
    Tablas nivel_servicio;
    private String Campos[]={"CANAL","UND_PEDIDAS","UND_FACTURADAS","NS_UND","VALOR_PEDIDO","VALOR_FACTURA","NS_VALOR","DEJADO_DE_FACT"};
    private TextView tagencia1,tagencia2,tagencia3,tagencia4,tagencia5,tagencia6,tagencia7,
            tundpedidas1,tundpedidas2,tundpedidas3,tundpedidas4,tundpedidas5,tundpedidas6,tundpedidas7,
            tundfacturadas1,tundfacturadas2,tundfacturadas3,tundfacturadas4,tundfacturadas5,tundfacturadas6,tundfacturadas7,
            tnsund1,tnsund2,tnsund3,tnsund4,tnsund5,tnsund6,tnsund7,
            tvalorpedido1,tvalorpedido2,tvalorpedido3,tvalorpedido4,tvalorpedido5,tvalorpedido6,tvalorpedido7,
            tvalorfactura1,tvalorfactura2,tvalorfactura3,tvalorfactura4,tvalorfactura5,tvalorfactura6,tvalorfactura7,
            tnsvalor1,tnsvalor2,tnsvalor3,tnsvalor4,tnsvalor5,tnsvalor6,tnsvalor7,
            tdejadodefact1,tdejadodefact2,tdejadodefact3,tdejadodefact4,tdejadodefact5,tdejadodefact6,tdejadodefact7;
    private String Dato1,Dato2,Dato3,Dato4,Dato5,Dato6,Dato7;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tabla_nivel_servicio_agencias, container, false);
        int i=0;
        while(i<8){
            leerDatos(i);
            i++;
        }
        return v;
    }

    public void leerDatos(int id){
        final int i=id;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Nivel_Servicio_Barranquilla");
        Log.d("A","AQUI2: "+Campos[i]+ "  "+ i);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("A","AQUI: "+Campos[i]+ "  "+ i);
                if (dataSnapshot.child(Campos[i]).exists()) {
                    nivel_servicio = dataSnapshot.child(Campos[i]).getValue(Tablas.class);
                    obtenerDatos();
                    if(i==0){imprimirDatosAgencia();}
                    if(i==1){imprimirDatosUndPedidas();}
                    if(i==2){imprimirDatosUndFacturadas();}
                    if(i==3){imprimirDatosNsUnd();}
                    if(i==4){imprimirDatosValorPedido();}
                    if(i==5){imprimirDatosValorFactura();}
                    if(i==6){imprimirDatosNsValor();}
                    if(i==7){imprimirDatosDejadoDeFact();}

                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    public void obtenerDatos(){
        Dato1=nivel_servicio.getDato1();
        Dato2=nivel_servicio.getDato2();
        Dato3=nivel_servicio.getDato3();
        Dato4=nivel_servicio.getDato4();
        Dato5=nivel_servicio.getDato5();
        Dato6=nivel_servicio.getDato6();
        Dato7=nivel_servicio.getDato7();



    }
    public void imprimirDatosAgencia(){
        tagencia1= (TextView) getView().findViewById(R.id.tagencia1);
        tagencia2= (TextView) getView().findViewById(R.id.tagencia2);
        tagencia3= (TextView) getView().findViewById(R.id.tagencia3);
        tagencia4= (TextView) getView().findViewById(R.id.tagencia4);
        tagencia5= (TextView) getView().findViewById(R.id.tagencia5);
        tagencia6= (TextView) getView().findViewById(R.id.tagencia6);
        tagencia7= (TextView) getView().findViewById(R.id.tagencia7);


        tagencia1.setText(Dato1 );
        tagencia2.setText(Dato2 );
        tagencia3.setText(Dato3 );
        tagencia4.setText(Dato4 );
        tagencia5.setText(Dato5 );
        tagencia6.setText(Dato6 );
        tagencia7.setText(Dato7 );


    }
    public void imprimirDatosUndPedidas(){
        tundpedidas1= (TextView) getView().findViewById(R.id.tundpedidas1);
        tundpedidas2= (TextView) getView().findViewById(R.id.tundpedidas2);
        tundpedidas3= (TextView) getView().findViewById(R.id.tundpedidas3);
        tundpedidas4= (TextView) getView().findViewById(R.id.tundpedidas4);
        tundpedidas5= (TextView) getView().findViewById(R.id.tundpedidas5);
        tundpedidas6= (TextView) getView().findViewById(R.id.tundpedidas6);
        tundpedidas7= (TextView) getView().findViewById(R.id.tundpedidas7);


        tundpedidas1.setText(Dato1 );
        tundpedidas2.setText(Dato2 );
        tundpedidas3.setText(Dato3 );
        tundpedidas4.setText(Dato4 );
        tundpedidas5.setText(Dato5 );
        tundpedidas6.setText(Dato6 );
        tundpedidas7.setText(Dato7 );



    }
    public void imprimirDatosUndFacturadas(){
        tundfacturadas1= (TextView) getView().findViewById(R.id.tundfacturadas1);
        tundfacturadas2= (TextView) getView().findViewById(R.id.tundfacturadas2);
        tundfacturadas3= (TextView) getView().findViewById(R.id.tundfacturadas3);
        tundfacturadas4= (TextView) getView().findViewById(R.id.tundfacturadas4);
        tundfacturadas5= (TextView) getView().findViewById(R.id.tundfacturadas5);
        tundfacturadas6= (TextView) getView().findViewById(R.id.tundfacturadas6);
        tundfacturadas7= (TextView) getView().findViewById(R.id.tundfacturadas7);



        tundfacturadas1.setText(Dato1 );
        tundfacturadas2.setText(Dato2 );
        tundfacturadas3.setText(Dato3 );
        tundfacturadas4.setText(Dato4 );
        tundfacturadas5.setText(Dato5 );
        tundfacturadas6.setText(Dato6 );
        tundfacturadas7.setText(Dato7 );


    }
    public void imprimirDatosNsUnd(){
        tnsund1= (TextView) getView().findViewById(R.id.tnsund1);
        tnsund2= (TextView) getView().findViewById(R.id.tnsund2);
        tnsund3= (TextView) getView().findViewById(R.id.tnsund3);
        tnsund4= (TextView) getView().findViewById(R.id.tnsund4);
        tnsund5= (TextView) getView().findViewById(R.id.tnsund5);
        tnsund6= (TextView) getView().findViewById(R.id.tnsund6);
        tnsund7= (TextView) getView().findViewById(R.id.tnsund7);



        tnsund1.setText(Dato1+"%" );
        tnsund2.setText(Dato2+"%" );
        tnsund3.setText(Dato3+"%" );
        tnsund4.setText(Dato4+"%" );
        tnsund5.setText(Dato5+"%" );
        tnsund6.setText(Dato6+"%" );
        tnsund7.setText(Dato7+"%" );



    }

    public void imprimirDatosValorPedido(){
        tvalorpedido1= (TextView) getView().findViewById(R.id.tvalorpedido1);
        tvalorpedido2= (TextView) getView().findViewById(R.id.tvalorpedido2);
        tvalorpedido3= (TextView) getView().findViewById(R.id.tvalorpedido3);
        tvalorpedido4= (TextView) getView().findViewById(R.id.tvalorpedido4);
        tvalorpedido5= (TextView) getView().findViewById(R.id.tvalorpedido5);
        tvalorpedido6= (TextView) getView().findViewById(R.id.tvalorpedido6);
        tvalorpedido7= (TextView) getView().findViewById(R.id.tvalorpedido7);



        tvalorpedido1.setText("$"+Dato1 );
        tvalorpedido2.setText("$"+Dato2 );
        tvalorpedido3.setText("$"+Dato3 );
        tvalorpedido4.setText("$"+Dato4 );
        tvalorpedido5.setText("$"+Dato5 );
        tvalorpedido6.setText("$"+Dato6 );
        tvalorpedido7.setText("$"+Dato7 );



    }
    public void imprimirDatosValorFactura(){
        tvalorfactura1= (TextView) getView().findViewById(R.id.tvalorfactura1);
        tvalorfactura2= (TextView) getView().findViewById(R.id.tvalorfactura2);
        tvalorfactura3= (TextView) getView().findViewById(R.id.tvalorfactura3);
        tvalorfactura4= (TextView) getView().findViewById(R.id.tvalorfactura4);
        tvalorfactura5= (TextView) getView().findViewById(R.id.tvalorfactura5);
        tvalorfactura6= (TextView) getView().findViewById(R.id.tvalorfactura6);
        tvalorfactura7= (TextView) getView().findViewById(R.id.tvalorfactura7);


        tvalorfactura1.setText("$"+Dato1 );
        tvalorfactura2.setText("$"+Dato2 );
        tvalorfactura3.setText("$"+Dato3 );
        tvalorfactura4.setText("$"+Dato4 );
        tvalorfactura5.setText("$"+Dato5 );
        tvalorfactura6.setText("$"+Dato6 );
        tvalorfactura7.setText("$"+Dato7 );



    }
    public void imprimirDatosNsValor(){
        tnsvalor1= (TextView) getView().findViewById(R.id.tnsvalor1);
        tnsvalor2= (TextView) getView().findViewById(R.id.tnsvalor2);
        tnsvalor3= (TextView) getView().findViewById(R.id.tnsvalor3);
        tnsvalor4= (TextView) getView().findViewById(R.id.tnsvalor4);
        tnsvalor5= (TextView) getView().findViewById(R.id.tnsvalor5);
        tnsvalor6= (TextView) getView().findViewById(R.id.tnsvalor6);
        tnsvalor7= (TextView) getView().findViewById(R.id.tnsvalor7);



        tnsvalor1.setText(Dato1+"%");
        tnsvalor2.setText(Dato2+"%" );
        tnsvalor3.setText(Dato3+"%" );
        tnsvalor4.setText(Dato4+"%" );
        tnsvalor5.setText(Dato5+"%" );
        tnsvalor6.setText(Dato6+"%" );
        tnsvalor7.setText(Dato7+"%" );



    }
    public void imprimirDatosDejadoDeFact(){
        tdejadodefact1= (TextView) getView().findViewById(R.id.tdejadodefact1);
        tdejadodefact2= (TextView) getView().findViewById(R.id.tdejadodefact2);
        tdejadodefact3= (TextView) getView().findViewById(R.id.tdejadodefact3);
        tdejadodefact4= (TextView) getView().findViewById(R.id.tdejadodefact4);
        tdejadodefact5= (TextView) getView().findViewById(R.id.tdejadodefact5);
        tdejadodefact6= (TextView) getView().findViewById(R.id.tdejadodefact6);
        tdejadodefact7= (TextView) getView().findViewById(R.id.tdejadodefact7);



        tdejadodefact1.setText("$"+Dato1 );
        tdejadodefact2.setText("$"+Dato2 );
        tdejadodefact3.setText("$"+Dato3 );
        tdejadodefact4.setText("$"+Dato4 );
        tdejadodefact5.setText("$"+Dato5 );
        tdejadodefact6.setText("$"+Dato6 );
        tdejadodefact7.setText("$"+Dato7 );



    }

}
