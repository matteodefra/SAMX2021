package com.lovebeer.lovebeer

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.HttpURLConnection
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var edittext : EditText = findViewById(R.id.city_name)

        var button : Button = findViewById(R.id.button_search)

        button.setOnClickListener {
            var text = edittext.text.toString()
            if (text.isNotEmpty()) {
                LoadBeer(text).execute();
            }
        }

    }


    inner class LoadBeer(search : String) : AsyncTask<String?,String?,String?>() {

        private val url : URL = URL("https://api.openbrewerydb.org/breweries?by_city=${search}")

        override fun doInBackground(vararg params: String?): String? {
            try {
                val httpClient = url.openConnection() as HttpURLConnection
                if (httpClient.responseCode == HttpURLConnection.HTTP_OK) {
                    try {
                        val stream = BufferedInputStream(httpClient.inputStream)
                        val bufferedReader = BufferedReader(InputStreamReader(stream))
                        val stringBuilder = StringBuilder()
                        bufferedReader.forEachLine { stringBuilder.append(it) }
                        return stringBuilder.toString()
                    } catch (e : Exception) {
                        Log.d("exce",e.toString())
                    }
                    finally {
                        httpClient.disconnect()
                    }
                }
                else {
                    return "nothing"
                }
            } catch (e : UnknownHostException) {
                return "nothing"
            }
            return "nothing"

        }

        override fun onPostExecute(result: String?) {
            if (result == "nothing") {
                return
            }
            var jsonArray : JSONArray? = JSONArray(result)
            if (jsonArray != null) {
                (0 until jsonArray.length()).forEach { i ->
                    var jsonObject : JSONObject? = jsonArray.optJSONObject(i)

                    var id : Int? = jsonObject?.getInt("id")
                    var obdb_id : String? = jsonObject?.getString("obdb_id")
                    var name : String? = jsonObject?.getString("name")
                    var brewery_type : String? = jsonObject?.getString("brewery_type")
                    var street : String? = jsonObject?.getString("street")
                    var address2 : String? = jsonObject?.getString("address_2")
                    var address3 : String? = jsonObject?.getString("address_3")
                    var city : String? = jsonObject?.getString("city")
                    var state : String? = jsonObject?.getString("state")
                    var county_province : String? = jsonObject?.getString("county_province")
                    var postalcode : String? = jsonObject?.getString("postal_code")
                    var country : String? = jsonObject?.getString("country")
                    var longitude : Double? = jsonObject?.getDouble("longitude")
                    var latitude : Double? = jsonObject?.getDouble("latitude")
                    var phone : String? = jsonObject?.getString("phone")
                    var website_url : String? = jsonObject?.getString("website_url")
                    var updated_at : String? = jsonObject?.getString("updated_at")
                    var created_at : String? = jsonObject?.getString("created_at")

                }
            }
        }

    }
}