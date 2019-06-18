package com.sheepeatsbear.roomwordsample;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

abstract class WordRoomDatabase extends RoomDatabase {

    abstract WordDao wordDao ();

    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {

            synchronized (WordRoomDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();
                }
            }


        }
        return INSTANCE;
    }


}
