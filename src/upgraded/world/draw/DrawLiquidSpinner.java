package upgraded.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.production.GenericCrafter.*;
import mindustry.world.draw.*;

public class DrawLiquidSpinner extends DrawSpinner{
    public TextureRegion liquid;

    @Override
    public void draw(GenericCrafterBuild entity){
        float x = entity.x;
        float y = entity.y;
        float totalProgress = entity.totalProgress;
        GenericCrafter block = (GenericCrafter)entity.block;

        Draw.rect(block.region, x, y);
        if(entity.liquids.total() > 0.001f){
            Draw.color(block.outputLiquid.liquid.color);
            Draw.alpha(entity.liquids.get(block.outputLiquid.liquid) / block.liquidCapacity);
            Draw.rect(liquid, x, y);
            Draw.color();
        }
        Draw.rect(rotator, x, y, totalProgress * 2f * spinSpeed);
        Draw.rect(top, x, y);
    }

    @Override
    public void load(Block block){
        super.load(block);
        liquid = Core.atlas.find(block.name + "-liquid");
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[] {block.region, rotator, top};
    }
}
