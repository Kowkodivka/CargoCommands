package core

import arc.func.Cons
import arc.util.CommandHandler
import arc.util.CommandHandler.CommandRunner
import mindustry.gen.Player

@Suppress("unused")
class CargoRegister {
    private var clientCommands: MutableList<CommandRunner<Player>> = mutableListOf()
    private var serverCommands: MutableList<Cons<Array<String>>> = mutableListOf()

    fun add(command: CommandRunner<Player>): CargoRegister {
        this.clientCommands.add(command)
        return this
    }

    fun add(vararg commands: CommandRunner<Player>): CargoRegister {
        this.clientCommands.addAll(commands)
        return this
    }

    fun add(command: Cons<Array<String>>): CargoRegister {
        this.serverCommands.add(command)
        return this
    }

    fun add(vararg commands: Cons<Array<String>>): CargoRegister {
        this.serverCommands.addAll(commands)
        return this
    }

    fun build(handler: CommandHandler): Cargo {
        return Cargo(handler, clientCommands, serverCommands)
    }
}