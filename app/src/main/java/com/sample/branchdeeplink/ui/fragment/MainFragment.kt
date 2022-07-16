package com.sample.branchdeeplink.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.branchdeeplink.R
import com.sample.branchdeeplink.databinding.FragmentMainBinding
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.SharingHelper
import io.branch.referral.util.*
import java.util.*

class MainFragment : Fragment() {

    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        return mainBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainBinding.shareApp.setOnClickListener {
           // share()
        }

        mainBinding.trackEvents.setOnClickListener {
            // track
        }


    }

    /*fun share() {
        val buo = BranchUniversalObject()
            .setTitle("Install My app")
            .setContentDescription("Click to install my app")
            //.setContentImageUrl("")
            .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
            .setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
            .setContentMetadata(ContentMetadata().addCustomMetadata("install", "open"))

        var lp = LinkProperties()
            .setChannel("facebook")
            .setAlias("install")
            .setFeature("sharing")
            .setCampaign("Share to install")
            .addControlParameter("data", "arjun 1")


        val ss = ShareSheetStyle(requireActivity(), "Check this out!", "install to check my app ")
            .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
            .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
            .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
            .addPreferredSharingOption(SharingHelper.SHARE_WITH.WHATS_APP)
            .setAsFullWidthStyle(true)
            .setSharingTitle("Share With")

        buo.showShareSheet(requireActivity(), lp, ss, object : Branch.BranchLinkShareListener {
            override fun onShareLinkDialogLaunched() {

            }
            override fun onShareLinkDialogDismissed() {

            }
            override fun onLinkShareResponse(sharedLink: String?, sharedChannel: String?, error: BranchError?) {
                Log.e("BRANCH SDK", sharedLink.toString())
            }
            override fun onChannelSelected(channelName: String) {

            }
        })
    }*/
}