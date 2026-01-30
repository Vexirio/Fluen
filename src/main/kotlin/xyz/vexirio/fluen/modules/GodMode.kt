package xyz.vexirio.fluen.modules

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import xyz.vexirio.fluen.Api.color
import java.util.UUID

class GodMode(private val plugin: JavaPlugin) : CommandExecutor {
    private val godModePlayers = mutableSetOf<UUID>()

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String?>
    ): Boolean {
        if (!sender.hasPermission("fluen.godmode")) {
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

        val isGodMode = if (godModePlayers.contains(player.uniqueId)) {
            godModePlayers.remove(player.uniqueId)
            player.isInvulnerable = false
            false
        } else {
            godModePlayers.add(player.uniqueId)
            player.isInvulnerable = true
            true
        }

        if (isGodMode) {
            sender.sendMessage(color(plugin.config.getString("godmode-enabled")?.replace("%player%", player.name)))
        } else {
            sender.sendMessage(color(plugin.config.getString("godmode-disabled")?.replace("%player%", player.name)))
        }

        return true
    }
}