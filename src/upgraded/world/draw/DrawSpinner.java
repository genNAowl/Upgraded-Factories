package upgraded.world.draw;

import arc.*;
import arc.graphics.g2d.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter.*;
import mindustry.world.draw.*;

public class DrawSpinner extends DrawBlock{
    public float spinSpeed = 1;
    public TextureRegion rotator;
    public TextureRegion top;

    @Override
    public void draw(GenericCrafterBuild entity){
        float x = entity.x;
        float y = entity.y;
        float totalProgress = entity.totalProgress;
        Block block = entity.block;

        Draw.rect(block.region, x, y);
        Draw.rect(rotator, x, y, totalProgress * 2f * spinSpeed);
        Draw.rect(top, x, y);
    }

    @Override
    public void load(Block block){
        rotator = Core.atlas.find(block.name + "-rotator");
        top = Core.atlas.find(block.name + "-top");
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[] {block.region, rotator, top};
    }
}
