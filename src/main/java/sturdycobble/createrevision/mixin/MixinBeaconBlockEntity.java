package sturdycobble.createrevision.mixin;

import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BeaconBlockEntity.class)
public interface MixinBeaconBlockEntity {

    @Accessor("levels")
    int getPower();

}
