package io.github.devrawr.menus.util

import org.bukkit.ChatColor

object ColorUtil
{
    fun String.translate(): String
    {
        return ChatColor.translateAlternateColorCodes('&', this)
    }
}