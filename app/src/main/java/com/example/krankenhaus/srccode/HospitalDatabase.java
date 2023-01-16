package com.example.krankenhaus.srccode;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.krankenhaus.srccode.dao.BedDao;
import com.example.krankenhaus.srccode.dao.BloodTestDao;
import com.example.krankenhaus.srccode.dao.MRIDao;
import com.example.krankenhaus.srccode.dao.PatientDao;
import com.example.krankenhaus.srccode.dao.RecordDao;
import com.example.krankenhaus.srccode.dao.VisitDao;
import com.example.krankenhaus.srccode.entities.Bed;
import com.example.krankenhaus.srccode.entities.BloodTest;
import com.example.krankenhaus.srccode.entities.MRI;
import com.example.krankenhaus.srccode.entities.Patient;
import com.example.krankenhaus.srccode.entities.Record;
import com.example.krankenhaus.srccode.entities.Visit;

@Database(
        entities = {
                Bed.class,
                BloodTest.class,
                MRI.class,
                Patient.class,
                Record.class,
                Visit.class
        },
        version = 5,
        exportSchema = false
)
public abstract class HospitalDatabase extends RoomDatabase {
    private static HospitalDatabase instance;
    private static final String DATABASE_NAME = "hospital";

    public abstract BedDao bedDao();
    public abstract BloodTestDao bloodTestDao();
    public abstract MRIDao mriDao();
    public abstract PatientDao patientDao();
    public abstract RecordDao recordDao();
    public abstract VisitDao visitDao();

    public static synchronized HospitalDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HospitalDatabase.class, "hospital_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private PatientDao patientDao;
        private BedDao bedDao;

        private PopulateDbAsyncTask(HospitalDatabase hospitalDatabase) {
            patientDao = hospitalDatabase.patientDao();
            bedDao = hospitalDatabase.bedDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
