package com.lovebeer.lovebeer

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lovebeer.lovebeer.data.Beer
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.net.HttpURLConnection
import java.net.UnknownHostException

class MainActivity : AppCompatActivity() {

    var list : MutableList<Beer> = mutableListOf()

    lateinit var edittext : EditText

    lateinit var button : Button

    lateinit var linearLayout : LinearLayout

    lateinit var recyclerView : RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edittext = findViewById(R.id.city_name)

        button = findViewById(R.id.button_search)

        linearLayout = findViewById(R.id.linear_layout)

        recyclerView = findViewById(R.id.recycler_view)

        button.setOnClickListener {
            var text = edittext.text.toString()
            if (text.isNotEmpty()) {
                LoadBeer(text).execute();
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        val beerAdapter = BeerAdapter()

        recyclerView.adapter = beerAdapter

    }


    inner class BeerAdapter : RecyclerView.Adapter<BeerAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerAdapter.ViewHolder {
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
            return ViewHolder(view)
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var cardView : CardView = view.findViewById(R.id.cardview)
            var image : ImageView = view.findViewById(R.id.cardview_image)
            var beerName : TextView = view.findViewById(R.id.beer_name)
            var beerAddress : TextView = view.findViewById(R.id.beer_address)
            var beerPhone : TextView = view.findViewById(R.id.beer_phone)
            var beerUrl : TextView = view.findViewById(R.id.beer_website)
        }

        override fun onBindViewHolder(holder: BeerAdapter.ViewHolder, position: Int) {
            var beer : Beer = list[position]

            // Controllare null
            holder.beerName.text = beer.name
            holder.beerAddress.text = beer.street
            holder.beerPhone.text = beer.phone
            holder.beerUrl.text = beer.website_url


        }

        override fun getItemCount(): Int {
            return list.size
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
            button.visibility = View.INVISIBLE
            edittext.visibility = View.INVISIBLE
            linearLayout.visibility = View.INVISIBLE
            var jsonArray : JSONArray? = JSONArray(result)
            if (jsonArray != null) {
                (0 until jsonArray.length()).forEach { i ->

                    var beer : Beer = Beer()

                    var jsonObject : JSONObject? = jsonArray.optJSONObject(i)
                    beer.id = jsonObject?.getInt("id")
                    beer.obdb_id = jsonObject?.getString("obdb_id")
                    beer.name = jsonObject?.getString("name")
                    beer.brewery_type = jsonObject?.getString("brewery_type")
                    beer.street = jsonObject?.getString("street")
                    beer.address2 = jsonObject?.getString("address_2")
                    beer.address3 = jsonObject?.getString("address_3")
                    beer.city = jsonObject?.getString("city")
                    beer.state = jsonObject?.getString("state")
                    beer.county_province = jsonObject?.getString("county_province")
                    beer.postalcode = jsonObject?.getString("postal_code")
                    beer.country = jsonObject?.getString("country")
                    beer.longitude = jsonObject?.getDouble("longitude")
                    beer.latitude = jsonObject?.getDouble("latitude")
                    beer.phone = jsonObject?.getString("phone")
                    beer.website_url = jsonObject?.getString("website_url")
                    beer.updated_at = jsonObject?.getString("updated_at")
                    beer.created_at = jsonObject?.getString("created_at")

                    list.add(beer)
                }
            }
        }

    }
}