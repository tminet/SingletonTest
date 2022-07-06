package example.singletontest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import example.singletontest.data.UserPreferences
import example.singletontest.data.UserTasks
import example.singletontest.data.datastore.UserPreferencesImplDataStore
import example.singletontest.data.room.task.UserTasksImplRoom

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
//    @Singleton
    fun bindUserPreferences(
        userPreferences: UserPreferencesImplDataStore
    ): UserPreferences

    @Binds
//    @Singleton
    fun bindUserTasks(
        userTasks: UserTasksImplRoom
    ): UserTasks
}