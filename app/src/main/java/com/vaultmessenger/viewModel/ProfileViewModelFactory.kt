package com.vaultmessenger.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaultmessenger.modules.FirebaseUserRepository

class ProfileViewModelFactory(
    private val repository: FirebaseUserRepository = FirebaseUserRepository(),
    private val errorsViewModel: ErrorsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository, errorsViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

