package com.app.lydiatest.presenter.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.lydiatest.R
import com.app.lydiatest.presenter.ui.theme.LydiaBlue


@Composable
fun InfoRow(info: String, icon: ImageVector) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = "image",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )

        Text(
            text = info,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
            )
        )
    }
}

@Composable
fun UserItem(
    fullName: String,
    address: String,
    email: String,
    picture: String,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.White),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(LydiaBlue)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onClick() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = picture,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape),
                placeholder = painterResource(id = R.drawable.ic_logo_lydia)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = fullName,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = address,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = email,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun UserItemPreview() {

    InfoRow(
        info = "Iheb",
        icon = Icons.Filled.AccountBox
    )

}