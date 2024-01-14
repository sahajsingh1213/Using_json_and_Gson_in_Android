package com.example.gson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.gson.Model.UserAdapter
import com.example.gson.Model.UserModelC
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView = findViewById<RecyclerView>(R.id.rvUsersList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val Adapter = UserAdapter(this, MakeJsonData())
        recyclerView.adapter = Adapter


    }

fun MakeJsonData():ArrayList<UserModelC>{
    var list:ArrayList<UserModelC> = ArrayList()
  try {


      val JSONObject = JSONObject(GetJsonFromAsset()!!)
      val UsersArray = JSONObject.getJSONArray("users")


      for (i in 0..UsersArray.length()) {
          var user = UsersArray.getJSONObject(i)
          var id = user.getInt("id")
          var name = user.getString("name")
          var email = user.getString("email")
          var gender = user.getString("gender")
          var weight = user.getDouble("weight")
          var height = user.getInt("height")

          var phone = user.getJSONObject("phone")
          var mobile = phone.getString("mobile")
          var office = phone.getString("office")
          list.add(UserModelC(id, name, email, gender, weight, height, mobile, office))

      }
  }
  catch (ex:Exception){
      println("$ex has occurred at MakeJsonData()")
  }




return list
}



    fun GetJsonFromAsset() : String?{
var json:String? = null
        try {
            val inputStream = assets.open("Users.Json")
            var size = inputStream.available()
            var buffer: ByteArray = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
           json = String(buffer, Charsets.UTF_8)
        }
        catch (ex:Exception){
            println("$ex has occurred at GetJsonFromAsset()")
        }
        return json
    }



}