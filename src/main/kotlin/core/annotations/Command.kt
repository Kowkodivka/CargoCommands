package core.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Command (
    val name: String = "",
    val description: String = "",
    val parameters: String = ""
)