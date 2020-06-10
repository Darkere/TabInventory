package darkere.tabinventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.AbstractCommandBlockScreen;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.glfw.GLFW;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("tabinventory")
public class tabinventory {
    public tabinventory() {
        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> MinecraftForge.EVENT_BUS.register(this));
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void KeyBoardKeyPressed(GuiScreenEvent.KeyboardKeyPressedEvent event) {
        if(Minecraft.getInstance().player == null) return;
        if (event.getKeyCode() == GLFW.GLFW_KEY_TAB && Minecraft.getInstance().gameSettings.keyBindInventory.isActiveAndMatches(InputMappings.getInputByCode(event.getKeyCode(), event.getModifiers()))) {
            if (!(event.getGui() instanceof ChatScreen) && !(event.getGui() instanceof AbstractCommandBlockScreen)) {
                Minecraft.getInstance().player.closeScreen();
                event.setCanceled(true);
            }
        }
    }
}
