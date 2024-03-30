package com.redpxnda.findingfortunes.networking;

import com.redpxnda.findingfortunes.client.FortuneRenamingHandler;
import dev.architectury.networking.NetworkManager;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.function.Supplier;

public class InformFortuneNamePacket {
    protected final String text;

    public InformFortuneNamePacket(String text) {
        this.text = text;
    }

    public InformFortuneNamePacket(PacketByteBuf buf) {
        text = buf.readString();
    }

    public void toBytes(PacketByteBuf buf) {
        buf.writeString(text);
    }

    public void handle(Supplier<NetworkManager.PacketContext> supplier) {
        NetworkManager.PacketContext context = supplier.get();
        context.queue(() -> {
            if (context.getPlayer() instanceof ServerPlayerEntity player && player.currentScreenHandler instanceof FortuneRenamingHandler handler) {
                handler.rename(Text.literal(text));
            }
        });
    }
}
