package upgraded.world.draw;

import arc.*;
import arc.math.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.util.*;
import mindustry.graphics.*;
import mindustry.world.*;
import mindustry.world.blocks.production.GenericCrafter.*;
import mindustry.world.draw.*;

public class DrawSmelter extends DrawBlock{
    public Color flameColor = Color.valueOf("ffc999");
    public TextureRegion top;

    @Override
    public void draw(GenericCrafterBuild entity){
        float x = entity.x;
        float y = entity.y;
        float warmup = entity.warmup;
        Block block = entity.block;

        Draw.rect(block.region, x, y);
        if(warmup > 0f && flameColor.a > 0.001f){
            float g = 0.3f;
            float r = 0.06f;
            float cr = Mathf.random(0.1f);

            Draw.z(Layer.block + 0.01f);
            Draw.alpha(((1f - g) + Mathf.absin(Time.time, 8f, g) + Mathf.random(r) - r) * warmup);
            Draw.tint(flameColor);
            Fill.circle(x, y, 3f + Mathf.absin(Time.time, 5f, 2f) + cr);
            Draw.color(1f, 1f, 1f, warmup);
            Draw.rect(top, x, y);
            Fill.circle(x, y, 1.9f + Mathf.absin(Time.time, 5f, 1f) + cr);
            Draw.color();
        }
    }

    @Override
    public void load(Block block){
        top = Core.atlas.find(block.name + "-top");
    }

    @Override
    public TextureRegion[] icons(Block block){
        return new TextureRegion[] {block.region};
    }
}
