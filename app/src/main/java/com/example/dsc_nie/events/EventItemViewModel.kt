package com.example.dsc_nie.events

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class EventItemViewModel: ViewModel() {

    //LiveData Variables
    //-----------------------------------------------------
    private val _eventTitle = MutableLiveData<String>()
    val eventTitle: LiveData<String>
        get() = _eventTitle

    private val _eventDate = MutableLiveData<String>()
    val eventDate: LiveData<String>
        get() = _eventDate

    private val _eventDetails = MutableLiveData<String>()
    val eventDetails: LiveData<String>
        get() = _eventDetails

    private val _eventLocation = MutableLiveData<String>()
    val eventLocation: LiveData<String>
        get() = _eventLocation

    private val _eventImageURL = MutableLiveData<String>()
    val eventImageURL: LiveData<String>
        get() = _eventImageURL

    private val _eventImage = MutableLiveData<Bitmap>()
    val eventImage: LiveData<Bitmap>
        get() = _eventImage
    //-------------------------------------------------------

    //Firebase Database Reference
    lateinit var firebaseInstance: DatabaseReference

    init{
        Log.i("info", "ViewModel Created")
        initializeFirebaseInstance()
    }

    //Called when the ViewModel gets destroyed
    override fun onCleared() {
        super.onCleared()
        Log.i("info", "OnCleared called")
    }

    fun initializeFirebaseInstance(){
        firebaseInstance = FirebaseDatabase.getInstance().reference
    }

    //Gets the JSON Object from db and extracts values
    fun getChild(fileName: String) {
        firebaseInstance.child(fileName).get().addOnCompleteListener { it ->
            _eventTitle.value = it.result!!.child("Title").getValue() as String
            _eventDate.value = it.result!!.child("Date").getValue() as String
            _eventDetails.value = it.result!!.child("Details").getValue() as String
            _eventLocation.value = it.result!!.child("Location").getValue() as String
            _eventImageURL.value = it.result!!.child("ImageURL").getValue() as String
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    makeNetworkCall()
                }
            } catch (e: Exception) {
                Log.i("info", e.toString())
            }
        }
    }

    //To Load the Event Image
    suspend fun makeNetworkCall(){
        loadImage()
    }

    suspend fun loadImage() {
        _eventImage.postValue(Picasso.get().load(eventImageURL.value).get())
    }
}