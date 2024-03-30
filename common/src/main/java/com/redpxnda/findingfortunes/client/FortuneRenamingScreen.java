package com.redpxnda.findingfortunes.client;

import com.redpxnda.findingfortunes.networking.FFPackets;
import com.redpxnda.findingfortunes.networking.InformFortuneNamePacket;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static com.redpxnda.findingfortunes.FindingFortunes.MOD_ID;

public class FortuneRenamingScreen extends HandledScreen<FortuneRenamingHandler> {
    public static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/gui/fortune_renamer.png");

    public FortuneRenamingScreen(FortuneRenamingHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        TextFieldWidget field = new TextFieldWidget(textRenderer, width/2 - 74, height/2 - 4, 148, 16, Text.empty());
        field.setDrawsBackground(false);
        field.setChangedListener(this::rename);
        field.setMaxLength(160);
        addDrawableChild(field);
        setInitialFocus(field);
    }

    public void rename(String name) {
        FFPackets.CHANNEL.sendToServer(new InformFortuneNamePacket(name));
    }

    @Override
    protected void drawForeground(DrawContext context, int mouseX, int mouseY) {
        //context.drawText(textRenderer, title, titleX, titleY, 0x404040, false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            close();
            return true;
        }
        return getFocused() != null && getFocused().keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        context.drawTexture(TEXTURE, width/2-88, height/2-19, 0, 0, 0, 176, 38, 176, 38);
    }
}
