package example.singletontest.domain.usecase

import example.singletontest.data.UserTasks
import example.singletontest.domain.model.Task
import javax.inject.Inject

interface DeleteTaskUseCase {
    suspend operator fun invoke(task: Task)
}

class DeleteTaskUseCaseImpl @Inject constructor(
    private val userTasks: UserTasks
) : DeleteTaskUseCase {
    override suspend fun invoke(task: Task) =
        userTasks.delete(task = task)
}