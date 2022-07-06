package example.singletontest.data.room.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import example.singletontest.util.ConstantsRoom

@Entity(tableName = ConstantsRoom.TABLE_TASKS)
data class TaskEntity(
    val description: String,
    @PrimaryKey(autoGenerate = false)
    val createdAt: Long
)