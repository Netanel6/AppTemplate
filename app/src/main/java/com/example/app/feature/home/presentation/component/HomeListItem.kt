package com.example.app.feature.home.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.app.feature.home.presentation.model.HomeItemUiModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeListItem(
    item: HomeItemUiModel,
    onClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(item.id) },
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            GlideImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier.weight(1f),
            )
            Column(modifier = Modifier.weight(2f)) {
                Text(text = item.title, style = MaterialTheme.typography.titleMedium)
                Text(text = item.subtitle, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
