package eu.posegga.brained.di

import eu.posegga.brained.data.LevelRepository
import eu.posegga.brained.domain.usecase.LoadLevelsUseCase
import eu.posegga.brained.local.repository.LevelLocalSource
import eu.posegga.brained.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LevelLocalSource() }
    single { LevelRepository(get()) }
    single { LoadLevelsUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}