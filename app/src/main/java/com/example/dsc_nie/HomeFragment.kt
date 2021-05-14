package com.example.dsc_nie

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.INVISIBLE
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference


class HomeFragment : Fragment() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: UpcomingEventsRecyclerAdapter? = null
    lateinit var binding: FragmentHomeBinding
    lateinit var image: Bitmap
    val imageList: ArrayList<String> = ArrayList()
    lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        //navController = Navigation.findNavController(requireView())
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference.child("upcoming_events")
        val listAllTask: Task<ListResult> = storageRef.listAll()
        listAllTask.addOnCompleteListener { result ->
            listTask(result)
        }

        setHasOptionsMenu(true)
        //requireActivity().actionBar!!.isHideOnContentScrollEnabled = true

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.top_app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        navController = Navigation.findNavController(requireView())
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item)
    }
    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = binding.EventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = UpcomingEventsRecyclerAdapter(context, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }

    private fun listTask(result: Task<ListResult>) {
        Log.i("abc", result.toString())
        val items: List<StorageReference> = result.result!!.items
        if(items.size == 0){
            val categoryItemList: MutableList<CategoryItem> = ArrayList()
            val categoryItemList2: MutableList<CategoryItem> = ArrayList()

            categoryItemList.add(CategoryItem(-1, R.drawable.noupcomingevents))
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
            binding.homeFragmentProgressBar.visibility = View.GONE

            setMainCategoryRecycler(allCategory)
        }else {
            var count = 0;
            //add cycle for add image url to list
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageList.add("$it"); count++
                }.addOnCompleteListener {
                    val categoryItemList: MutableList<CategoryItem> = ArrayList()
                    if (count == items.size) {
                        for (i in 0..(count - 1)) {
                            categoryItemList.add(CategoryItem(i, -1, imageList[i]))
                        }

                        //Use 540*300 dimension images
                        val categoryItemList2: MutableList<CategoryItem> = ArrayList()
                        categoryItemList2.add(CategoryItem(R.string.aboutFragmentId, R.drawable.aboutus))
                        categoryItemList2.add(CategoryItem(7, R.drawable.leaderboard))

                        val categoryItemList3: MutableList<CategoryItem> = ArrayList()
                        categoryItemList3.add(CategoryItem(R.string.techTeamId, R.drawable.techteam))
                        categoryItemList3.add(CategoryItem(R.string.managementTeamId, R.drawable.managementteam))

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
    }

}