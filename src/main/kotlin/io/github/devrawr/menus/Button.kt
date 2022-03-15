package io.github.devrawr.menus

import io.github.devrawr.menus.section.Section
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

open class Button(
    var material: Material = Material.AIR,
    var displayName: String = "",
    var action: (InventoryClickEvent) -> Unit = {},

    var amount: Int = 0,
    var data: Byte = 0,

    var sections: MutableList<Section> = mutableListOf(),

    lore: MutableList<String> = mutableListOf(),
)
{
    var lore: MutableList<String> = lore
        get()
        {
            return field.apply {
                for (section in sections)
                {
                    this.addAll(section.format())
                }
            }
        }

    companion object
    {
        fun createOf(action: (Button) -> (Unit)): Button
        {
            return Button().apply(action)
        }
    }

    fun addSection(section: Section): Button
    {
        return this.apply {
            this.sections.add(section)
        }
    }

    fun build(): ItemStack
    {
        return ItemStack(this.material, amount).apply {
            if (this.itemMeta != null)
            {
                this.itemMeta = this.itemMeta.apply {
                    this.displayName = this@Button.displayName
                    this.lore = this@Button.lore
                }
            }

            this.data.data = this@Button.data
        }
    }
}

class ButtonBuilder : Button()
{
    fun displayName(displayName: String): ButtonBuilder
    {
        return this.apply {
            this.displayName = displayName
        }
    }

    fun lore(lore: MutableList<String>): ButtonBuilder
    {
        return this.apply {
            this.lore = lore
        }
    }

    fun action(action: (InventoryClickEvent) -> Unit = {}): ButtonBuilder
    {
        return this.apply {
            this.action = action
        }
    }

    fun data(data: Byte): ButtonBuilder
    {
        return this.apply {
            this.data = data
        }
    }
}