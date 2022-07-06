package example.singletontest.domain.usecase

import example.singletontest.data.UserTasks
import example.singletontest.domain.model.Task
import javax.inject.Inject

interface InsertTaskUseCase {
    suspend operator fun invoke(task: Task)
}

class InsertTaskUseCaseImpl @Inject constructor(
    private val userTasks: UserTasks
) : InsertTaskUseCase {
    override suspend fun invoke(task: Task) =
        userTasks.insert(task = task)
}