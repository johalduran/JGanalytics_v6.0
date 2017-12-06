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
public class GraficaVentasBucaramangaFragment extends Fragment {
    DatabaseReference myRef;
    Tablas ventas_diarias;
    private String Campos[]={"FECHA","VENTA_DEL_DIA","META","ACUMULADO"};
    public String Dato1,Dato2,Dato3,Dato4,Dato5,Dato6,Dato7,Dato8,Dato9,Dato10,
            Dato11,Dato12,Dato13,Dato14,Dato15,Dato16,Dato17,Dato18,Dato19,Dato20,Dato21;
    LineChart lineChart;
    private ArrayList<String> x_fechas;
    private ArrayList<Entry> y_dias,y_meta,y_acumulado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_graficas, container, false);
        x_fechas = new ArrayList<>();
        y_dias = new ArrayList<>();
        y_meta = new ArrayList<>();
        y_acumulado = new ArrayList<>();

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
        myRef = database.getReference("Ventas_Diarias_Bucaramanga");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("A","AQUI: "+Campos[i]+ "  "+ i);
                if (dataSnapshot.child(Campos[i]).exists()) {
                    ventas_diarias = dataSnapshot.child(Campos[i]).getValue(Tablas.class);
                    obtenerDatos();
                    if(i==0){llenar_fechas();}
                    if(i==1){llenar_dias();}
                    if(i==2){llenar_meta();}
                    if(i==3){llenar_acumulado();}
                    String[] xaxes = new String[x_fechas.size()];
                    for(int l=0; l<x_fechas.size();l++){
                        xaxes[l] = x_fechas.get(l).toString();
                    }

                    ArrayList<ILineDataSet> lineDataSets = new ArrayList<>(); //Arreglo con las dos graficas del eje y

                    LineDataSet lineDataSet1 = new LineDataSet(y_dias,"Venta Diaria");
                    lineDataSet1.setDrawCircles(false);
                    lineDataSet1.setColor(Color.BLUE);
                    LineDataSet lineDataSet2 = new LineDataSet(y_meta,"Meta");
                    lineDataSet2.setDrawCircles(false);
                    lineDataSet2.setColor(Color.GREEN);

                    LineDataSet lineDataSet3 = new LineDataSet(y_acumulado,"Acumulado");
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
                    lineChart.setDescription("Millones de pesos");
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

        y_dias.add(new Entry(Float.valueOf(Dato1)/1000000,0));
        y_dias.add(new Entry(Float.valueOf(Dato2)/1000000,1));
        y_dias.add(new Entry(Float.valueOf(Dato3)/1000000,2));
        y_dias.add(new Entry(Float.valueOf(Dato4)/1000000,3));
        y_dias.add(new Entry(Float.valueOf(Dato5)/1000000,4));
        y_dias.add(new Entry(Float.valueOf(Dato6)/1000000,5));
        y_dias.add(new Entry(Float.valueOf(Dato7)/1000000,6));
        y_dias.add(new Entry(Float.valueOf(Dato8)/1000000,7));
        y_dias.add(new Entry(Float.valueOf(Dato9)/1000000,8));
        y_dias.add(new Entry(Float.valueOf(Dato10)/1000000,9));
        y_dias.add(new Entry(Float.valueOf(Dato11)/1000000,10));
        y_dias.add(new Entry(Float.valueOf(Dato12)/1000000,11));
        y_dias.add(new Entry(Float.valueOf(Dato13)/1000000,12));
        y_dias.add(new Entry(Float.valueOf(Dato14)/1000000,13));
        y_dias.add(new Entry(Float.valueOf(Dato15)/1000000,14));
        y_dias.add(new Entry(Float.valueOf(Dato16)/1000000,15));
        y_dias.add(new Entry(Float.valueOf(Dato17)/1000000,16));
        y_dias.add(new Entry(Float.valueOf(Dato18)/1000000,17));
        y_dias.add(new Entry(Float.valueOf(Dato19)/1000000,18));
        y_dias.add(new Entry(Float.valueOf(Dato20)/1000000,19));
        y_dias.add(new Entry(Float.valueOf(Dato21)/1000000,20));


    }
    public void llenar_acumulado() {

        y_acumulado.add(new Entry(Float.valueOf(Dato1)/1000000,0));
        y_acumulado.add(new Entry(Float.valueOf(Dato2)/1000000,1));
        y_acumulado.add(new Entry(Float.valueOf(Dato3)/1000000,2));
        y_acumulado.add(new Entry(Float.valueOf(Dato4)/1000000,3));
        y_acumulado.add(new Entry(Float.valueOf(Dato5)/1000000,4));
        y_acumulado.add(new Entry(Float.valueOf(Dato6)/1000000,5));
        y_acumulado.add(new Entry(Float.valueOf(Dato7)/1000000,6));
        y_acumulado.add(new Entry(Float.valueOf(Dato8)/1000000,7));
        y_acumulado.add(new Entry(Float.valueOf(Dato9)/1000000,8));
        y_acumulado.add(new Entry(Float.valueOf(Dato10)/1000000,9));
        y_acumulado.add(new Entry(Float.valueOf(Dato11)/1000000,10));
        y_acumulado.add(new Entry(Float.valueOf(Dato12)/1000000,11));
        y_acumulado.add(new Entry(Float.valueOf(Dato13)/1000000,12));
        y_acumulado.add(new Entry(Float.valueOf(Dato14)/1000000,13));
        y_acumulado.add(new Entry(Float.valueOf(Dato15)/1000000,14));
        y_acumulado.add(new Entry(Float.valueOf(Dato16)/1000000,15));
        y_acumulado.add(new Entry(Float.valueOf(Dato17)/1000000,16));
        y_acumulado.add(new Entry(Float.valueOf(Dato18)/1000000,17));
        y_acumulado.add(new Entry(Float.valueOf(Dato19)/1000000,18));
        y_acumulado.add(new Entry(Float.valueOf(Dato20)/1000000,19));
        y_acumulado.add(new Entry(Float.valueOf(Dato21)/1000000,20));


    }
    public void llenar_meta() {

        y_meta.add(new Entry(Float.valueOf(Dato1)/1000000,0));
        y_meta.add(new Entry(Float.valueOf(Dato2)/1000000,1));
        y_meta.add(new Entry(Float.valueOf(Dato3)/1000000,2));
        y_meta.add(new Entry(Float.valueOf(Dato4)/1000000,3));
        y_meta.add(new Entry(Float.valueOf(Dato5)/1000000,4));
        y_meta.add(new Entry(Float.valueOf(Dato6)/1000000,5));
        y_meta.add(new Entry(Float.valueOf(Dato7)/1000000,6));
        y_meta.add(new Entry(Float.valueOf(Dato8)/1000000,7));
        y_meta.add(new Entry(Float.valueOf(Dato9)/1000000,8));
        y_meta.add(new Entry(Float.valueOf(Dato10)/1000000,9));
        y_meta.add(new Entry(Float.valueOf(Dato11)/1000000,10));
        y_meta.add(new Entry(Float.valueOf(Dato12)/1000000,11));
        y_meta.add(new Entry(Float.valueOf(Dato13)/1000000,12));
        y_meta.add(new Entry(Float.valueOf(Dato14)/1000000,13));
        y_meta.add(new Entry(Float.valueOf(Dato15)/1000000,14));
        y_meta.add(new Entry(Float.valueOf(Dato16)/1000000,15));
        y_meta.add(new Entry(Float.valueOf(Dato17)/1000000,16));
        y_meta.add(new Entry(Float.valueOf(Dato18)/1000000,17));
        y_meta.add(new Entry(Float.valueOf(Dato19)/1000000,18));
        y_meta.add(new Entry(Float.valueOf(Dato20)/1000000,19));
        y_meta.add(new Entry(Float.valueOf(Dato21)/1000000,20));


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

        Log.d("A","ARREGLO2: "+Dato1+Dato2+Dato3);


    }


}
