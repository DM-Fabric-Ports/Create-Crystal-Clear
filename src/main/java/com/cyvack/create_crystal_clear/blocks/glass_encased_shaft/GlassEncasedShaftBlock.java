package com.cyvack.create_crystal_clear.blocks.glass_encased_shaft;

import com.cyvack.create_crystal_clear.blocks.glass_casings.GlassCasing;
import com.cyvack.create_crystal_clear.blocks.ModBlocks;
import com.cyvack.create_crystal_clear.tile_entities.ModtileEntities;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.content.contraptions.relays.encased.AbstractEncasedShaftBlock;
import com.simibubi.create.content.schematics.ISpecialBlockItemRequirement;
import com.simibubi.create.content.schematics.ItemRequirement;
import com.simibubi.create.foundation.block.ITE;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;


public class GlassEncasedShaftBlock extends AbstractEncasedShaftBlock implements ITE<KineticTileEntity>, ISpecialBlockItemRequirement {

	private BlockEntry<GlassCasing> GlassCasing;

	public static GlassEncasedShaftBlock andesiteglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.ANDESITE_GLASS_CASING);
	}
	public static GlassEncasedShaftBlock andesiteclearglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.ANDESITE_CLEAR_GLASS_CASING);
	}
	public static GlassEncasedShaftBlock brassglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.BRASS_GLASS_CASING);
	}
	public static GlassEncasedShaftBlock brassclearglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.BRASS_CLEAR_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock trainglass(Properties properties){
		return new GlassEncasedShaftBlock(properties, ModBlocks.TRAIN_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock trainclearglass(Properties properties){
		return new GlassEncasedShaftBlock(properties, ModBlocks.TRAIN_CLEAR_GLASS_CASING);
	}

	public static GlassEncasedShaftBlock steelglass(Properties properties) {
		return new GlassEncasedShaftBlock(properties, ModBlocks.STEEL_GLASS_CASING);
	}



	public  GlassEncasedShaftBlock(Properties properties, BlockEntry<GlassCasing> GlassCasing) {
		super(properties);
		this.GlassCasing = GlassCasing;
	}

	public BlockEntry<GlassCasing> getCasing() {
		return GlassCasing;
	}

	@Override
	public void fillItemCategory(CreativeModeTab pTab, NonNullList<ItemStack> pItems) {}

	@Override
	public InteractionResult onSneakWrenched(BlockState state, UseOnContext context) {
		if (context.getLevel().isClientSide)
			return InteractionResult.SUCCESS;
		context.getLevel()
				.levelEvent(2001, context.getClickedPos(), Block.getId(state));
		KineticTileEntity.switchToBlockState(context.getLevel(), context.getClickedPos(),
				AllBlocks.SHAFT.getDefaultState()
						.setValue(AXIS, state.getValue(AXIS)));
		return InteractionResult.SUCCESS;
	}

	@Override
	public ItemRequirement getRequiredItems(BlockState state, BlockEntity te) {
		return ItemRequirement.of(AllBlocks.SHAFT.getDefaultState(), te);
	}

	@Override
	public Class<KineticTileEntity> getTileEntityClass() {
		return KineticTileEntity.class;
	}

	@Override
	public BlockEntityType<? extends KineticTileEntity> getTileEntityType() {
		return ModtileEntities.GLASS_ENCASED_SHAFT.get();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction side) {
		return ((pState.getBlock() instanceof GlassEncasedShaftBlock) && (pAdjacentBlockState.getBlock() instanceof GlassEncasedShaftBlock));
	}

	@SuppressWarnings("deprecation")
	public float getShadeBrightness(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
		return 1.0F;
	}

	public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
		return true;
	}
}
