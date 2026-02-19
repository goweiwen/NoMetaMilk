package me.weiwen.nometamilk

import me.weiwen.nometamilk.config.Config
import me.weiwen.nometamilk.config.parseConfig
import me.weiwen.nometamilk.managers.ChatManager
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.plugin.java.JavaPlugin
import org.incendo.cloud.description.Description
import org.incendo.cloud.execution.ExecutionCoordinator
import org.incendo.cloud.paper.PaperCommandManager

class NoMetaMilk : JavaPlugin() {
    companion object {
        lateinit var plugin: NoMetaMilk
            private set
    }

    lateinit var config: Config
    lateinit var miniMessage: MiniMessage

    override fun onLoad() {
        plugin = this
    }

    override fun onEnable() {
        config = parseConfig(this)
        miniMessage = MiniMessage.miniMessage()

        registerCommands()

        ChatManager.enable()

        logger.info("NoMetaMilk is enabled")
    }

    override fun onDisable() {
        ChatManager.disable()

        logger.info("NoMetaMilk is disabled")
    }

    fun registerCommands() {
        val manager = PaperCommandManager.builder()
            .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
            .buildOnEnable(plugin)

        manager.commandBuilder("nometamilk", Description.of("Manages the NoMetaMilk plugin"))
            .permission("nometamilk.admin").let { builder ->
                manager.command(
                    builder.literal("reload", Description.of("Reloads configuration"))
                        .handler { ctx ->
                            config = parseConfig(plugin)
                            ctx.sender().sender.sendMessage(miniMessage.deserialize(config.messages.configReloaded))
                        }
                    )
            }

        logger.info("Registered commands.")
    }
}
