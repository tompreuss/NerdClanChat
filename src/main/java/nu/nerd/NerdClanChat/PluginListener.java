package nu.nerd.NerdClanChat;


import nu.nerd.NerdClanChat.database.PlayerMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PluginListener implements Listener {


    private final NerdClanChat plugin;


    public PluginListener(NerdClanChat plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void updateStoredPlayerName(PlayerLoginEvent event) {

        String UUID = event.getPlayer().getUniqueId().toString();
        String name = event.getPlayer().getName();
        PlayerMeta meta = plugin.playerMetaCache.getPlayerMeta(UUID);

        if ( meta.getName() != null && !meta.getName().equals(name) ) {
            plugin.channelMembersTable.updateChannelMemberNames(UUID, name);
        }

        meta.setName(name);
        plugin.playerMetaCache.updatePlayerMeta(UUID, meta);

    }


}