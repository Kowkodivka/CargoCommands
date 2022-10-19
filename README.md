# Cargo commands

## Annotation-based library for registration commands

### Examples:

Plugin main class:

```kotlin
class ExamplePlugin : Plugin() {
    override fun registerServerCommands(handler: CommandHandler) {
        CargoRegister().add(EchoCommandServer()).build(handler)
    }

    override fun registerClientCommands(handler: CommandHandler) {
        CargoRegister().add(EchoCommandClient()).build(handler)
    }
}
```

Client command:

```kotlin
@Command("echo", "Replies with your message.", "<message...>")
class EchoCommandClient : CommandRunner<Player> {
    override fun accept(args: Array<out String>, player: Player) {
        player.sendMessage(args[0])
    }
}
```

Server command:

```kotlin
@Command("echo", "Replies with your message.", "<message...>")
class EchoCommandServer : Cons<Array<String>> {
    override fun get(args: Array<String>) {
        Log.info(args[0])
    }
}
```

### Building a Jar

If you use Linux / Mac OS:
`chmod +x ./gradlew`

Then:
`gradlew jar` / `./gradlew jar`

Output jar should be in `build/libs`.