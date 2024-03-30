package com.redpxnda.findingfortunes.facet;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.DataResult;
import com.redpxnda.nucleus.facet.FacetKey;
import com.redpxnda.nucleus.facet.item.ItemStackFacet;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtOps;
import net.minecraft.text.Text;
import net.minecraft.util.dynamic.Codecs;

import static com.redpxnda.findingfortunes.FindingFortunes.LOGGER;

public class FortuneData implements ItemStackFacet<FortuneData, NbtCompound> {
    public static FacetKey<FortuneData> KEY;

    public Text text = Text.empty();

    @Override
    public NbtCompound toNbt() {
        NbtCompound root = new NbtCompound();

        Either<NbtElement, DataResult.PartialResult<NbtElement>> result = Codecs.TEXT.encodeStart(NbtOps.INSTANCE, text).get();
        result.ifLeft(e -> root.put("text", e));
        result.ifRight(p -> LOGGER.error("Failed to encode text for FortuneData! -> {}", p.message()));

        return root;
    }

    @Override
    public void loadNbt(NbtCompound nbt) {
        if (nbt.contains("text")) {
            Either<Text, DataResult.PartialResult<Text>> result = Codecs.TEXT.parse(NbtOps.INSTANCE, nbt.get("text")).get();
            result.ifLeft(t -> text = t);
            result.ifRight(p -> LOGGER.error("Failed to decode text for FortuneData! -> {}", p.message()));
        }
    }

    public void update(ItemStack stack) {
        updateNbtOf(KEY, stack);
    }

    @Override
    public void onCopied(FortuneData original) {
        text = original.text.copy();
    }
}
