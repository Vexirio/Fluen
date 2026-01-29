package xyz.vexirio.fluen

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class Fluen : JavaPlugin() {

    override fun onEnable() {
        // Логика при запуске
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        // Используем 'this' вместо 'plugin', так как мы находимся внутри главного класса
        if (!Api.permission("system.admin", sender, this)) {
            return true
        }
        return true
    }

    override fun onDisable() {
    }
}