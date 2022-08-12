package io.lucin.core

import arc.func.Cons
import arc.util.CommandHandler
import arc.util.CommandHandler.CommandRunner
import arc.util.Log
import io.lucin.core.annotations.Command
import mindustry.gen.Player
import kotlin.reflect.full.findAnnotation

class Cargo(handler: CommandHandler, clientCommands: MutableList<CommandRunner<Player>>, serverCommands: MutableList<Cons<Array<String>>>) {
    init {
        try {
            for (command in clientCommands) {
                if (clientCommands.isEmpty()) break
                val annotations = command::class.findAnnotation<Command>() ?: continue

                val name = annotations.name
                val description = annotations.description
                val parameters = annotations.parameters

                handler.register(name, parameters, description, command::accept)
            }

            for (command in serverCommands) {
                if (serverCommands.isEmpty()) break
                val annotations = command::class.findAnnotation<Command>() ?: continue

                val name = annotations.name
                val description = annotations.description
                val parameters = annotations.parameters

                handler.register(name, parameters, description) { args ->
                    command.get(args)
                }
            }
        } catch (e: Exception) {
            Log.err(e)
        }
    }
}