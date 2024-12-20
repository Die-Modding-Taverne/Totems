package de.dmt.totems;

import de.dmt.totems.system.SystemBase;
import de.dmt.totems.system.TestSystem;
import de.dmt.totems.util.Log;
import de.dmt.totems.util.StringUtil;
import net.fabricmc.api.ModInitializer;

import java.util.List;

public class Totems implements ModInitializer {
    public static final String MOD_ID = "Totems";
    public static final List<SystemBase> systems = List.of( new TestSystem());

    @Override
    public void onInitialize() {
        for (SystemBase systemBase : systems) {
            try {
                systemBase.initialize();
            } catch (Exception exception) {
                Log.errorf("Error while initializing system \"%s\":\n%s", systemBase.name, StringUtil.fromException(exception));
                throw new RuntimeException(exception);
            }
        }
    }
}
