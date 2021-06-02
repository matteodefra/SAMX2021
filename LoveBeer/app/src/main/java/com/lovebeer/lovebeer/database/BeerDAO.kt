@file:Suppress("AndroidUnresolvedRoomSqlReference")

package com.lovebeer.lovebeer.database

import android.database.Cursor
import androidx.room.*
import com.lovebeer.lovebeer.data.Beer

@Dao
interface BeerDAO {

    @Query("SELECT * FROM BeerTable")
    fun selectAll() : Cursor?;


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beer : Beer) : Long?


    @Query("DELETE FROM BeerTable WHERE BeerTable.ID = :id")
    fun deleteBeer(id: Long) : Int?

}