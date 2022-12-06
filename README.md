<style>
    .cargo {
        display: flex;
        flex-direction: column;
        justify-content: center;
        text-align: center;
    }
</style>

<div class="cargo">
    <h1>Cargo Commands</h1>
    <p>Annotation-based library for registration commands</p>
</div>

## Installation

#### Gradle

```groovy
repositories {
    maven { url 'https://www.jitpack.io' }
}

dependencies {
    implementation 'com.github.Kowkodivka:CargoCommands:1.0.2'
}
```

#### Gradle Kotlin DSL

```kotlin
repositories {
    maven(url = "https://www.jitpack.io")
}

dependencies {
    implementation("com.github.Kowkodivka:CargoCommands:1.0.2")
}
```

## Building

If you'd rather compile on your own, follow these instructions. First, make sure you have JDK 19 installed. Other JDK
versions will not work. Open a terminal your working directory and run the following commands:

#### Windows

_Building:_ `gradlew jar`

#### Linux/Mac OS

_Building:_ `./gradlew jar`

### Troubleshooting

#### Permission Denied

If the terminal returns Permission denied or Command not found on Mac/Linux, run chmod +x ./gradlew before running
./gradlew. This is a one-time procedure.

## Examples:

Plugin main class:

```kotlin
import arc.util.CommandHandler
import io.lucin.core.CargoRegister
import mindustry.mod.Plugin

class ExamplePlugin : Plugin() {
    override fun registerServerCommands(handler: CommandHandler) {
        CargoRegister().add(ExampleServerCommand()).build(handler)
    }

    override fun registerClientCommands(handler: CommandHandler) {
        CargoRegister().add(ExampleClientCommand()).build(handler)
    }
}
```

Client command:

```kotlin
import arc.util.CommandHandler.CommandRunner
import io.lucin.core.annotations.Command
import mindustry.gen.Player

@Command("echo", "Replies with your message.", "<message...>")
class ExampleClientCommand : CommandRunner<Player> {
    override fun accept(args: Array<out String>, player: Player) {
        player.sendMessage("[accent]You said: []{args[0]}[]")
    }
}
```

Server command:

```kotlin
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
```