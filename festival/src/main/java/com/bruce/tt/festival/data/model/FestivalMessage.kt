package com.bruce.tt.festival.data.model

import com.bruce.tt.festival.R

data class FestivalMessage(
    val resource: Int? = null,
    val message: String? = null
)

object FestivalConstants {
    private val messages = listOf(
        FestivalMessage(
            resource = R.drawable.ic_king_crown,
            message = "King’s Birthday celebrates the official birth of our reigning monarch."
        ),
        FestivalMessage(
            resource = R.drawable.ic_royal_flag,
            message = "A day to honour tradition, leadership, and the royal legacy."
        ),
        FestivalMessage(
            resource = R.drawable.ic_parade,
            message = "Mark the day with parades, ceremonies, and national pride."
        ),
        FestivalMessage(
            resource = R.drawable.ic_cake_crown,
            message = "Wishing joy and prosperity on the King’s Birthday celebration."
        )
    )

    fun getFestivalMessages(): List<FestivalMessage> {
        return messages
    }
}
