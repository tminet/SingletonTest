package example.singletontest.domain.model

data class Task(
    val description: String,
    val createdAt: Long = System.currentTimeMillis()
)