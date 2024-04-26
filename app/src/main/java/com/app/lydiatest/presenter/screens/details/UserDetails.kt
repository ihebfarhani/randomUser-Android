package com.app.lydiatest.presenter.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.app.lydiatest.R
import com.app.lydiatest.presenter.screens.main.MainViewModel
import com.app.lydiatest.presenter.ui.components.InfoRow
import com.app.lydiatest.presenter.ui.theme.LydiaBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailsScreen(navController: NavController, viewModel: MainViewModel) {

    val userInfo by viewModel.userSelected.collectAsState()

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
                text = stringResource(R.string.details_screen_title)
            )
        },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
    }) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(it)
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                colors = CardDefaults.cardColors(LydiaBlue),
                shape = RoundedCornerShape(20.dp)

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    AsyncImage(
                        model = userInfo.picture,
                        contentDescription = "User Image",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                            .clip(CircleShape)
                            .border(1.dp, Color.White, CircleShape),
                        placeholder = painterResource(id = R.drawable.ic_logo_lydia)
                    )

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 15.dp, horizontal = 10.dp),
                        thickness = 1.dp,
                        color = Color.White
                    )

                    InfoRow(
                        info = userInfo.fullName,
                        icon = Icons.Filled.AccountBox
                    )

                    InfoRow(
                        info = userInfo.birthday,
                        icon = Icons.Filled.DateRange
                    )

                    InfoRow(
                        info = userInfo.gender,
                        icon = Icons.Filled.Face
                    )

                    InfoRow(
                        info = userInfo.phone,
                        icon = Icons.Filled.Phone
                    )

                    InfoRow(
                        info = userInfo.email,
                        icon = Icons.Filled.Email
                    )

                    InfoRow(
                        info = userInfo.fullAddress,
                        icon = Icons.Filled.LocationOn
                    )


                }
            }
        }


    }

}