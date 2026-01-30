package xyz.vexirio.fluen

import org.bukkit.plugin.java.JavaPlugin
import xyz.vexirio.fluen.modules.Feed
import xyz.vexirio.fluen.modules.Fly
import xyz.vexirio.fluen.modules.GodMode

class FluenMain : JavaPlugin() {
    override fun onEnable() {
        getCommand("fly")?.setExecutor(Fly(this))
        getCommand("feed")?.setExecutor(Feed(this))
        getCommand("godmode")?.setExecutor(GodMode(this))
    }
    override fun onDisable() {}
}