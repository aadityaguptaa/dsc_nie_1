package com.example.dsc_nie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem


class HomeFragment : Fragment() {

    private var mainCategoryRecycler: RecyclerView? = null
    private var mainRecyclerAdapter: UpcomingEventsRecyclerAdapter? = null
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        // Inflate the layout for this fragment

        val categoryItemList: MutableList<CategoryItem> = ArrayList()

        categoryItemList.add(CategoryItem(1, R.drawable.adi))
        categoryItemList.add(CategoryItem(2, R.drawable.adi))
        categoryItemList.add(CategoryItem(3, R.drawable.adi))
        categoryItemList.add(CategoryItem(4, R.drawable.adi))
        categoryItemList.add(CategoryItem(5, R.drawable.adi))


        //Use 540*300 dimension images
        val categoryItemList2: MutableList<CategoryItem> = ArrayList()
        categoryItemList2.add(CategoryItem(6, R.drawable.aboutus))
        categoryItemList2.add(CategoryItem(7, R.drawable.leaderboard))

        val categoryItemList3: MutableList<CategoryItem> = ArrayList()
        categoryItemList3.add(CategoryItem(8, R.drawable.techteam))
        categoryItemList3.add(CategoryItem(9, R.drawable.managementteam))

        val categoryItemList4: MutableList<CategoryItem> = ArrayList()
        categoryItemList4.add(CategoryItem(10, R.drawable.pastevents))
        categoryItemList4.add(CategoryItem(11, R.drawable.comingsoon))


        var allCategory: MutableList<AllCategory> = ArrayList()
        allCategory.add(AllCategory("Upcoming Events ->", categoryItemList))
        allCategory.add(AllCategory("Menu", categoryItemList2))
        allCategory.add(AllCategory("", categoryItemList3))
        allCategory.add(AllCategory("", categoryItemList4))



        setMainCategoryRecycler(allCategory)

        return binding.root
    }

    private fun setMainCategoryRecycler(allCategory: List<AllCategory>){
        mainCategoryRecycler = binding.EventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = UpcomingEventsRecyclerAdapter(context, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }

}