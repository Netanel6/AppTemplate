package com.example.app.core.designsystem.preview

data class PreviewCardData(
    val title: String,
    val subtitle: String,
    val imageUrl: String,
)

object PreviewData {
    val cards = listOf(
        PreviewCardData(
            title = "Architecture starter",
            subtitle = "Reusable feature-first setup",
            imageUrl = "https://picsum.photos/400/240?1",
        ),
        PreviewCardData(
            title = "Offline-ready sample",
            subtitle = "Room cache wired to Compose",
            imageUrl = "https://picsum.photos/400/240?2",
        ),
    )
}
