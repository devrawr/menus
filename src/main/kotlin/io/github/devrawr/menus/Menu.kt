package io.github.devrawr.menus

import io.github.devrawr.menus.util.ColorUtil.translate
import io.github.devrawr.tasks.Tasks
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import java.util.*

abstract class Menu(
    val title: String,
    val size: Int,
    val updateTime: Long = -1L
)
{
    open val illegalSlots = mutableListOf<Int>()

    open val inventories = hashMapOf<Player, Inventory>()
    open val entries = hashMapOf<Player, Map<Int, Button>>()

    init
    {
        if (this.updateTime != -1L)
        {
            Tasks
                .sync()
                .repeating(0L, this.updateTime) {
                    for (key in this.inventories.keys)
                    {
                        this.openFor(key)
                    }
                }
        }
    }

    /**
     * Get the entries to display within the inventory.
     *
     * @param player the player to retrieve the entries for
     * @return the entries, as a [Map], with [Int] representing the index, and [Button] representing the item.
     */
    abstract fun getEntries(player: Player): HashMap<Int, Button>

    /**
     * Open the menu for a player.
     *
     * @param uniqueId the [UUID] of the player, this method calls [Bukkit.getPlayer] using
     *                 the provided unique identifier.
     */
    fun openFor(uniqueId: UUID) = this.openFor(player = Bukkit.getPlayer(uniqueId))

    open fun openFor(player: Player)
    {
        val entries = this.getEntries(player)
        val inventory = this.inventories[player]
            ?: this.createInventory(player)

        this.updateEntries(
            player, inventory, entries
        )

        if (inventories[player] != inventory)
        {
            player.openInventory(inventory)
        } else
        {
            player.updateInventory()
        }

        this.inventories[player] = inventory
    }

    open fun updateEntries(player: Player, inventory: Inventory, entries: Map<Int, Button>)
    {
        this.entries[player] = entries

        for (entry in entries)
        {
            if (this.illegalSlots.contains(entry.key))
            {
                continue
            }

            inventory.setItem(entry.key, entry.value.build())
        }
    }

    open fun createInventory(player: Player): Inventory
    {
        return Bukkit.createInventory(
            null, this.size, this.title.translate()
        )
    }
}