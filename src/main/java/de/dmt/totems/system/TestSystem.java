package de.dmt.totems.system;

import de.dmt.totems.util.Log;

public class TestSystem extends SystemBase {
    public TestSystem() {
        super("Test");
    }

    @Override
    protected void doInitialize() throws Exception {
        Log.info("");
    }
}
