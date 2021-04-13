package com.example.dsc_nie

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dsc_nie.adapter.EventAdapter
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: UpcomingEventsRecyclerAdapter? = null
    lateinit var binding: FragmentHomeBinding
    lateinit var  firebaseStorage: FirebaseStorage
    lateinit var firebaseReference: StorageReference
    lateinit var image: Bitmap
    val categoryItemList: MutableList<CategoryItem> = ArrayList()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val storage = FirebaseStorage.getInstance()
        Log.i("abc", storage.toString())
        val storageRef = storage.reference
        Log.i("abc", storageRef.toString())
        val imageList: ArrayList<String> = ArrayList()
        var im : Int
        val listAllTask: Task<ListResult> = storageRef.listAll()

        listAllTask.addOnCompleteListener { result ->
            Log.i("abc", result.toString())
            val items: List<StorageReference> = result.result!!.items
            var count = 0;
            //add cycle for add image url to list
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageList.add("$it"); count++
                }.addOnCompleteListener {
                    if(count == items.size) {
                        for(i in 0..(count-1)){ categoryItemList.add(CategoryItem(i, -1, imageList[i])) }

                        //Use 540*300 dimension images
                        val categoryItemList2: MutableList<CategoryItem> = ArrayList()
                        categoryItemList2.add(CategoryItem(R.string.aboutFragmentId, R.drawable.aboutus))
                        categoryItemList2.add(CategoryItem(7, R.drawable.leaderboard))

                        val categoryItemList3: MutableList<CategoryItem> = ArrayList()
                        categoryItemList3.add(CategoryItem(R.string.techTeamId , R.drawable.techteam))
                        categoryItemList3.add(CategoryItem(R.string.managementTeamId , R.drawable.managementteam))

                        val categoryItemList4: MutableList<CategoryItem> = ArrayList()
                        categoryItemList4.add(CategoryItem(10, R.drawable.pastevents))
                        categoryItemList4.add(CategoryItem(11, R.drawable.comingsoon))


                        var allCategory: MutableList<AllCategory> = ArrayList()
                        allCategory.add(AllCategory("Upcoming Events ->", categoryItemList))
                        allCategory.add(AllCategory("Menu", categoryItemList2))
                        allCategory.add(AllCategory("", categoryItemList3))
                        allCategory.add(AllCategory("", categoryItemList4))

                        setMainCategoryRecycler(allCategory)
                        binding.homeFragmentProgressBar.visibility = INVISIBLE
                    }
                }
            }
        }

        /*val firebaseUser: FirebaseUser = FirebaseAuth.getInstance().currentUser

        if(firebaseUser != null){

        }*/

        return binding.root
    }

    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = binding.EventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = UpcomingEventsRecyclerAdapter(context, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }

    /*private fun executeGetImage(url: String){
        CoroutineScope(IO).launch {
            val im = getImage(url)
        }
    }*/

    /*private suspend fun getImage(url: String){
        image= Picasso.get().load(url).get()
        categoryItemList.add(CategoryItem(1, image))
        Log.i("abc", image.toString())
    }*/

}