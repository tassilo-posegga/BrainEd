package eu.posegga.brained.di

import eu.posegga.brained.home.data.LevelRepository
import eu.posegga.brained.home.domain.usecase.LoadLevelsUseCase
import eu.posegga.brained.home.local.repository.LevelLocalSource
import eu.posegga.brained.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { LevelLocalSource() }
    single { LevelRepository(get()) }
    single { LoadLevelsUseCase(get()) }
    viewModel { HomeViewModel(get()) }
}