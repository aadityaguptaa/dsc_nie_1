package com.example.dsc_nie.events

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dsc_nie.model.EventItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference

class PastEventsViewModel: ViewModel() {

    val PastEventItemList = MutableLiveData<List<EventItem>>()
    var done = MutableLiveData<Boolean>()

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference
    val imageList: ArrayList<String> = ArrayList()
    val listAllTask: Task<ListResult> = storageRef.listAll()

    init{
        Log.i("info", "Past Event ViewModel Created")
        addItems()
    }

    fun addItems() {
        listAllTask.addOnCompleteListener { result ->
            val items: List<StorageReference> = result.result!!.items
            var count = 0
            var list = mutableListOf<EventItem>()
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageList.add("$it"); count++
                    Log.i("ababab", "$it")
                }.addOnCompleteListener {
                    if (count == items.size) {
                        for (i in 0..(items.size - 1)) {
                            list.add(EventItem(i, imageList[i]))
                        }
                        PastEventItemList.value = list
                    }
                }
            }
        }
    }
}