import com.devsteady.playerlock.PlayerLock;
import com.devsteady.playerlock.PlayerLockData;
import com.devsteady.playerlock.Settings;
import com.devsteady.playerlock.events.PlayerLockEvent;
import com.devsteady.playerlock.events.PlayerUnlockEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PlayerLockEvent.class, PlayerUnlockEvent.class, PlayerCommandPreprocessEvent.class,Settings.class,Listener.class,PlayerLockData.class})
public class TestPrisonLock {

    @Rule
    private TemporaryFolder folder = new TemporaryFolder();

    class PlayerLockListener implements Listener {
        public void onPlayerLock(PlayerLockEvent event) {
            if (event.isCancelled()) {
                return;
            }

            event.getPlayer().sendMessage("You have been locked");
        }
    }

    class PlayerUnlockListener implements Listener {
        public void onPlayerLock(PlayerUnlockEvent event) {
            if (event.isCancelled()) {
                return;
            }

            event.getPlayer().sendMessage("You have been locked");
        }
    }

    public TestPrisonLock() {

    }

    @Test
    public void testPlayerLockEvent() {
        /* Create and mock two players:
            Player being locked
            Player issueing the lock
         */
        Player mockPlayer = mock(Player.class);
        Player mockIssuer = mock(Player.class);

        UUID playerId = UUID.randomUUID();
        UUID issuerId = UUID.randomUUID();

        when(mockPlayer.getUniqueId()).thenReturn(playerId);
        when(mockPlayer.getName()).thenReturn("Mocker");

        when(mockIssuer.getUniqueId()).thenReturn(issuerId);
        when(mockIssuer.getName()).thenReturn("Issuer");

        //Create listener
        PlayerLockListener listener = new PlayerLockListener();
        //Create lock event
        PlayerLockEvent event = new PlayerLockEvent(mockPlayer,mockIssuer);

        //Mock the player lock event
        listener.onPlayerLock(event);
        verify(mockPlayer).sendMessage(anyString());
    }

    @Test
    public void testPlayerUnlockEvent() {
        /* Create and mock two players:
            Player being locked
            Player issueing the lock
         */
        Player mockPlayer = mock(Player.class);
        Player mockIssuer = mock(Player.class);

        UUID playerId = UUID.randomUUID();
        UUID issuerId = UUID.randomUUID();

        when(mockPlayer.getUniqueId()).thenReturn(playerId);
        when(mockPlayer.getName()).thenReturn("Mocker");

        when(mockIssuer.getUniqueId()).thenReturn(issuerId);
        when(mockIssuer.getName()).thenReturn("Issuer");

        //Create listener
        PlayerUnlockListener listener = new PlayerUnlockListener();
        //Create lock event
        PlayerUnlockEvent event = new PlayerUnlockEvent(mockPlayer,mockIssuer);

        //Mock the player lock event
        listener.onPlayerLock(event);
        verify(mockPlayer).sendMessage(anyString());
    }
}
