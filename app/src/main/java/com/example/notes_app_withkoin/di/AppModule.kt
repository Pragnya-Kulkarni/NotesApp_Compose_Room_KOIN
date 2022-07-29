package com.example.notes_app_withkoin.di

import android.app.Application
import androidx.room.Room
import com.example.notes_app_withkoin.NoteViewModel
import com.example.notes_app_withkoin.data.datasource.LocalDataSourceImpl
import com.example.notes_app_withkoin.data.datasource.LocalDataSourceInterface
import com.example.notes_app_withkoin.data.datasource.NoteDao
import com.example.notes_app_withkoin.data.datasource.NoteDataBase
import com.example.notes_app_withkoin.data.repo.NoteRepositoryImpl
import com.example.notes_app_withkoin.domain.NoteRepositoryInterface
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { NoteViewModel(get()) }
    single<NoteRepositoryInterface> {
        NoteRepositoryImpl(get())
    }
    single<LocalDataSourceInterface> {
        LocalDataSourceImpl(get())
    }
    fun provideDataBase(application: Application): NoteDataBase {
        return Room.databaseBuilder(application, NoteDataBase::class.java, "notes_db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { provideDataBase(androidApplication()) }
    single<NoteDao> {
        val database = get<NoteDataBase>()
        database.noteDao
    }
}