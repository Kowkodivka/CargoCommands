package io.lucin.examples

import arc.util.CommandHandler.CommandRunner
import io.lucin.core.annotations.Command
import mindustry.gen.Player

@Command("echo", "Replies with your message.", "<message...>")
class ExampleClientCommand : CommandRunner<Player> {
    override fun accept(args: Array<out String>, player: Player) {
        player.sendMessage("[accent]You said: []{args[0]}[]")
    }
}