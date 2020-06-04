package com.example.sopaletras;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class Juego {

    private ArrayList<ArrayList<String>> tablero;
    private String[] arrayPalabras = {"LONDRES", "BOGOTA", "MADRID", "PARIS", "ARGEL", "PIONYANG", "DUBLIN", "TOKYO"};
    private String[] arrayLetras = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int ancho = 10;
    private int alto = 10;

    public Juego(){
        tablero = new ArrayList<ArrayList<String>>();
        for(int i = 0; i<this.alto; i++){
            this.tablero.add(new ArrayList<String>());
            for(int j = 0; j<this.ancho; j++){
                this.tablero.get(i).add("#");

            }
        }
        generarTablero();
    }

    private void generarTablero(){
        boolean noValido;
        int contador = 5;
        ArrayList<String> arrayPalabrasRepetidas = new ArrayList<String>();
        arrayPalabrasRepetidas.add("EJEMPLO");
        do{
            Palabra palabra;
            do{
                noValido = false;

                palabra = new Palabra();

                for(String pal : arrayPalabrasRepetidas){
                    if(pal == palabra.palabra){
                        noValido = true;
                    }
                }

                if(!noValido){
                    for(int i = 0; i< palabra.palabraArray.length; i++){
                        if(palabra.x < 0 || palabra.y <0 || palabra.x>=alto || palabra.y>=ancho){
                            noValido = true;
                        }else{
                            Log.d("Tablero", "palabrax: "+palabra.x+" palabray: "+palabra.y);
                            if(this.tablero.get(palabra.x).get(palabra.y) != "#"){
                                if(this.tablero.get(palabra.x).get(palabra.y) != palabra.palabraArray[i]){
                                    noValido = true;
                                }
                            }
                        }
                        palabra.calculaDireccion();
                    }
                }
            }while(noValido);
            arrayPalabrasRepetidas.add(palabra.palabra);
            Log.d("Tablero", "contador: "+contador);
            actualizarTablero(palabra);
            contador--;
        }while(contador != 0);
    }

    private void actualizarTablero(Palabra palabra){
        palabra.revertirPalabra();
        for(int i = 0; i< palabra.palabraArray.length; i++){
            Log.d("Tablero", "palabrax: "+palabra.x+" palabray: "+palabra.y);
            this.tablero.get(palabra.x).set(palabra.y, palabra.palabraArray[i]);
            palabra.calculaDireccion();
        }
    }

    public ArrayList<ArrayList<String>> getTablero(){
        return this.tablero;
    }



}
