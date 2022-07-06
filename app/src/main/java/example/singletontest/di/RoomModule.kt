package example.singletontest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.singletontest.data.room.AppDatabase
import example.singletontest.data.room.task.TaskDao
import example.singletontest.util.ConstantsRoom

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
//    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        ConstantsRoom.DATABASE_NAME
    ).build()

    @Provides
//    @Singleton
    fun provideTaskDao(
        appDatabase: AppDatabase
    ): TaskDao = appDatabase.taskDao()
}