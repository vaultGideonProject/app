package com.vaultmessenger.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaultmessenger.modules.ConversationRepository

class ConversationViewModelFactory(
    private val repository: ConversationRepository,
    private val errorsViewModel: ErrorsViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConversationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConversationViewModel(repository, errorsViewModel = errorsViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
    }