package example.singletontest.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import example.singletontest.util.ConstantsDataStore

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
//    @Singleton
    fun provideDataStoreUserPreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = PreferenceDataStoreFactory.create(
        produceFile = {
            context.preferencesDataStoreFile(name = ConstantsDataStore.FILE_NAME)
        }
    )
}