/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.core.config;

import com.google.common.collect.Maps;
import io.github.nucleuspowered.neutrino.annotations.DoNotGenerate;
import io.github.nucleuspowered.nucleus.internal.text.NucleusTextTemplateImpl;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import uk.co.drnaylor.quickstart.config.NoMergeIfPresent;

import java.util.Map;
import java.util.UUID;

@ConfigSerializable
public class CoreConfig {

    @Setting(value = "debug-mode", comment = "config.debugmode")
    private boolean debugmode = false;

    @Setting(value = "print-on-autosave", comment = "config.printonautosave")
    private boolean printOnAutosave = false;

    @Setting(value = "use-custom-message-file", comment = "config.custommessages")
    private boolean custommessages = false;

    @Setting(value = "warmup-canceling", comment = "config.core.warmup.info")
    private WarmupConfig warmupConfig = new WarmupConfig();

    @Setting(value = "command-on-name-click", comment = "config.core.commandonname")
    private String commandOnNameClick = "/msg {{subject}}";

    @Setting(value = "kick-on-stop")
    private KickOnStopConfig kickOnStop = new KickOnStopConfig();

    @DoNotGenerate
    @Setting(value = "enable-doc-gen")
    private boolean enableDocGen = false;

    @DoNotGenerate
    @Setting(value = "simulate-error-on-startup")
    private boolean errorOnStartup = false;

    @Setting(value = "safe-teleport-check", comment = "config.core.safeteleport")
    private SafeTeleportConfig safeTeleportConfig = new SafeTeleportConfig();

    @Setting(value = "console-overrides-exemptions", comment = "config.core.consoleoverrides")
    private boolean consoleOverride = true;

    @DoNotGenerate
    @Setting(value = "trace-user-creations-level")
    private int traceUserCreations = 0;

    @DoNotGenerate
    @Setting(value = "print-file-save-load")
    private boolean printSaveLoad = false;

    @Setting(value = "track-world-uuids", comment = "config.core.track")
    private boolean trackWorldUUIDs = true;

    @NoMergeIfPresent
    @Setting(value = "world-uuid-migration", comment = "config.core.worlduuidmigration")
    private Map<UUID, String> uuidMigration = Maps.newHashMap();

    public boolean isDebugmode() {
        return debugmode;
    }

    public boolean isPrintOnAutosave() {
        return this.printOnAutosave;
    }

    public boolean isCustommessages() {
        return custommessages;
    }

    public WarmupConfig getWarmupConfig() {
        return warmupConfig;
    }

    public String getCommandOnNameClick() {
        return commandOnNameClick;
    }

    public boolean isKickOnStop() {
        return kickOnStop.isKickOnStop();
    }

    public NucleusTextTemplateImpl getKickOnStopMessage() {
        return kickOnStop.getKickOnStopMessage();
    }

    public boolean isEnableDocGen() {
        return enableDocGen;
    }

    public boolean isErrorOnStartup() {
        return errorOnStartup;
    }

    public SafeTeleportConfig getSafeTeleportConfig() {
        return safeTeleportConfig;
    }

    public boolean isConsoleOverride() {
        return consoleOverride;
    }

    /**
     * For debugging. 0 is off, 1 is abnormal players, such as "offline", 2 is everyone.
     * @return The level to debug.
     */
    public int traceUserCreations() {
        return this.traceUserCreations;
    }

    public boolean isPrintSaveLoad() {
        return this.printSaveLoad;
    }

    public boolean isTrackWorldUUIDs() {
        return this.trackWorldUUIDs;
    }

    public Map<UUID, String> getUuidMigration() {
        return this.uuidMigration;
    }
}
