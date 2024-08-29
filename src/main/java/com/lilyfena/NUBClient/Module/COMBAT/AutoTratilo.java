package com.lilyfena.NUBClient.Module.COMBAT;

import com.lilyfena.NUBClient.Module.Mod;
import com.lilyfena.NUBClient.Module.settings.NumberSetting;
import net.minecraft.block.Blocks;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AutoTratilo extends Mod {
    public NumberSetting range = new NumberSetting("Range", 1,10, 4, 1);

    public AutoTratilo() {
        super("AutoTratilo", "Automaticly ignite", Category.COMBAT);
        addSettings(
            range
        );
    }

    @Override
    public void onTick() {
        ClientPlayerEntity player = mc.player;
        World world = mc.world;

        if (player != null && world != null) {
            BlockPos playerPos = player.getBlockPos();
            int selectedSlot = player.getInventory().selectedSlot;

            for (int x = -range.getValueInt(); x <= range.getValueInt(); ++x) {
                for (int y = -range.getValueInt(); y <= range.getValueInt(); ++y) {
                    for (int z = -range.getValueInt(); z <= range.getValueInt(); ++z) {
                        BlockPos blockPos = playerPos.add(x, y, z);
                        if (world.getBlockState(blockPos).getBlock() == Blocks.TNT) {
                            igniteTNT(blockPos, Direction.UP);
                        }
                    }
                }
            }

            player.getInventory().selectedSlot = selectedSlot;
        }
    }

    private void igniteTNT(BlockPos pos, Direction dir) {
        int flintSlot = getFlintAndSteelSlot();
        if (flintSlot != -1) {
            ClientPlayerEntity player = mc.player;
            player.getInventory().selectedSlot = flintSlot;

            // Simulate right-click to ignite TNT
            player.swingHand(Hand.MAIN_HAND);

            // Create a BlockHitResult for the interaction
            Vec3d hitVec = new Vec3d(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            BlockHitResult hitResult = new BlockHitResult(hitVec, dir, pos, false);

            // Interact with the block
            mc.interactionManager.interactBlock(player, Hand.MAIN_HAND, hitResult);

            // Reset block breaking progress to avoid accidental breaking
            mc.interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
        }
    }

    private int getFlintAndSteelSlot() {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = mc.player.getInventory().getStack(i);
            if (!stack.isEmpty() && stack.getItem() == Items.FLINT_AND_STEEL) {
                return i;
            }
        }
        return -1;
    }
}


