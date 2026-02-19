package me.weiwen.nometamilk.filters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegexTest {
    @Test
    fun `BANNER_FONT_PUA should match on all strings containing any banner font`() {
        // No Banner Font
        Assertions.assertFalse(BANNER_FONT_PUA.containsMatchIn("A"))
        Assertions.assertFalse(BANNER_FONT_PUA.containsMatchIn("a"))
        Assertions.assertFalse(BANNER_FONT_PUA.containsMatchIn("0"))
        Assertions.assertFalse(BANNER_FONT_PUA.containsMatchIn(" "))
        Assertions.assertFalse(BANNER_FONT_PUA.containsMatchIn("Aa0 "))

        // Banner Font only
        Assertions.assertTrue(BANNER_FONT_PUA.containsMatchIn("\uE000"))
        Assertions.assertTrue(BANNER_FONT_PUA.containsMatchIn("\uE00F\uE000\uE00D"))

        // Banner Font and others
        Assertions.assertTrue(BANNER_FONT_PUA.containsMatchIn("A\uE00F\uE000\uE00D"))
        Assertions.assertTrue(BANNER_FONT_PUA.containsMatchIn("\uE00F\uE000\uE00DA"))
        Assertions.assertTrue(BANNER_FONT_PUA.containsMatchIn("A\uE00F\uE000\uE00DA"))
    }

    @Test
    fun `NON_BANNER_FONT_PUA should match on all strings containing non-banner font`() {
        // No Banner Font
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("A"))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("a"))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("0"))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn(" "))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("Aa0 "))

        // Banner Font only
        Assertions.assertFalse(NON_BANNER_FONT_PUA.containsMatchIn("\uE000"))
        Assertions.assertFalse(NON_BANNER_FONT_PUA.containsMatchIn("\uE00F\uE000\uE00D"))

        // Banner Font and others
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("A\uE00F\uE000\uE00D"))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("\uE00F\uE000\uE00DA"))
        Assertions.assertTrue(NON_BANNER_FONT_PUA.containsMatchIn("A\uE00F\uE000\uE00DA"))
    }
}