/*
How to use :3

import xyz.vexirio.fluen.Api

color:
sender.sendMessage(Api.color(plugin.config.getString("no-permission")))
 */



package xyz.vexirio.fluen

import net.md_5.bungee.api.ChatColor
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
}