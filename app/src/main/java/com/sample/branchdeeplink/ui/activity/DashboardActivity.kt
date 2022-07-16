package com.sample.branchdeeplink.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sample.branchdeeplink.R
import com.sample.branchdeeplink.databinding.ActivityDashboardBinding
import com.sample.branchdeeplink.repository.BranchRepository
import com.sample.branchdeeplink.ui.fragment.ProfileFragment
import com.sample.branchdeeplink.viewmodel.BranchViewModel
import com.sample.branchdeeplink.viewmodel.BranchViewModelFactory
import io.branch.referral.Branch
import io.branch.referral.BranchError
import org.json.JSONObject

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashboardBinding: ActivityDashboardBinding
    private lateinit var navController: NavController
    lateinit var bottomNavigationView: BottomNavigationView

    lateinit var viewModel: BranchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        navController =
            (supportFragmentManager.findFragmentById(dashboardBinding.dashboardNavHost.id)
                    as NavHostFragment).navController

        dashboardBinding.dashboardBottomNavigation.setupWithNavController(navController = navController)
        bottomNavigationView = dashboardBinding.dashboardBottomNavigation

        val branchRepository = BranchRepository()
        val viewModelProviderFactory = BranchViewModelFactory(application, branchRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(BranchViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        Branch.sessionBuilder(this).withCallback(object : Branch.BranchReferralInitListener {
            override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
                if (error == null) {
                    if (referringParams!!.getString("profile") == "viewprofile") {
                        navigate(referringParams!!.getString("userid"))
                    }
                } else {
                    Log.e("BRANCH SDK", error.message)
                }
            }
        }).withData(this.intent.data).init()
    }

    fun navigate(string: String) {
        dashboardBinding.dashboardBottomNavigation.selectedItemId =
            R.id.profileFragment
        val bundle = Bundle()
        bundle.putString("userid",string)
        navController.navigate(R.id.action_mainFragment_to_profileFragment,bundle)

    }
}