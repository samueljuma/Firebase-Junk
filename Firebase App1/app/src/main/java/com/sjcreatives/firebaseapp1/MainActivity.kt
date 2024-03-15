package com.sjcreatives.firebaseapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.sjcreatives.firebaseapp1.data.User

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv : TextView= findViewById(R.id.tv)

        //Real Time database reference
        database = Firebase.database.reference

        //Writing custom data to firebase
        val user1 = User(0,"samueljuma", "Samuel", "Juma")
        database.child("Users").child(user1.userName).setValue(user1)

        //Read custom data from Firebase
        val postListener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue<User>()
                tv.text = user?.userName
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("TAGGY", "loadPost:onCancelled", error.toException())
            }

        }
            database.child("Users").child(user1.userName).addValueEventListener(postListener)




//        //write Data to Firebase
//        database.child("users").setValue("samueljuma")
//
//        //Read Data from Firebase
//        val postListener = object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val user = snapshot.value
//                tv.text = user.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        }
//        database.child("users").addValueEventListener(postListener)


    }
}