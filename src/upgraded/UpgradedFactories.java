package upgraded;

import upgraded.content.*;
import mindustry.ctype.*;
import mindustry.mod.*;

import static mindustry.Vars.*;

public class UpgradedFactories extends Mod{
    public final ContentList[] upgradedContent = {
		new UpgradedBlocks()
	};

    @Override
    public void init(){
    }

    @Override
    public void loadContent(){
        for(ContentList list : upgradedContent){
            list.load();
        }
    }
}
