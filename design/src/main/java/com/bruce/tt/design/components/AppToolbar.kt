package com.bruce.tt.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bruce.tt.design.R
import com.bruce.tt.design.ui.theme.primaryColor

@Composable
fun AppToolbar(
    title: String? = null,
    isBackButtonVisible: Boolean = false,
    isNotificationVisible: Boolean = false,
    primaryButtonClicked: () -> Unit = { },
    backgroundColor: Color = primaryColor,
    primaryTextClicked: () -> Unit = { }
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .systemBarsPadding()
            .fillMaxWidth()
            .padding(start = 18.dp, end = 18.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    primaryButtonClicked.invoke()
                },
            painter = painterResource(id = if (isBackButtonVisible) R.drawable.icon_arrow_back else R.drawable.icon_person),
            contentDescription = if (isBackButtonVisible) "Back Button" else "User image",
            tint = com.bruce.tt.design.ui.theme.whiteColor
        )
        Spacer(modifier = Modifier.width(18.dp))
        TextComponent(
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    primaryTextClicked.invoke()
                },
            textValue = title,
            textColorValue = com.bruce.tt.design.ui.theme.whiteColor,
            fontSizeValue = 20.sp,
            paddingValue = 8.dp
        )
        Spacer(modifier = Modifier.weight(1f))
        if (isNotificationVisible) {
            Icon(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.icon_notifications),
                contentDescription = "Notifications",
                tint = com.bruce.tt.design.ui.theme.whiteColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview_AppToolbar() {
    AppToolbar(
        title = "AppToolbar title",
        isBackButtonVisible = true,
        primaryButtonClicked = { }
    )
}