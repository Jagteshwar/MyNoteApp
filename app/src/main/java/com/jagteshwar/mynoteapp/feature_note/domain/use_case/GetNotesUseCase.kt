package com.jagteshwar.mynoteapp.feature_note.domain.use_case

import com.jagteshwar.mynoteapp.feature_note.domain.model.Note
import com.jagteshwar.mynoteapp.feature_note.domain.repository.NoteRepository
import com.jagteshwar.mynoteapp.feature_note.domain.util.NoteOrder
import com.jagteshwar.mynoteapp.feature_note.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class GetNotesUseCase(
    private val repository: NoteRepository
) {

    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Date(OrderType.Ascending)
    ): Flow<List<Note>> = flow {
            repository.getNotes().map { notes->
                when(noteOrder.orderType){
                    is OrderType.Ascending->{
                        when(noteOrder){
                            is NoteOrder.Title->{notes.sortedBy { it.title.lowercase() }}
                            is NoteOrder.Date->{notes.sortedBy { it.timeStamp }}
                            is NoteOrder.Color->{notes.sortedBy { it.color }}
                        }
                    }
                    is OrderType.Descending->{
                        when(noteOrder){
                            is NoteOrder.Title->{notes.sortedByDescending { it.title.lowercase() }}
                            is NoteOrder.Date->{notes.sortedByDescending { it.timeStamp }}
                            is NoteOrder.Color->{notes.sortedByDescending { it.color }}
                        }
                    }
                }
            }
    }
}