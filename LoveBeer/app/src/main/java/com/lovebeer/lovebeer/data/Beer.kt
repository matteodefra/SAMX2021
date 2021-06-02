package com.lovebeer.lovebeer.data

import android.content.ContentValues
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Beer")
data class Beer constructor (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID",index = true) var ID : Long? = 0,
    @ColumnInfo(name= COLUMN_ID) var id : Int? = 0,
    @ColumnInfo(name= COLUMN_OBDB_ID) var obdb_id : String? = "",
    @ColumnInfo(name= COLUMN_NAME) var name : String? = "",
    @ColumnInfo(name= COLUMN_BREWERY_TYPE) var brewery_type : String? = "",
    @ColumnInfo(name= COLUMN_STREET) var street : String? = "",
    @ColumnInfo(name= COLUMN_ADDRESS2) var address2 : String? = "",
    @ColumnInfo(name= COLUMN_ADDRESS3) var address3 : String? = "",
    @ColumnInfo(name= COLUMN_CITY) var city : String? = "",
    @ColumnInfo(name= COLUMN_STATE) var state : String? = "",
    @ColumnInfo(name= COLUMN_COUNTY_PROVINCE) var county_province : String? = "",
    @ColumnInfo(name= COLUMN_POSTALCODE) var postalcode : String? = "",
    @ColumnInfo(name= COLUMN_COUNTRY) var country : String? = "",
    @ColumnInfo(name= COLUMN_LONGITUDE) var longitude : Double? = 0.0,
    @ColumnInfo(name= COLUMN_LATITUDE) var latitude : Double? = 0.0,
    @ColumnInfo(name= COLUMN_PHONE) var phone : String? = "",
    @ColumnInfo(name= COLUMN_WEBSITE_URL) var website_url : String? = "",
    @ColumnInfo(name= COLUMN_UPDATED_AT) var updated_at : String? = "",
    @ColumnInfo(name= COLUMN_CREATED_AT) var created_at : String? = "",
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        ID?.let { dest?.writeLong(it) }
        id?.let { dest?.writeInt(it) }
        obdb_id?.let { dest?.writeString(it) }
        name?.let { dest?.writeString(it) }
        brewery_type?.let { dest?.writeString(it) }
        street?.let { dest?.writeString(it) }
        address2?.let { dest?.writeString(it) }
        address3?.let { dest?.writeString(it) }
        city?.let { dest?.writeString(it) }
        state?.let { dest?.writeString(it) }
        county_province?.let { dest?.writeString(it) }
        postalcode?.let { dest?.writeString(it) }
        country?.let { dest?.writeString(it) }
        longitude?.let { dest?.writeDouble(it) }
        latitude?.let { dest?.writeDouble(it) }
        phone?.let { dest?.writeString(it) }
        website_url?.let { dest?.writeString(it) }
        updated_at?.let { dest?.writeString(it) }
        created_at?.let { dest?.writeString(it) }
    }

    companion object CREATOR : Parcelable.Creator<Beer> {

        const val COLUMN_LONG_ID : String = "ID"
        const val COLUMN_ID : String = "id"
        const val COLUMN_OBDB_ID : String = "obdb_id"
        const val COLUMN_NAME : String = "name"
        const val COLUMN_BREWERY_TYPE : String = "brewery_type"
        const val COLUMN_STREET : String = "street"
        const val COLUMN_ADDRESS2 : String = "address2"
        const val COLUMN_ADDRESS3 : String = "address3"
        const val COLUMN_CITY : String = "city"
        const val COLUMN_STATE : String = "state"
        const val COLUMN_COUNTY_PROVINCE : String = "county_province"
        const val COLUMN_POSTALCODE : String = "postalcode"
        const val COLUMN_COUNTRY : String = "country"
        const val COLUMN_LONGITUDE : String = "longitude"
        const val COLUMN_LATITUDE : String = "latitude"
        const val COLUMN_PHONE : String = "phone"
        const val COLUMN_WEBSITE_URL : String = "website_url"
        const val COLUMN_UPDATED_AT : String = "updated_at"
        const val COLUMN_CREATED_AT : String = "created_at"

        fun fromContentValues(values: ContentValues?) : Beer {
            var beer = Beer()
            if (values != null && values.containsKey(COLUMN_LONG_ID)) {
                beer.ID = values.getAsLong(COLUMN_LONG_ID)
            }
            if (values != null && values.containsKey(COLUMN_ID)) {
                beer.id = values.getAsInteger(COLUMN_ID)
            }
            if (values != null && values.containsKey(COLUMN_OBDB_ID)) {
                beer.obdb_id = values.getAsString(COLUMN_OBDB_ID)
            }
            if (values != null && values.containsKey(COLUMN_NAME)) {
                beer.name = values.getAsString(COLUMN_NAME)
            }
            if (values != null && values.containsKey(COLUMN_BREWERY_TYPE)) {
                beer.brewery_type = values.getAsString(COLUMN_BREWERY_TYPE)
            }
            if (values != null && values.containsKey(COLUMN_STREET)) {
                beer.street = values.getAsString(COLUMN_STREET)
            }
            if (values != null && values.containsKey(COLUMN_ADDRESS2)) {
                beer.address2 = values.getAsString(COLUMN_ADDRESS2)
            }
            if (values != null && values.containsKey(COLUMN_ADDRESS3)) {
                beer.address3 = values.getAsString(COLUMN_ADDRESS3)
            }
            if (values != null && values.containsKey(COLUMN_CITY)) {
                beer.city = values.getAsString(COLUMN_CITY)
            }
            if (values != null && values.containsKey(COLUMN_STATE)) {
                beer.state = values.getAsString(COLUMN_STATE)
            }
            if (values != null && values.containsKey(COLUMN_COUNTY_PROVINCE)) {
                beer.county_province = values.getAsString(COLUMN_COUNTY_PROVINCE)
            }
            if (values != null && values.containsKey(COLUMN_POSTALCODE)) {
                beer.postalcode = values.getAsString(COLUMN_POSTALCODE)
            }
            if (values != null && values.containsKey(COLUMN_COUNTRY)) {
                beer.country = values.getAsString(COLUMN_COUNTRY)
            }
            if (values != null && values.containsKey(COLUMN_LONGITUDE)) {
                beer.longitude = values.getAsDouble(COLUMN_LONGITUDE)
            }
            if (values != null && values.containsKey(COLUMN_LATITUDE)) {
                beer.latitude = values.getAsDouble(COLUMN_LATITUDE)
            }
            if (values != null && values.containsKey(COLUMN_PHONE)) {
                beer.phone = values.getAsString(COLUMN_PHONE)
            }
            if (values != null && values.containsKey(COLUMN_WEBSITE_URL)) {
                beer.website_url = values.getAsString(COLUMN_WEBSITE_URL)
            }
            if (values != null && values.containsKey(COLUMN_UPDATED_AT)) {
                beer.updated_at = values.getAsString(COLUMN_UPDATED_AT)
            }
            if (values != null && values.containsKey(COLUMN_CREATED_AT)) {
                beer.created_at = values.getAsString(COLUMN_CREATED_AT)
            }
            return beer
        }


        override fun createFromParcel(parcel: Parcel): Beer {
            return Beer(parcel)
        }

        override fun newArray(size: Int): Array<Beer?> {
            return arrayOfNulls(size)
        }
    }

}

