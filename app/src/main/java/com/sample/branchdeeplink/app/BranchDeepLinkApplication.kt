package com.sample.branchdeeplink.app

import android.app.Application
import io.branch.referral.Branch
import java.util.*

class BranchDeepLinkApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        val branch = Branch.getAutoInstance(this)
        Branch.enableTestMode()
       // branch.setIdentity(UUID.randomUUID().toString())
    }

}

