package io.github.devrawr.menus

import io.github.devrawr.menus.listener.InventoryListener
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.plugin.Plugin

object Menus
{
    private var plugin: Plugin
    private val listener = InventoryListener

    val menus = hashMapOf<Player, Menu>()

    init
    {
        val pluginManager = Bukkit.getPluginManager()
        val plugins = pluginManager.plugins

        this.plugin = plugins.first()

        Bukkit.getPluginManager().registerEvents(this.listener, this.plugin)
    }

    fun withPlugin(plugin: Plugin)
    {
        this.plugin = plugin

        // unregister the previous registered listener
        HandlerList.unregisterAll(listener)

        // register the new listener, using the new plugin
        Bukkit.getPluginManager().registerEvents(this.listener, plugin)
    }

    fun createMenu(
        title: String,
        size: Int,
        builder: (Player) -> HashMap<Int, Button>
    ): Menu
    {
        return object : Menu(title, size)
        {
            override fun getEntries(player: Player): HashMap<Int, Button>
            {
                return builder.invoke(player)
            }
        }
    }
}