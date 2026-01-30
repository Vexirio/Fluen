package xyz.vexirio.fluen.modules

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import xyz.vexirio.fluen.Api.color

class Feed(private val plugin: JavaPlugin) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String?>
    ): Boolean {
        if (!sender.hasPermission("fluen.feed")) {
            sender.sendMessage(color(plugin.config.getString("no-permission")))
            return true
        }

        val player: Player? = if (args.isEmpty()) {
            sender as? Player
        } else {
            Bukkit.getPlayer(args[0] ?: "")
        }
        if (player == null) {
            sender.sendMessage(color(plugin.config.getString("player-not-found")))
            return true
        }

        player?.foodLevel = 20
        player?.saturation = 20f

        sender.sendMessage(color(plugin.config.getString("was-fed")?.replace("%player%", player?.name ?: "")))

        return true
    }
}