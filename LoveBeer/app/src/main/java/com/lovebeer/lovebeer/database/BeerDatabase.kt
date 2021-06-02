package com.lovebeer.lovebeer.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lovebeer.lovebeer.data.Beer

@Database(entities=[Beer::class],version=1)
abstract class BeerDatabase : RoomDatabase() {

    abstract fun beerDao() : BeerDAO

    companion object {

        private var dbInstance : BeerDatabase? = null



        @Synchronized
        fun getDatabase(context: Context?) : BeerDatabase? {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context!!.applicationContext,
                    BeerDatabase::class.java,"ex"
                ).build()
            }
            return dbInstance
        }

    }

}