package es.cm.dam.dos.pmdm.ludotecaroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Disco.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiscoDao discoDao();
}