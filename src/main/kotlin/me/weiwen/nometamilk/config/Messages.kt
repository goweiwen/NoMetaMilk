package me.weiwen.nometamilk.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Messages(
    @SerialName("config-reloaded")
    val configReloaded: String = "<gold>Configuration reloaded.</gold>",
)