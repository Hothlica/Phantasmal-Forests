package net.hothlica.phantasmal_forests.world.gen.feature.tree.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.hothlica.phantasmal_forests.registry.ModFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class MudwoodFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<MudwoodFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            MudwoodFoliagePlacer.foliagePlacerParts(instance).apply(instance, MudwoodFoliagePlacer::new));

    public MudwoodFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.MUDWOOD;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config, int trunkHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        double maxFoliageRadius = 1.5 + 1.5 * random.nextDouble();
        int pinnacle = (int)((trunkHeight * 1.5) - trunkHeight);
        BlockPos.MutableBlockPos pos = foliageAttachment.pos().mutable();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        double currTreeRadius;
        // 0 is the top of the trunk (trunkHeight)
        for (int dy = -trunkHeight; dy < pinnacle; dy++) {
            pos.set(x, y + dy, z);
            double upTrunk = (double) (trunkHeight + dy) / (trunkHeight + pinnacle);
            currTreeRadius = maxFoliageRadius * (6.5 * (upTrunk * upTrunk * upTrunk) - 13 * (upTrunk * upTrunk) + 6.5 * upTrunk);
            if (currTreeRadius < 0) continue;

            // Add circle layer x^2 + z^2 <= r^2
            BlockPos.MutableBlockPos newPos = pos.mutable();
            int radiusCeiling = (int) Math.ceil(currTreeRadius);
            double currTreeRadiusSq = currTreeRadius * currTreeRadius;
            int dxMax = radiusCeiling;
            for (int dz = -radiusCeiling; dz <= radiusCeiling; dz++) {
                int dzSq = dz * dz;
                while (dxMax > 0 && dzSq + dxMax * dxMax > currTreeRadiusSq) {
                    dxMax--;
                }
                for (int dx = -dxMax; dx <= dxMax; dx++) {
                    if ((dx == dxMax || dx == -dxMax || dz == radiusCeiling || dz == -radiusCeiling) && random.nextBoolean()) continue;
                    newPos.set(x + dx, pos.getY(), z + dz);
                    if (TreeFeature.isAirOrLeaves(level, newPos)) {
                        foliageSetter.set(newPos.immutable(), config.foliageProvider.getState(level, random, newPos));
                    }
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return 0;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return false;
    }
}
