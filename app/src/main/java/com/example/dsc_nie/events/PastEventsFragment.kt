package com.example.dsc_nie.events

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dsc_nie.R
import com.example.dsc_nie.adapter.PastEventItemAdapter
import com.example.dsc_nie.adapter.UpcomingEventsRecyclerAdapter
import com.example.dsc_nie.databinding.FragmentHomeBinding
import com.example.dsc_nie.databinding.FragmentPastEventsBinding
import com.example.dsc_nie.model.AllCategory
import com.example.dsc_nie.model.CategoryItem
import com.example.dsc_nie.model.EventItem
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference


class PastEventsFragment : Fragment() {


    val PastEventItemList: MutableList<EventItem> = ArrayList()
    private var pastEventsRecycler: RecyclerView? = null
    private var pastEventsRecyclerAdapter: PastEventItemAdapter? = null
    lateinit var binding: FragmentPastEventsBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_past_events, container, false
        )
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageList: ArrayList<String> = ArrayList()
        val listAllTask: Task<ListResult> = storageRef.listAll()

        listAllTask.addOnCompleteListener { result ->
            val items: List<StorageReference> = result.result!!.items
            var count = 0
            items.forEachIndexed { index, item ->
                item.downloadUrl.addOnSuccessListener {
                    imageList.add("$it"); count++
                    Log.i("ababab", "$it")
                }.addOnCompleteListener {
                    if (count == items.size) {
                        for (i in 0..(count - 1)) {
                            PastEventItemList.add(EventItem(i, imageList[i]))
                        }
                        setPastEventRecycler(PastEventItemList)
                        binding.PastEventFragmentProgressBar.visibility = View.GONE
                        binding.pastEventsRecyclerView.visibility = View.VISIBLE

                    }
                }
            }
        }
        return binding.root
    }

    private fun setPastEventRecycler(Events: List<EventItem>){
        pastEventsRecycler = binding.pastEventsRecyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        pastEventsRecycler!!.layoutManager = layoutManager
        pastEventsRecyclerAdapter = PastEventItemAdapter(requireContext(), Events)
        pastEventsRecycler!!.adapter = pastEventsRecyclerAdapter
    }


}