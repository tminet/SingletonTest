package example.singletontest.domain.usecase

import example.singletontest.data.UserTasks
import example.singletontest.domain.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetTasksUseCase {
    operator fun invoke(): Flow<List<Task>>
}

class GetTasksUseCaseImpl @Inject constructor(
    private val userTasks: UserTasks
) : GetTasksUseCase {
    override fun invoke(): Flow<List<Task>> = userTasks.loadAll()
}