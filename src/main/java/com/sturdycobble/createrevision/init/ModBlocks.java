package com.sturdycobble.createrevision.init;

import com.sturdycobble.createrevision.CreateRevision;
import com.sturdycobble.createrevision.contents.heat.FrictionHeaterBlock;
import com.sturdycobble.createrevision.contents.heat.HeatEngineBlock;
import com.sturdycobble.createrevision.contents.heat.HeatExchangerBlock;
import com.sturdycobble.createrevision.contents.heat.HeatPipeBlock;
import com.sturdycobble.createrevision.contents.heat.ThermometerBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, CreateRevision.MODID);
	
	public static final RegistryObject<Block> HEAT_PIPE = BLOCKS.register("heat_pipe",
			() -> new HeatPipeBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(6F, 6F)));
	public static final RegistryObject<Block> THERMOMETER = BLOCKS.register("thermometer",
			() -> new ThermometerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(0.5F, 0.5F)));
	public static final RegistryObject<Block> FRICTION_HEATER = BLOCKS.register("friction_heater",
			() -> new FrictionHeaterBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(1.0F, 1.0F)));
	public static final RegistryObject<Block> HEAT_EXCHANGER = BLOCKS.register("heat_exchanger",
			() -> new HeatExchangerBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(6F, 6F)));
	public static final RegistryObject<Block> HEAT_ENGINE = BLOCKS.register("heat_engine",
			() -> new HeatEngineBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(1F, 3F)));

}