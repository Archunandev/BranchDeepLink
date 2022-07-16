package com.sample.branchdeeplink.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.branchdeeplink.repository.BranchRepository


class BranchViewModelFactory(val app : Application, val BranchRepository: BranchRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BranchViewModel(app, BranchRepository) as T
    }
}