/*
How to use :3
color:
sender.sendMessage(Api.color(plugin.config.getString("no-permission")))

permission:
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

if (!Api.permission("fluen.admin", sender, this)) return true
 */



package xyz.vexirio.fluen

import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.util.regex.Matcher
import java.util.regex.Pattern

object Api {
    // from my another plugin
    fun color(
        text: String?
    ): String {
        var text = text ?: return ""

        text = ChatColor.translateAlternateColorCodes('&', text)

        val hex: Pattern = Pattern.compile("(?i)#([0-9a-f]{6})")
        val matcher: Matcher = hex.matcher(text)

        val buffer = StringBuilder()
        while (matcher.find()) {
            matcher.appendReplacement(
                buffer,
                ChatColor.of("#" + matcher.group(1)).toString()
            )
        }
        matcher.appendTail(buffer)

        return buffer.toString()
    }

    fun permission(
        permission: String, // PluginName.Permission
        sender: CommandSender,
        plugin: JavaPlugin
    ): Boolean {
        if (!sender.hasPermission(permission)) {
            sender.sendMessage(plugin.config.getString("no-permission"))
            return false
        } else {
            return true
        }
    }
}