package com.lovebeer.lovebeer.mycontentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import com.lovebeer.lovebeer.data.Beer
import com.lovebeer.lovebeer.database.BeerDAO
import com.lovebeer.lovebeer.database.BeerDatabase

class MyContentProvider : ContentProvider() {


    private val MATCHER = UriMatcher(UriMatcher.NO_MATCH).apply {

        addURI(AUTHORITY,"BeerTable",1)

        addURI(AUTHORITY,"BeerTable/#",2)

    }


    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code : Int = MATCHER.match(uri)
        if (context == null) return null
        var beerDAO : BeerDAO = BeerDatabase.getDatabase(this.context)!!.beerDao()
        var cursor : Cursor?
        cursor = when(code) {
            1 -> {
                beerDAO.selectAll()
            }
            else -> {
                null
            }
        }
        cursor?.setNotificationUri(context!!.contentResolver,uri)
        return cursor
    }

    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            1 -> "vnd.android.cursor.dir/${AUTHORITY}.BeerTable"
            2 -> "vnd.android.cursor.item/${AUTHORITY}.BeerTable"
            else -> null
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        val code : Int = MATCHER.match(uri)
        if (context == null) return null
        var beerDAO : BeerDAO = BeerDatabase.getDatabase(this.context)!!.beerDao()
        return when (code) {
            1 -> {
                val id : Long? = beerDAO.insertBeer(Beer.fromContentValues(values))
                context!!.contentResolver.notifyChange(uri,null)
                ContentUris.withAppendedId(uri,id!!)
            }
            else -> {
                null
            }
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val code : Int = MATCHER.match(uri)
        val beerDAO : BeerDAO = BeerDatabase.getDatabase(context)!!.beerDao()
        when (code) {
            2 -> {
                val id : Int? = beerDAO.deleteBeer(ContentUris.parseId(uri))
                context!!.contentResolver.notifyChange(uri,null)
                return id!!
            }
            else -> {
                return -1
            }
        }
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }


    companion object {

        const val AUTHORITY = "com.lovebeer.lovebeer.mycontentprovider.MyContentProvider"
        val URI_BEER = Uri.parse("content://$AUTHORITY/BeerTable")
    }

}