package com.example.dsc_nie

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference

class HomeFragmentViewModel : ViewModel() {

    val storage = FirebaseStorage.getInstance()
    val storageRef = storage.reference.child("upcoming_events")
    val listAllTask: Task<ListResult> = storageRef.listAll()
    val imageList: ArrayList<String> = ArrayList()
    val done = MutableLiveData<Boolean>()
    var allCategory: MutableList<AllCategory> = ArrayList()

    val categoryItemList: MutableList<CategoryItem> = ArrayList()
    val categoryItemList2: MutableList<CategoryItem> = ArrayList()
    val categoryItemList3: MutableList<CategoryItem> = ArrayList()
    val categoryItemList4: MutableList<CategoryItem> = ArrayList()

    init {
        listAllTask.addOnCompleteListener { result ->
            listTask(result)
        }
    }

    private fun listTask(result: Task<ListResult>) {
        val items: List<StorageReference> = result.result!!.items
        if (items.size == 0) {
            categoryItemList.add(CategoryItem(-1, R.drawable.noupcomingevents))
            addCategoryItemList()
            addAllCategory()
            done.value = true
        } else {
            var count = 0;
            //add cycle for add image url to list
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageList.add("$it"); count++
                }.addOnCompleteListener {
                    if (count == items.size) {
                        for (i in 0..(count - 1)) {
                            categoryItemList.add(CategoryItem(i, -1, imageList[i]))
                        }
                        addCategoryItemList()
                        addAllCategory()
                        done.value = true
                    }
                }
            }
        }
    }


    fun addCategoryItemList() {
        //Use 540*300 dimensional images
        categoryItemList2.add(CategoryItem(R.string.aboutFragmentId, R.drawable.aboutus))
        categoryItemList2.add(CategoryItem(7, R.drawable.leaderboard))

        categoryItemList3.add(CategoryItem(R.string.techTeamId, R.drawable.techteam))
        categoryItemList3.add(CategoryItem(R.string.managementTeamId, R.drawable.managementteam))

        categoryItemList4.add(CategoryItem(10, R.drawable.pastevents))
        categoryItemList4.add(CategoryItem(11, R.drawable.comingsoon))
    }

    fun addAllCategory() {
        allCategory.add(AllCategory("Upcoming Events ->", categoryItemList))
        allCategory.add(AllCategory("Menu", categoryItemList2))
        allCategory.add(AllCategory("", categoryItemList3))
        allCategory.add(AllCategory("", categoryItemList4))
    }
}