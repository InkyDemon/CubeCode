package thebendy.cubecode.api.scripts.code;

import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import thebendy.cubecode.api.scripts.code.cubecode.CubeCodeStates;
import thebendy.cubecode.api.scripts.code.entities.ScriptPlayer;

import java.util.ArrayList;
import java.util.List;

public class ScriptServer {
    private MinecraftServer server;

    public ScriptServer(MinecraftServer server) {
        this.server = server;
    }

    public List<ScriptPlayer> getAllPlayers() {
        List<ScriptPlayer> players = new ArrayList<>();
        this.server.getPlayerManager().getPlayerList().forEach((player) -> players.add((ScriptPlayer) ScriptPlayer.create(player)));

        return players;
    }

    public int getMaxPlayerCount() {
        return this.server.getMaxPlayerCount();
    }

    public boolean isSingleplayer() {
        return this.server.isSingleplayer();
    }

    public boolean isDedicated() {
        return this.server.isDedicated();
    }

    public boolean isHardcore() {
        return this.server.isHardcore();
    }

    public boolean isOnlineMode() {
        return this.server.isOnlineMode();
    }

    public int getTicks() {
        return this.server.getTicks();
    }

    public void send(String message, boolean overlay) {
        this.server.getPlayerManager().broadcast(Text.of(message), overlay);
    }

    public ScriptPlayer getPlayer(String nickName) {
        return (ScriptPlayer) ScriptPlayer.create(this.server.getPlayerManager().getPlayer(nickName));
    }

    public CubeCodeStates getStates() {
        return new CubeCodeStates(this.server);
    }
}