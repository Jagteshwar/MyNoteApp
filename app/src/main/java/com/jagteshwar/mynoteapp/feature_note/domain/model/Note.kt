package com.jagteshwar.mynoteapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jagteshwar.mynoteapp.ui.BabyBlue
import com.jagteshwar.mynoteapp.ui.LightGreen
import com.jagteshwar.mynoteapp.ui.RedOrange
import com.jagteshwar.mynoteapp.ui.RedPink
import com.jagteshwar.mynoteapp.ui.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id:Int? = null
){
    companion object {
        val noteColors = listOf(RedOrange, Violet, BabyBlue, LightGreen, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message)