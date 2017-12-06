package com.johnduran.jganalytics;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraficaNivelServicioFragment extends Fragment {
    DatabaseReference myRef;
    Tablas ventas_diarias;
    private String Campos[]={"SEMANA","NS_PESOS_2016","NS_PESOS_2017","META"};
    public String Dato1,Dato2,Dato3,Dato4,Dato5,Dato6,Dato7,Dato8,Dato9,Dato10,
            Dato11,Dato12,Dato13,Dato14,Dato15,Dato16,Dato17,Dato18,Dato19,Dato20,Dato21
            ,Dato22,Dato23,Dato24,Dato25,Dato26,Dato27,Dato28,Dato29,Dato30,Dato31,Dato32
            ,Dato33,Dato34,Dato35,Dato36,Dato37,Dato38,Dato39,Dato40,Dato41,Dato42,Dato43
            ,Dato44,Dato45,Dato46,Dato47,Dato48,Dato49,Dato50,Dato51,Dato52;
    LineChart lineChart;
    private ArrayList<String> x_fechas;
    private ArrayList<Entry> y_dias,y_meta,y_acumulado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graficas, container, false);
        x_fechas = new ArrayList<>(); //En este caso es semana
        y_dias = new ArrayList<>();//En este caso es ns_pesos_2016
        y_acumulado = new ArrayList<>();//En este caso es ns_pesos_2017
        y_meta = new ArrayList<>();//En este caso es meta




        int i=0;
        while(i<4){
            leerDatos(i);
            Log.d("A","DATO2: "+Dato1);
            i++;
        }
        lineChart = (LineChart) v.findViewById(R.id.lineChart);
        return v;
    }

    public void leerDatos(int id){
        final int i=id;
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Nivel_Servicio_Grafica");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("A","AQUI: "+Campos[i]+ "  "+ i);
                if (dataSnapshot.child(Campos[i]).exists()) {
                    ventas_diarias = dataSnapshot.child(Campos[i]).getValue(Tablas.class);
                    obtenerDatos();
                    if(i==0){llenar_fechas();}
                    if(i==1){llenar_dias();}
                    if(i==2){llenar_acumulado();}
                    if(i==3){llenar_meta();}
                    String[] xaxes = new String[x_fechas.size()];
                    for(int l=0; l<x_fechas.size();l++){
                        xaxes[l] = x_fechas.get(l).toString();
                    }

                    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>(); //Arreglo con las dos graficas del eje y

                    LineDataSet lineDataSet1 = new LineDataSet(y_dias,"NS $ 2016");
                    lineDataSet1.setDrawCircles(false);
                    lineDataSet1.setColor(Color.BLUE);
                    LineDataSet lineDataSet2 = new LineDataSet(y_meta,"Meta");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setColor(Color.GREEN);

                    LineDataSet lineDataSet3 = new LineDataSet(y_acumulado,"NS $ 2017");
                    lineDataSet3.setDrawCircles(false);
                    lineDataSet3.setColor(Color.RED);

                    lineDataSets.add(lineDataSet1);
                    lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);

                    lineChart.setNoDataText("Gargando...");
                    lineChart.setNoDataTextDescription("");
                    lineChart.setVisibleXRangeMaximum(65f);
                    lineChart.setTouchEnabled(true);
                    lineChart.setDragEnabled(true);
                    lineChart.setDescription("Porcentaje");
                    lineChart.setData(new LineData(xaxes,lineDataSets));
                    lineChart.invalidate();


                }
            }
            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }
    public void llenar_dias() {

        y_dias.add(new Entry(Float.valueOf(Dato1)  ,0));
        y_dias.add(new Entry(Float.valueOf(Dato2)  ,1));
        y_dias.add(new Entry(Float.valueOf(Dato3)  ,2));
        y_dias.add(new Entry(Float.valueOf(Dato4)  ,3));
        y_dias.add(new Entry(Float.valueOf(Dato5)  ,4));
        y_dias.add(new Entry(Float.valueOf(Dato6)  ,5));
        y_dias.add(new Entry(Float.valueOf(Dato7)  ,6));
        y_dias.add(new Entry(Float.valueOf(Dato8)  ,7));
        y_dias.add(new Entry(Float.valueOf(Dato9)  ,8));
        y_dias.add(new Entry(Float.valueOf(Dato10)  ,9));
        y_dias.add(new Entry(Float.valueOf(Dato11)  ,10));
        y_dias.add(new Entry(Float.valueOf(Dato12)  ,11));
        y_dias.add(new Entry(Float.valueOf(Dato13)  ,12));
        y_dias.add(new Entry(Float.valueOf(Dato14)  ,13));
        y_dias.add(new Entry(Float.valueOf(Dato15)  ,14));
        y_dias.add(new Entry(Float.valueOf(Dato16)  ,15));
        y_dias.add(new Entry(Float.valueOf(Dato17)  ,16));
        y_dias.add(new Entry(Float.valueOf(Dato18)  ,17));
        y_dias.add(new Entry(Float.valueOf(Dato19)  ,18));
        y_dias.add(new Entry(Float.valueOf(Dato20)  ,19));
        y_dias.add(new Entry(Float.valueOf(Dato21)  ,20));
        y_dias.add(new Entry(Float.valueOf(Dato22)  ,21));
        y_dias.add(new Entry(Float.valueOf(Dato23)  ,22));
        y_dias.add(new Entry(Float.valueOf(Dato24)  ,23));
        y_dias.add(new Entry(Float.valueOf(Dato25)  ,24));
        y_dias.add(new Entry(Float.valueOf(Dato26)  ,25));
        y_dias.add(new Entry(Float.valueOf(Dato27)  ,26));
        y_dias.add(new Entry(Float.valueOf(Dato28)  ,27));
        y_dias.add(new Entry(Float.valueOf(Dato29)  ,28));
        y_dias.add(new Entry(Float.valueOf(Dato30)  ,29));
        y_dias.add(new Entry(Float.valueOf(Dato31)  ,30));
        y_dias.add(new Entry(Float.valueOf(Dato32)  ,31));
        y_dias.add(new Entry(Float.valueOf(Dato33)  ,32));
        y_dias.add(new Entry(Float.valueOf(Dato34)  ,33));
        y_dias.add(new Entry(Float.valueOf(Dato35)  ,34));
        y_dias.add(new Entry(Float.valueOf(Dato36)  ,35));
        y_dias.add(new Entry(Float.valueOf(Dato37)  ,36));
        y_dias.add(new Entry(Float.valueOf(Dato38)  ,37));
        y_dias.add(new Entry(Float.valueOf(Dato39)  ,38));
        y_dias.add(new Entry(Float.valueOf(Dato40)  ,39));
        y_dias.add(new Entry(Float.valueOf(Dato41)  ,40));
        y_dias.add(new Entry(Float.valueOf(Dato42)  ,41));
        y_dias.add(new Entry(Float.valueOf(Dato43)  ,42));
        y_dias.add(new Entry(Float.valueOf(Dato44)  ,43));
        y_dias.add(new Entry(Float.valueOf(Dato45)  ,44));
        y_dias.add(new Entry(Float.valueOf(Dato46)  ,45));
        y_dias.add(new Entry(Float.valueOf(Dato47)  ,46));
        y_dias.add(new Entry(Float.valueOf(Dato48)  ,47));
        y_dias.add(new Entry(Float.valueOf(Dato49)  ,48));
        y_dias.add(new Entry(Float.valueOf(Dato50)  ,49));
        y_dias.add(new Entry(Float.valueOf(Dato51)  ,50));
        y_dias.add(new Entry(Float.valueOf(Dato52)  ,51));

    }
    public void llenar_acumulado() {

        y_acumulado.add(new Entry(Float.valueOf(Dato1)  ,0));
        y_acumulado.add(new Entry(Float.valueOf(Dato2)  ,1));
        y_acumulado.add(new Entry(Float.valueOf(Dato3)  ,2));
        y_acumulado.add(new Entry(Float.valueOf(Dato4)  ,3));
        y_acumulado.add(new Entry(Float.valueOf(Dato5)  ,4));
        y_acumulado.add(new Entry(Float.valueOf(Dato6)  ,5));
        y_acumulado.add(new Entry(Float.valueOf(Dato7)  ,6));
        y_acumulado.add(new Entry(Float.valueOf(Dato8)  ,7));
        y_acumulado.add(new Entry(Float.valueOf(Dato9)  ,8));
        y_acumulado.add(new Entry(Float.valueOf(Dato10)  ,9));
        y_acumulado.add(new Entry(Float.valueOf(Dato11)  ,10));
        y_acumulado.add(new Entry(Float.valueOf(Dato12)  ,11));
        y_acumulado.add(new Entry(Float.valueOf(Dato13)  ,12));
        y_acumulado.add(new Entry(Float.valueOf(Dato14)  ,13));
        y_acumulado.add(new Entry(Float.valueOf(Dato15)  ,14));
        y_acumulado.add(new Entry(Float.valueOf(Dato16)  ,15));
        y_acumulado.add(new Entry(Float.valueOf(Dato17)  ,16));
        y_acumulado.add(new Entry(Float.valueOf(Dato18)  ,17));
        y_acumulado.add(new Entry(Float.valueOf(Dato19)  ,18));
        y_acumulado.add(new Entry(Float.valueOf(Dato20)  ,19));
        y_acumulado.add(new Entry(Float.valueOf(Dato21)  ,20));

        y_acumulado.add(new Entry(Float.valueOf(Dato22)  ,21));
        y_acumulado.add(new Entry(Float.valueOf(Dato23)  ,22));
        y_acumulado.add(new Entry(Float.valueOf(Dato24)  ,23));
        y_acumulado.add(new Entry(Float.valueOf(Dato25)  ,24));
        y_acumulado.add(new Entry(Float.valueOf(Dato26)  ,25));
        y_acumulado.add(new Entry(Float.valueOf(Dato27)  ,26));
        y_acumulado.add(new Entry(Float.valueOf(Dato28)  ,27));
        y_acumulado.add(new Entry(Float.valueOf(Dato29)  ,28));
        y_acumulado.add(new Entry(Float.valueOf(Dato30)  ,29));
        y_acumulado.add(new Entry(Float.valueOf(Dato31)  ,30));
        y_acumulado.add(new Entry(Float.valueOf(Dato32)  ,31));
        y_acumulado.add(new Entry(Float.valueOf(Dato33)  ,32));
        y_acumulado.add(new Entry(Float.valueOf(Dato34)  ,33));
        y_acumulado.add(new Entry(Float.valueOf(Dato35)  ,34));
        y_acumulado.add(new Entry(Float.valueOf(Dato36)  ,35));
        y_acumulado.add(new Entry(Float.valueOf(Dato37)  ,36));
        y_acumulado.add(new Entry(Float.valueOf(Dato38)  ,37));
        y_acumulado.add(new Entry(Float.valueOf(Dato39)  ,38));
        y_acumulado.add(new Entry(Float.valueOf(Dato40)  ,39));
        y_acumulado.add(new Entry(Float.valueOf(Dato41)  ,40));
        y_acumulado.add(new Entry(Float.valueOf(Dato42)  ,41));
        y_acumulado.add(new Entry(Float.valueOf(Dato43)  ,42));
        y_acumulado.add(new Entry(Float.valueOf(Dato44)  ,43));
        y_acumulado.add(new Entry(Float.valueOf(Dato45)  ,44));
        y_acumulado.add(new Entry(Float.valueOf(Dato46)  ,45));
        y_acumulado.add(new Entry(Float.valueOf(Dato47)  ,46));
        y_acumulado.add(new Entry(Float.valueOf(Dato48)  ,47));
        y_acumulado.add(new Entry(Float.valueOf(Dato49)  ,48));
        y_acumulado.add(new Entry(Float.valueOf(Dato50)  ,49));
        y_acumulado.add(new Entry(Float.valueOf(Dato51)  ,50));
        y_acumulado.add(new Entry(Float.valueOf(Dato52)  ,51));







    }
    public void llenar_meta() {

        y_meta.add(new Entry(Float.valueOf(Dato1)  ,0));
        y_meta.add(new Entry(Float.valueOf(Dato2)  ,1));
        y_meta.add(new Entry(Float.valueOf(Dato3)  ,2));
        y_meta.add(new Entry(Float.valueOf(Dato4)  ,3));
        y_meta.add(new Entry(Float.valueOf(Dato5)  ,4));
        y_meta.add(new Entry(Float.valueOf(Dato6)  ,5));
        y_meta.add(new Entry(Float.valueOf(Dato7)  ,6));
        y_meta.add(new Entry(Float.valueOf(Dato8)  ,7));
        y_meta.add(new Entry(Float.valueOf(Dato9)  ,8));
        y_meta.add(new Entry(Float.valueOf(Dato10)  ,9));
        y_meta.add(new Entry(Float.valueOf(Dato11)  ,10));
        y_meta.add(new Entry(Float.valueOf(Dato12)  ,11));
        y_meta.add(new Entry(Float.valueOf(Dato13)  ,12));
        y_meta.add(new Entry(Float.valueOf(Dato14)  ,13));
        y_meta.add(new Entry(Float.valueOf(Dato15)  ,14));
        y_meta.add(new Entry(Float.valueOf(Dato16)  ,15));
        y_meta.add(new Entry(Float.valueOf(Dato17)  ,16));
        y_meta.add(new Entry(Float.valueOf(Dato18)  ,17));
        y_meta.add(new Entry(Float.valueOf(Dato19)  ,18));
        y_meta.add(new Entry(Float.valueOf(Dato20)  ,19));
        y_meta.add(new Entry(Float.valueOf(Dato21)  ,20));

        y_meta.add(new Entry(Float.valueOf(Dato22)  ,21));
        y_meta.add(new Entry(Float.valueOf(Dato23)  ,22));

        y_meta.add(new Entry(Float.valueOf(Dato24)  ,23));
        y_meta.add(new Entry(Float.valueOf(Dato25)  ,24));
        y_meta.add(new Entry(Float.valueOf(Dato26)  ,25));
        y_meta.add(new Entry(Float.valueOf(Dato27)  ,26));
        y_meta.add(new Entry(Float.valueOf(Dato28)  ,27));
        y_meta.add(new Entry(Float.valueOf(Dato29)  ,28));
        y_meta.add(new Entry(Float.valueOf(Dato30)  ,29));
        y_meta.add(new Entry(Float.valueOf(Dato31)  ,30));
        y_meta.add(new Entry(Float.valueOf(Dato32)  ,31));
        y_meta.add(new Entry(Float.valueOf(Dato33)  ,32));
        y_meta.add(new Entry(Float.valueOf(Dato34)  ,33));
        y_meta.add(new Entry(Float.valueOf(Dato35)  ,34));
        y_meta.add(new Entry(Float.valueOf(Dato36)  ,35));
        y_meta.add(new Entry(Float.valueOf(Dato37)  ,36));
        y_meta.add(new Entry(Float.valueOf(Dato38)  ,37));
        y_meta.add(new Entry(Float.valueOf(Dato39)  ,38));
        y_meta.add(new Entry(Float.valueOf(Dato40)  ,39));
        y_meta.add(new Entry(Float.valueOf(Dato41)  ,40));
        y_meta.add(new Entry(Float.valueOf(Dato42)  ,41));
        y_meta.add(new Entry(Float.valueOf(Dato43)  ,42));
        y_meta.add(new Entry(Float.valueOf(Dato44)  ,43));
        y_meta.add(new Entry(Float.valueOf(Dato45)  ,44));
        y_meta.add(new Entry(Float.valueOf(Dato46)  ,45));
        y_meta.add(new Entry(Float.valueOf(Dato47)  ,46));
        y_meta.add(new Entry(Float.valueOf(Dato48)  ,47));
        y_meta.add(new Entry(Float.valueOf(Dato49)  ,48));
        y_meta.add(new Entry(Float.valueOf(Dato50)  ,49));
        y_meta.add(new Entry(Float.valueOf(Dato51)  ,50));
        y_meta.add(new Entry(Float.valueOf(Dato52)  ,51));



    }
    public void llenar_fechas() {
        x_fechas.add(String.valueOf(Dato1));
        x_fechas.add(String.valueOf(Dato2));
        x_fechas.add( String.valueOf(Dato3));
        x_fechas.add( String.valueOf(Dato4));
        x_fechas.add( String.valueOf(Dato5));
        x_fechas.add( String.valueOf(Dato6));
        x_fechas.add( String.valueOf(Dato7));
        x_fechas.add( String.valueOf(Dato8));
        x_fechas.add( String.valueOf(Dato9));
        x_fechas.add( String.valueOf(Dato10));
        x_fechas.add( String.valueOf(Dato11));
        x_fechas.add( String.valueOf(Dato12));
        x_fechas.add( String.valueOf(Dato13));
        x_fechas.add( String.valueOf(Dato14));
        x_fechas.add( String.valueOf(Dato15));
        x_fechas.add( String.valueOf(Dato16));
        x_fechas.add( String.valueOf(Dato17));
        x_fechas.add( String.valueOf(Dato18));
        x_fechas.add( String.valueOf(Dato19));
        x_fechas.add( String.valueOf(Dato20));
        x_fechas.add( String.valueOf(Dato21));
        x_fechas.add( String.valueOf(Dato22));
        x_fechas.add( String.valueOf(Dato23));
        x_fechas.add( String.valueOf(Dato24));
        x_fechas.add( String.valueOf(Dato25));
        x_fechas.add( String.valueOf(Dato26));
        x_fechas.add( String.valueOf(Dato27));
        x_fechas.add( String.valueOf(Dato28));
        x_fechas.add( String.valueOf(Dato29));
        x_fechas.add( String.valueOf(Dato30));
        x_fechas.add( String.valueOf(Dato31));
        x_fechas.add( String.valueOf(Dato32));
        x_fechas.add( String.valueOf(Dato33));
        x_fechas.add( String.valueOf(Dato34));
        x_fechas.add( String.valueOf(Dato35));
        x_fechas.add( String.valueOf(Dato36));
        x_fechas.add( String.valueOf(Dato37));
        x_fechas.add( String.valueOf(Dato38));
        x_fechas.add( String.valueOf(Dato39));
        x_fechas.add( String.valueOf(Dato40));
        x_fechas.add( String.valueOf(Dato41));
        x_fechas.add( String.valueOf(Dato42));
        x_fechas.add( String.valueOf(Dato43));
        x_fechas.add( String.valueOf(Dato44));
        x_fechas.add( String.valueOf(Dato45));
        x_fechas.add( String.valueOf(Dato46));
        x_fechas.add( String.valueOf(Dato47));
        x_fechas.add( String.valueOf(Dato48));
        x_fechas.add( String.valueOf(Dato49));
        x_fechas.add( String.valueOf(Dato50));
        x_fechas.add( String.valueOf(Dato51));
        x_fechas.add( String.valueOf(Dato52));
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
        Dato22=ventas_diarias.getDato22();
        Dato23=ventas_diarias.getDato23();
        Dato24=ventas_diarias.getDato24();
        Dato25=ventas_diarias.getDato25();
        Dato26=ventas_diarias.getDato26();
        Dato27=ventas_diarias.getDato27();
        Dato28=ventas_diarias.getDato28();
        Dato29=ventas_diarias.getDato29();
        Dato30=ventas_diarias.getDato30();
        Dato31=ventas_diarias.getDato31();
        Dato32=ventas_diarias.getDato32();
        Dato33=ventas_diarias.getDato33();
        Dato34=ventas_diarias.getDato34();
        Dato35=ventas_diarias.getDato35();
        Dato36=ventas_diarias.getDato36();
        Dato37=ventas_diarias.getDato37();
        Dato38=ventas_diarias.getDato38();
        Dato39=ventas_diarias.getDato39();
        Dato40=ventas_diarias.getDato40();
        Dato41=ventas_diarias.getDato41();
        Dato42=ventas_diarias.getDato42();
        Dato43=ventas_diarias.getDato43();
        Dato44=ventas_diarias.getDato44();
        Dato45=ventas_diarias.getDato45();
        Dato46=ventas_diarias.getDato46();
        Dato47=ventas_diarias.getDato47();
        Dato48=ventas_diarias.getDato48();
        Dato49=ventas_diarias.getDato49();
        Dato50=ventas_diarias.getDato50();
        Dato51=ventas_diarias.getDato51();
        Dato52=ventas_diarias.getDato52();
        Log.d("A","ARREGLO2: "+Dato1+Dato2+Dato3);
    }


}
