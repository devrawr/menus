import io.github.devrawr.menus.Button
import io.github.devrawr.menus.Menu
import io.github.devrawr.menus.Menus
import io.github.devrawr.menus.section.Section
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.util.HashMap

class MenuExample
{
    fun functional()
    {
        Menus.createMenu("hey!", 9) { player ->
            return@createMenu hashMapOf<Int, Button>()
                .apply {
                    this[0] = Button.createOf {
                        it.displayName = "${ChatColor.GOLD}${player.name}"
                        it.addSection(
                            Section(
                                hashMapOf(
                                    "GameMode" to player.gameMode.name,
                                    "Name" to player.name
                                )
                            )
                        )
                    }
                }
        }
    }

    fun oop()
    {
        ExampleMenu() // look at ExampleMenu class.
    }
}

class ExampleMenu : Menu("hey!", 9)
{
    override fun getEntries(player: Player): HashMap<Int, Button>
    {
        val map = hashMapOf<Int, Button>()

        map[0] = Button.createOf {
            it.displayName = "${ChatColor.GOLD}${player.name}"
            it.addSection(
                Section(
                    hashMapOf(
                        "GameMode" to player.gameMode.name,
                        "Name" to player.name
                    )
                )
            )
        }

        return map
    }
}