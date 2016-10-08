package ttftcuts.atg.generator;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.NoiseGeneratorSimplex;
import ttftcuts.atg.ATG;

import java.util.Arrays;

public class ChunkProviderATG extends ChunkProviderBasic {
    NoiseGeneratorSimplex testnoise;

    public ChunkProviderATG(World world) {
        super(world);

        testnoise = new NoiseGeneratorSimplex(world.rand);
    }

    @Override
    public void fillChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
        //this.depthBuffer = testnoise.getRegion(depthBuffer, chunkX*16.0, chunkZ*16.0, 16,16, 0.0625, 0.0625,1.0);

        IBlockState iblockstate = Blocks.STONE.getDefaultState();

        //ATG.logger.info(Arrays.toString(this.depthBuffer));
        double scale = 1.0/100.0;

        for (int ix = 0; ix < 16; ++ix)
        {
            for (int iz = 0; iz < 16; ++iz)
            {
                double x = chunkX*16.0 + ix;
                double z = chunkZ*16.0 + iz;
                double n = testnoise.getValue(x*scale, z*scale);
                int height = (int)Math.round(n*20 + 120);//(int)Math.round(this.depthBuffer[ix+iz*16] * 255);
                for (int iy = 0; iy < height; ++iy)
                {
                    primer.setBlockState(ix, iy, iz, iblockstate);
                }
            }
        }
    }
}