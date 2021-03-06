/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.world;

import io.github.nucleuspowered.nucleus.internal.annotations.RegisterService;
import io.github.nucleuspowered.nucleus.internal.qsml.module.ConfigurableModule;
import io.github.nucleuspowered.nucleus.modules.world.config.WorldConfigAdapter;
import uk.co.drnaylor.quickstart.annotations.ModuleData;

@RegisterService(WorldHelper.class)
@ModuleData(id = WorldModule.ID, name = "World")
public class WorldModule extends ConfigurableModule<WorldConfigAdapter> {

    public static final String ID = "world";

    @Override public WorldConfigAdapter createAdapter() {
        return new WorldConfigAdapter();
    }

}
