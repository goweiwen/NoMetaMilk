package me.weiwen.nometamilk.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Messages(
    @SerialName("config-reloaded")
    val configReloaded: String = "<gold>Configuration reloaded.</gold>",
    @SerialName("message-blocked")
    val messageBlocked: String = "<red>Your message can only contain Banner Font.</red>",
)