package com.sample.branchdeeplink.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sample.branchdeeplink.databinding.FragmentMainBinding
import com.sample.branchdeeplink.databinding.FragmentProfileBinding
import io.branch.indexing.BranchUniversalObject
import io.branch.referral.Branch
import io.branch.referral.BranchError
import io.branch.referral.SharingHelper
import io.branch.referral.util.*


class ProfileFragment : Fragment() {

    private lateinit var profileBinding: FragmentProfileBinding

    private var arjun: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        profileBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return profileBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arjun = arguments?.getString("userid")
        if (arjun != null) {
            profileBinding.profileName.text = "Arjun@branch ${arguments?.getString("userid")}"
        }

        profileBinding.shareProfile.setOnClickListener {
            sharerofile()

        }

        profileBinding.profileBtn.setOnClickListener {
            BranchEvent(BRANCH_STANDARD_EVENT.COMPLETE_REGISTRATION)
                .setCustomerEventAlias("my_custom_alias")
                .setTransactionID("tx1234")
                .setDescription("User followed you")
                .addCustomDataProperty("followed", "numbers")
                .logEvent(context)
        }
    }

    private fun sharerofile() {
        //we also save this in local
        val branchUniversalObject = BranchUniversalObject()
            .setTitle("Profile")
            .setContentDescription("Check my photos")
            //.setContentImageUrl("")
            .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
            .setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
            .setContentMetadata(ContentMetadata().addCustomMetadata("profile", "viewprofile"))

        var linkProperties = LinkProperties()
            .setChannel("facebook")
            .setFeature("view")
            .setCampaign("Share test profile")
            .addControlParameter("userid", "5")


        val shareSheetStyle =
            ShareSheetStyle(requireActivity(), "Check this out!", "Click to see my profile")
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
                .addPreferredSharingOption(SharingHelper.SHARE_WITH.WHATS_APP)
                .setAsFullWidthStyle(true)
                .setSharingTitle("Share With")

        branchUniversalObject.showShareSheet(
            requireActivity(),
            linkProperties,
            shareSheetStyle,
            object : Branch.BranchLinkShareListener {
                override fun onShareLinkDialogLaunched() {

                }

                override fun onShareLinkDialogDismissed() {

                }

                override fun onLinkShareResponse(
                    sharedLink: String?,
                    sharedChannel: String?,
                    error: BranchError?
                ) {
                    Log.e("BRANCH SDK", sharedLink.toString())
                }

                override fun onChannelSelected(channelName: String) {

                }
            })
    }
}
