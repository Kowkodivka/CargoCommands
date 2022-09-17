package io.lucin.core

import arc.Events
import arc.func.Cons
import arc.util.CommandHandler
import arc.util.CommandHandler.CommandRunner
import arc.util.Log.err
import io.lucin.core.annotations.Command
import io.lucin.core.annotations.Event
import mindustry.gen.Player
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberFunctions

class Cargo {
    constructor(
        events: MutableList<KClass<*>> = mutableListOf()
    ) {
        // TODO make better this shit
        try {
            if (events.isNotEmpty()) for (event in events) {
                val annotations = event::class.findAnnotation<Event>() ?: continue
                val type = annotations.type

                Events.on(type::class.java) { event ->
                    for (function in event::class.memberFunctions) {
                        function.findAnnotation<Event>().let {
                            function.call(event)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            err(e)
        }
    }

    constructor(
        handler: CommandHandler,
        clientCommands: MutableList<CommandRunner<Player>>,
        serverCommands: MutableList<Cons<Array<String>>>
    ) {
        try {
            if (clientCommands.isNotEmpty()) for (command in clientCommands) {
                val annotations = command::class.findAnnotation<Command>() ?: continue

                val name = annotations.name
                val description = annotations.description
                val parameters = annotations.parameters

                handler.register(name, parameters, description, command::accept)
            }

            if (serverCommands.isNotEmpty()) for (command in serverCommands) {
                val annotations = command::class.findAnnotation<Command>() ?: continue

                val name = annotations.name
                val description = annotations.description
                val parameters = annotations.parameters

                handler.register(name, parameters, description) { args ->
                    command.get(args)
                }
            }
        } catch (e: Exception) {
            err(e)
        }
    }
}