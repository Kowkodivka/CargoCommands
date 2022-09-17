package io.lucin.core.annotations

import javax.lang.model.type.NullType
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Event(
    val type: KClass<*> = NullType::class
)