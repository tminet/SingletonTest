package example.singletontest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import example.singletontest.domain.usecase.DeleteTaskUseCase
import example.singletontest.domain.usecase.DeleteTaskUseCaseImpl
import example.singletontest.domain.usecase.GetTasksUseCase
import example.singletontest.domain.usecase.GetTasksUseCaseImpl
import example.singletontest.domain.usecase.InsertTaskUseCase
import example.singletontest.domain.usecase.InsertTaskUseCaseImpl
import example.singletontest.domain.usecase.IsAppThemeDarkModeUseCase
import example.singletontest.domain.usecase.IsAppThemeDarkModeUseCaseImpl
import example.singletontest.domain.usecase.UpdateAppThemeUseCase
import example.singletontest.domain.usecase.UpdateAppThemeUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindIsAppThemeDarkMode(
        useCase: IsAppThemeDarkModeUseCaseImpl
    ): IsAppThemeDarkModeUseCase

    @Binds
    fun bindUpdateAppTheme(
        useCase: UpdateAppThemeUseCaseImpl
    ): UpdateAppThemeUseCase

    @Binds
    fun bindGetTasks(
        useCase: GetTasksUseCaseImpl
    ): GetTasksUseCase

    @Binds
    fun bindInsertTask(
        useCase: InsertTaskUseCaseImpl
    ): InsertTaskUseCase

    @Binds
    fun bindDeleteTask(
        useCase: DeleteTaskUseCaseImpl
    ): DeleteTaskUseCase
}