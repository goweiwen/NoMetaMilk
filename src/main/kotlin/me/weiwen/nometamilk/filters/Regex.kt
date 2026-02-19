package me.weiwen.nometamilk.filters

val BANNER_FONT_PUA = Regex("[\\x{E000}-\\x{F8FF}]")
val NON_BANNER_FONT_PUA = Regex("[^\\x{E000}-\\x{F8FF}]")
