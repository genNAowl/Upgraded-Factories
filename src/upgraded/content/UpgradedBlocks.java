package upgraded.content;

import arc.graphics.*;
import mindustry.content.*;
import mindustry.content.TechTree.*;
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.production.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import upgraded.world.blocks.*;
import upgraded.world.draw.*;

import static mindustry.content.Items.*;
import static mindustry.content.Liquids.*;
import static mindustry.type.ItemStack.*;

public class UpgradedBlocks implements ContentList{
	public static Block 
    furnace, sporeCompactor, crusher, coalSynthesizer, waterExtorter, nurturer, oilExtorter;

	@Override
    public void load(){
        furnace = new AttributeCrafter("furnace"){{
            requirements(Category.crafting, with(titanium, 120, metaglass, 80, plastanium, 35, graphite, 200));
            TechNode node = new TechNode(TechTree.get(Blocks.kiln), this, researchRequirements());
            outputItem = new ItemStack(metaglass, 8);
            craftTime = 65f;
            size = 3;
            itemCapacity = 30;
            boostScale = 0.15f;
            drawer = new DrawSmelter();
            craftEffect = Fx.smeltsmoke;

            consumes.items(with(sand, 3, lead, 3, pyratite, 1));
            consumes.power(5f);
        }};
        sporeCompactor = new GenericCrafter("spore-compactor"){{
            requirements(Category.crafting, with(lead, 100, silicon, 150, metaglass, 80, plastanium, 40));
            TechNode node = new TechNode(TechTree.get(Blocks.sporePress), this, researchRequirements());
            outputItem = new ItemStack(coal, 1);
            outputLiquid = new LiquidStack(oil, 30);
            craftTime = 20;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 120;
            hasLiquids = true;
            drawer = new DrawAnimation();

            consumes.power(5);
            consumes.item(sporePod, 4);
        }};
        crusher = new GenericCrafter("crusher"){{
            requirements(Category.crafting, with(copper, 100, graphite, 75, silicon, 50, plastanium, 20));
            TechNode node = new TechNode(TechTree.get(Blocks.pulverizer), this, researchRequirements());
            outputItem = new ItemStack(sand, 5);
            craftTime = 30;
            size = 2;
            itemCapacity = 20;
            liquidCapacity = 50;
            drawer = new DrawRotator();
            craftEffect = Fx.pulverize;
            updateEffect = Fx.pulverizeMedium;
            updateEffectChance = 0.05f;

            consumes.power(0.5f);
            consumes.liquid(water, 0.05f);
            consumes.item(scrap, 5);
        }};
        coalSynthesizer = new GenericCrafter("coal-synthesizer"){{
            requirements(Category.crafting, with(lead, 200, graphite, 150, titanium, 100, phaseFabric, 50));
            TechNode node = new TechNode(TechTree.get(Blocks.coalCentrifuge), this, researchRequirements());
            outputItem = new ItemStack(coal, 15);
            craftTime = 60;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 150;
            craftEffect = Fx.smeltsmoke;

            consumes.power(1.5f);
            consumes.item(sporePod, 1);
            consumes.liquid(oil, 0.5f);
        }};
        waterExtorter = new SolidPump("water-extorter"){{
            requirements(Category.production, with(metaglass, 100, graphite, 75, titanium, 50, plastanium, 25));
            TechNode node = new TechNode(TechTree.get(Blocks.waterExtractor), this, researchRequirements());
            result = water;
            pumpAmount = 0.4f;
            size = 3;
            liquidCapacity = 100f;
            attribute = Attribute.water;
            rotateSpeed = 1.4f;

            consumes.power(4f);
        }};
        nurturer = new Cultivator("nurturer"){{
            requirements(Category.production, with(lead, 150, silicon, 150, titanium, 125, plastanium, 75));
            TechNode node = new TechNode(TechTree.get(Blocks.cultivator), this, researchRequirements());
            outputItem = new ItemStack(sporePod, 10);
            craftTime = 240;
            size = 3;
            itemCapacity = 30;
            liquidCapacity = 100;
            hasItems = true;

            consumes.power(8f);
            consumes.liquid(Liquids.water, 0.75f);
        }};
        oilExtorter = new Fracker("oil-extorter"){{
            requirements(Category.production, with(copper, 300, graphite, 350, silicon, 250, thorium, 200, plastanium, 150, surgeAlloy, 100));
            TechNode node = new TechNode(TechTree.get(Blocks.oilExtractor), this, researchRequirements());
            result = oil;
            pumpAmount = 0.75f;
            itemUseTime = 60f;
            size = 4;
            itemCapacity = 40;
            liquidCapacity = 90f;
            attribute = Attribute.oil;
            baseEfficiency = 0f;
            updateEffect = Fx.pulverize;
            updateEffectChance = 0.05f;
            rotateSpeed = 1.4f;

            consumes.item(sand, 2);
            consumes.power(10f);
            consumes.liquid(water, 0.4f);
        }};
    }
}