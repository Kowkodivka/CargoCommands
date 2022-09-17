package io.lucin.core.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Command(
    val name: String = "",
    val description: String = "",
    val parameters: String = ""
)