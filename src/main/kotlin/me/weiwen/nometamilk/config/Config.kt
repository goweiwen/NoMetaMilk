@file:UseSerializers(RegexSerializer::class)

package me.weiwen.nometamilk.config

import com.charleskorn.kaml.Yaml
import kotlinx.serialization.*
import me.weiwen.nometamilk.NoMetaMilk
import me.weiwen.nometamilk.filters.NON_BANNER_FONT_PUA
import me.weiwen.nometamilk.serializers.RegexSerializer
import java.io.File
import java.util.logging.Level

const val CONFIG_VERSION = "1.0.0"

@Serializable
data class Config(
    @SerialName("config-version")
    var configVersion: String = CONFIG_VERSION,

    var blacklist: List<Regex> = listOf(
        NON_BANNER_FONT_PUA
    ),

    var messages: Messages = Messages(),
)

fun parseConfig(plugin: NoMetaMilk): Config {
    val file = File(plugin.dataFolder, "config.yml")

    if (!file.exists()) {
        plugin.logger.log(Level.INFO, "Config file not found, creating default")
        plugin.dataFolder.mkdirs()
        file.createNewFile()
        file.writeText(Yaml().encodeToString(Config()))
    }

    val config = try {
        Yaml().decodeFromString(file.readText())
    } catch (e: Exception) {
        plugin.logger.log(Level.SEVERE, e.message)
        Config()
    }

    if (config.configVersion != CONFIG_VERSION) {
        config.configVersion = CONFIG_VERSION
        plugin.logger.log(Level.INFO, "Updating config")
        file.writeText(Yaml().encodeToString(plugin.config))
    }

    return config
}
