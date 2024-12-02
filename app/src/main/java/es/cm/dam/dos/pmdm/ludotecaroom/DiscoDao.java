package es.cm.dam.dos.pmdm.ludotecaroom;

//import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiscoDao {
    @Insert
    void insertarDisco(Disco disco);

    @Query("SELECT * FROM discos WHERE id = :id")
    Disco obtenerDiscoPorId(int id);

    @Query("SELECT * FROM discos")
    List<Disco> obtenerTodosLosDiscos();

    @Query("DELETE FROM discos WHERE id = :id")
    void eliminarDisco(int id);

    @Query("DELETE FROM discos")
    void eliminarTodosLosDisco();

}