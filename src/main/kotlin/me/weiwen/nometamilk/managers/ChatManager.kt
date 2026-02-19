package me.weiwen.nometamilk.managers

import io.papermc.paper.event.player.AsyncChatEvent
import me.weiwen.nometamilk.NoMetaMilk.Companion.plugin
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatManager : Listener {
    lateinit var plainTextComponentSerializer: PlainTextComponentSerializer
        private set

    fun enable() {
        plainTextComponentSerializer = PlainTextComponentSerializer.plainText()

        plugin.server.pluginManager.registerEvents(this, plugin)
    }

    fun disable() {
    }

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val message = plainTextComponentSerializer.serialize(event.originalMessage())
        if (plugin.config.blacklist.any { it.containsMatchIn(message) }) {
            if (plugin.config.messages.messageBlocked.isNotEmpty()) {
                val errorMessage = plugin.miniMessage.deserialize(plugin.config.messages.messageBlocked)
                event.player.sendMessage(errorMessage)
            }

            event.isCancelled = true
            return
        }

        if (plugin.config.prependBlankLineWhitelist.any { it.containsMatchIn(message) }) {
            event.viewers()
                .filterIsInstance<Player>()
                .forEach { it.sendMessage(Component.empty()) }
        }
    }
}