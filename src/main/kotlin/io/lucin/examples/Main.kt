package io.lucin.examples

import arc.util.CommandHandler
import io.lucin.core.CargoRegister
import mindustry.mod.Plugin

class Main : Plugin() {
    override fun registerServerCommands(handler: CommandHandler) {
        CargoRegister().add(ExampleServerCommand()).build(handler)
    }

    override fun registerClientCommands(handler: CommandHandler) {
        CargoRegister().add(ExampleClientCommand()).build(handler)
    }
}