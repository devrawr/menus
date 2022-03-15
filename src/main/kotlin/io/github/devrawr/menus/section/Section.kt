package io.github.devrawr.menus.section

class Section(
    val entries: Map<String, String> = hashMapOf(),
    val format: SectionFormat = SectionFormat.DEFAULT_TOP_BOTTOM_SECTION
)
{
    /**
     * Formats the current [Section] according to the [format].
     *
     * Read [SectionFormat.format] for more information, since this method
     * returns a call of that method.
     */
    fun format(): List<String>
    {
        return this.format.format(this)
    }
}