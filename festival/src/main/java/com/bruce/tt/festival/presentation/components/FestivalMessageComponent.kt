package com.bruce.tt.festival.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bruce.tt.design.components.ImageComponent
import com.bruce.tt.design.components.TextComponent
import com.bruce.tt.festival.data.model.FestivalConstants
import io.github.jan.supabase.postgrest.query.Columns

@Composable
fun FestivalMessagesComponent() {
    FestivalConstants.getFestivalMessages().forEach {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .padding(all = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            ImageComponent(
                modifier = Modifier.wrapContentSize(),
                resourceValue = it.resource
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextComponent(
                modifier = Modifier.fillMaxWidth(),
                textValue = it.message
            )
        }
    }
}