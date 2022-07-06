package example.singletontest.data.room.task

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import example.singletontest.util.ConstantsRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query(value = "SELECT * FROM ${ConstantsRoom.TABLE_TASKS} ORDER BY createdAt")
    fun loadAll(): Flow<List<TaskEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(taskEntity: TaskEntity)

    @Delete
    suspend fun delete(taskEntity: TaskEntity)
}