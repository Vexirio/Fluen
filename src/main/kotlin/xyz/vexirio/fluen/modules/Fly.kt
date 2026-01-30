package xyz.vexirio.fluen.modules

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import xyz.vexirio.fluen.Api.color
import java.util.UUID

class Fly(private val plugin: JavaPlugin) : CommandExecutor {
    private val flyPlayers = mutableSetOf<UUID>()

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String?>
    ): Boolean {
        if (!sender.hasPermission("fluen.fly")) {
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

        val flight = !player.allowFlight
        player.allowFlight = flight

        if (flight) {
            flyPlayers.add(player.uniqueId)
            sender.sendMessage(color(plugin.config.getString("fly-enabled")?.replace("%player%", player.name)))
        } else {
            flyPlayers.remove(player.uniqueId)
            player.isFlying = false
            sender.sendMessage(color(plugin.config.getString("fly-disabled")?.replace("%player%", player.name)))
        }

        return true
    }
}