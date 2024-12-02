package es.cm.dam.dos.pmdm.ludotecaroom;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private static AppDatabase database;

    public static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "discoteque"
            ).allowMainThreadQueries().build();
        }
        return database;
    }
}