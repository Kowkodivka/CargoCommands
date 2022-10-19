package io.lucin.examples

import arc.func.Cons
import arc.util.Log.err
import io.lucin.core.annotations.Command
import mindustry.gen.Groups
import mindustry.gen.Player

@Command("send", "Send message to a player.", "<player> <message...>")
class ExampleServerCommand : Cons<Array<String>> {
    override fun get(
        args: Array<String>
    ) {
        var target: Player? = null

        Groups.player.each { player ->
            if (player.name == args[0]) target = player else err("Cannot find player '${args[0]}'!")
        }

        if (target != null) {
            target!!.sendMessage(args[1])
        }
    }
}