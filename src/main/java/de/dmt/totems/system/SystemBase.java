package de.dmt.totems.system;

import de.dmt.totems.util.Log;

public abstract class SystemBase {

    public final String name;

    private boolean initialized;

    public SystemBase(String name) {
        this.name = name;
        this.initialized = false;
    }

    public synchronized void initialize() throws Exception {
        if (initialized) {
            Log.warnf("Tried to initialize system \"%s\" multiple times. Ignoring...", name);
            return;
        }

        Log.infof("Initializing system \"%s\"", name);
        doInitialize();
        this.initialized = true;
    }

    protected abstract void doInitialize() throws Exception;
}