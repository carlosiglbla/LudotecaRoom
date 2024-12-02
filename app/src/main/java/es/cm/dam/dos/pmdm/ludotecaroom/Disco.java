package es.cm.dam.dos.pmdm.ludotecaroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Year;

@Entity(tableName = "discos")
public class Disco {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombre;
    public String artista;
    public int anio;

    // Constructor
    public Disco(String nombre, String artista, int anio) {
        this.nombre = nombre;
        this.artista = artista;
        this.anio = anio;
    }


    @Override
    public String toString() {
        return "Disco{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", artista='" + artista + '\'' +
                ", anio=" + anio +
                '}';
    }
}