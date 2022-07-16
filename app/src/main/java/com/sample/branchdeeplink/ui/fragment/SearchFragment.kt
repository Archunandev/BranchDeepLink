package com.sample.branchdeeplink.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.gson.JsonObject
import com.sample.branchdeeplink.R
import com.sample.branchdeeplink.databinding.FragmentSearchBinding
import com.sample.branchdeeplink.ui.activity.DashboardActivity
import com.sample.branchdeeplink.viewmodel.BranchViewModel

class SearchFragment : Fragment() {

    private lateinit var searchBinding: FragmentSearchBinding

    lateinit var viewModel: BranchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return searchBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as DashboardActivity).viewModel

        searchBinding.discoverSearch.setOnClickListener {
            PostSearch()
        }

        viewModel.branchLink.observe(viewLifecycleOwner, Observer { response ->
            Log.e("Branch Api", "${response.branch_view_enabled}")
        })

    }

    private fun PostSearch() {
        try {
            val jsonObject = JsonObject()
            val jsonObject1 = JsonObject()

            jsonObject.addProperty(
                "branch_key",
                resources.getString(R.string.branch_live_key)
            )
            jsonObject.addProperty(
                "name",
                "SEARCH"
            )
            jsonObject.add(
                "user_data",
                jsonObject1
            )
            jsonObject1.addProperty(
                "os",
                "Android"
            )

            jsonObject1.addProperty(
                "ip",
                "192.168.214.22"
            )

            viewModel.postValueServer(jsonObject)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}