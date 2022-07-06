package example.singletontest.domain.usecase

import example.singletontest.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IsAppThemeDarkModeUseCase {
    operator fun invoke(): Flow<Boolean?>
}

class IsAppThemeDarkModeUseCaseImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : IsAppThemeDarkModeUseCase {
    override fun invoke(): Flow<Boolean?> =
        userPreferences.isAppThemeDarkMode
}