package com.app.lydiatest.presenter.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.app.lydiatest.domain.model.User
import com.app.lydiatest.domain.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useRepo: UsersRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState: StateFlow<MainUIState> = _uiState.asStateFlow()

    private val _userSelected = MutableStateFlow(User())
    val userSelected: StateFlow<User> = _userSelected.asStateFlow()
    fun updateUser(newUser: User) {
        _userSelected.value = newUser
    }

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            useRepo.getAllUser()
                .cachedIn(viewModelScope)
                .catch { exception ->
                    _uiState.update { it.copy(exception = exception) }
                }
                .collect { pagingData ->
                    _uiState.update { it.copy(userList = flowOf(pagingData)) }
                }
        }
    }
}

