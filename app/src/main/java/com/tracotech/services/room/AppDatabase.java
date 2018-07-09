package com.tracotech.services.room;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.tracotech.services.room.entities.UserEntity;


/**
 * Created by vishalm on 09/02/18.
 */
@Database(entities = {UserEntity.class},
        version = 1)
//@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase{

    AppDatabase(){

    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
