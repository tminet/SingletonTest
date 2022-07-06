package example.singletontest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import example.singletontest.data.room.task.TaskDao
import example.singletontest.data.room.task.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}