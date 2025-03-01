package com.jagteshwar.mynoteapp.di

import android.app.Application
import androidx.room.Room
import com.jagteshwar.mynoteapp.feature_note.data.data_source.NoteDao
import com.jagteshwar.mynoteapp.feature_note.data.data_source.NoteDatabase
import com.jagteshwar.mynoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.jagteshwar.mynoteapp.feature_note.domain.repository.NoteRepository
import com.jagteshwar.mynoteapp.feature_note.domain.use_case.AddNoteUseCase
import com.jagteshwar.mynoteapp.feature_note.domain.use_case.DeleteNoteUseCase
import com.jagteshwar.mynoteapp.feature_note.domain.use_case.GetNoteUseCase
import com.jagteshwar.mynoteapp.feature_note.domain.use_case.GetNotesUseCase
import com.jagteshwar.mynoteapp.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNoteDatabase(app: Application): NoteDatabase{
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun providesNoteUseCases(repository: NoteRepository):NoteUseCases{
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository),
            getNoteUseCase = GetNoteUseCase(repository)
        )
    }
}