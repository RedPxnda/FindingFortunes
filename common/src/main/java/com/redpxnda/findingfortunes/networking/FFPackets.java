package com.redpxnda.findingfortunes.networking;

import dev.architectury.networking.NetworkChannel;
import net.minecraft.util.Identifier;

import static com.redpxnda.findingfortunes.FindingFortunes.MOD_ID;

public class FFPackets {
    public static final NetworkChannel CHANNEL = NetworkChannel.create(new Identifier(MOD_ID, "main"));

    public static void init() {
        CHANNEL.register(InformFortuneNamePacket.class, InformFortuneNamePacket::toBytes, InformFortuneNamePacket::new, InformFortuneNamePacket::handle);
    }
}
