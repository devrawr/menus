package io.github.devrawr.menus.section

import org.bukkit.ChatColor

open class SectionFormat(
    var spacer: String = "",
    var spacerPolicy: SpacerPolicy = SpacerPolicy.TOP_BOTTOM_SPACER,
    var keyColor: ChatColor = ChatColor.YELLOW,
    var valueColor: ChatColor = ChatColor.WHITE,
    var keyValueDelimiter: String = ": ",
    var keyValueColor: ChatColor = keyColor,
)
{
    companion object
    {
        val DEFAULT_TOP_BOTTOM_SECTION = SectionFormat()
        val DEFAULT_TOP_SECTION = SectionFormat(spacerPolicy = SpacerPolicy.TOP_SPACER)
        val DEFAULT_BOTTOM_SECTION = SectionFormat(spacerPolicy = SpacerPolicy.BOTTOM_SPACER)
        val DEFAULT_NO_SPACER_SECTION = SectionFormat(spacerPolicy = SpacerPolicy.NO_SPACER)
    }

    /**
     * Format a section using the current [SectionFormat] format.
     *
     * @param section the section to format accordingly to the format
     * @return the formatted entries
     */
    fun format(section: Section): List<String>
    {
        val lines = mutableListOf<String>()

        if (this.spacerPolicy == SpacerPolicy.TOP_SPACER || this.spacerPolicy == SpacerPolicy.TOP_BOTTOM_SPACER)
        {
            lines.add(this.spacer)
        }

        for (entry in section.entries)
        {
            lines.add("${keyColor}${entry.key}${keyValueColor}${keyValueDelimiter}${valueColor}${entry.value}")
        }

        if (this.spacerPolicy == SpacerPolicy.BOTTOM_SPACER || this.spacerPolicy == SpacerPolicy.TOP_BOTTOM_SPACER)
        {
            lines.add(this.spacer)
        }

        return lines
    }
}

enum class SpacerPolicy
{
    TOP_BOTTOM_SPACER,
    TOP_SPACER,
    BOTTOM_SPACER,
    NO_SPACER
}