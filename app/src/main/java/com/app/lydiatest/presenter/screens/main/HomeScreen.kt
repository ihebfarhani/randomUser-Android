package com.app.lydiatest.presenter.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.app.lydiatest.R
import com.app.lydiatest.domain.model.User
import com.app.lydiatest.navigation.Routes
import com.app.lydiatest.presenter.ui.components.ErrorView
import com.app.lydiatest.presenter.ui.components.LoadingView
import com.app.lydiatest.presenter.ui.components.NoMoreDataView
import com.app.lydiatest.presenter.ui.components.UserItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val pullRefreshState =
        rememberPullRefreshState(uiState.isRefreshing, { viewModel.fetchUsers() })

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                ),
                text = stringResource(R.string.home_screen_title)
            )
        })
    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .pullRefresh(pullRefreshState)
                .background(Color.White)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {

            PullRefreshIndicator(
                modifier = Modifier.size(50.dp),
                refreshing = uiState.isRefreshing,
                state = pullRefreshState
            )

            when {
                uiState.exception != null -> ErrorView(uiState.exception)
                uiState.userList != null -> {
                    val users = uiState.userList!!.collectAsLazyPagingItems()
                    UsersListView(
                        navController = navController,
                        viewModel = viewModel,
                        users = users
                    )
                }
            }
        }
    }

}

@Composable
fun UsersListView(
    navController: NavController,
    viewModel: MainViewModel,
    users: LazyPagingItems<User>
) {
    LazyColumn {
        item { Spacer(modifier = Modifier.height(10.dp)) }

        items(
            count = users.itemCount,
            key = users.itemKey { it.email },
            contentType = users.itemContentType { "contentType" }) { index ->
            val item = users[index]
            item?.let {
                UserItem(fullName = item.fullName,
                    address = item.fullAddress,
                    email = item.email,
                    picture = item.picture,
                    onClick = {
                        viewModel.updateUser(item)
                        navController.navigate(route = Routes.DETAILS_SCREEN.name)
                    })
            }
        }

        users.apply {
            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingView()
                    }
                }

                is LoadState.NotLoading -> {
                    item {
                        NoMoreDataView()
                    }
                }

                is LoadState.Error -> {
                    val e = users.loadState.refresh as LoadState.Error
                    item { ErrorView(e.error) }
                }
            }
        }
    }
}