package upgraded.world.blocks;

import arc.*;
import arc.math.*;
import mindustry.graphics.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.production.LiquidConverter.*;
import mindustry.ui.*;

/** A liquid mixer that takes a long time to warm up. */
public class InertiaConverter extends LiquidConverter{
    public float warmupSpeed = 0.0005f;
    public float cooldownSpeed = 0.001f;

    public InertiaConverter(String name){
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();
        bars.add("multiplier", (InertiaConverterBuild entity) -> new Bar(() ->
        Core.bundle.formatFloat("bar.efficiency", entity.warmup * 100, 1),
        () -> Pal.ammo,
        () -> entity.warmup));
    }

    public class InertiaConverterBuild extends LiquidConverterBuild{
        @Override
        public void updateTile(){
            ConsumeLiquid cl = consumes.get(ConsumeType.liquid);

            if(cons.valid()){
                warmup = Mathf.lerpDelta(warmup, timeScale * efficiency(), warmupSpeed * edelta());
                if(Mathf.equal(warmup, 1f, 0.001f)){
                    warmup = 1f;
                }

                float use = Math.min(cl.amount * warmup, liquidCapacity - liquids.get(outputLiquid.liquid));

                liquids.remove(cl.liquid, Math.min(use, liquids.get(cl.liquid)));

                progress += use / cl.amount * delta() / timeScale;
                liquids.add(outputLiquid.liquid, use);
                if(progress >= craftTime){
                    consume();
                    progress %= craftTime;
                }
            }else{
                warmup = Mathf.lerpDelta(warmup, 0f, cooldownSpeed);
            }
            totalProgress += warmup * delta() / timeScale;

            dumpLiquid(outputLiquid.liquid);
        }
    }
}
