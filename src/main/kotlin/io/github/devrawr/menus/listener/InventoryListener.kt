package io.github.devrawr.menus.listener

import io.github.devrawr.menus.Menus
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

object InventoryListener : Listener
{
    @EventHandler
    fun click(event: InventoryClickEvent)
    {
        val player = event.whoClicked as Player
        val menu = Menus.menus[player]

        if (menu != null)
        {
            val entries = menu.entries[player]
                ?: menu.getEntries(player)

            entries[event.slot]?.action?.invoke(event)
        }
    }

    @EventHandler
    fun close(event: InventoryCloseEvent)
    {
        val player = event.player
        val menu = Menus.menus[player]

        menu?.inventories?.remove(player)
    }
}