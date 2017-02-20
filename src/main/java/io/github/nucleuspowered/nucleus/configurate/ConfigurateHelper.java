/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.configurate;

import com.flowpowered.math.vector.Vector3d;
import com.google.common.reflect.TypeToken;
import io.github.nucleuspowered.nucleus.configurate.objectmapper.NucleusObjectMapperFactory;
import io.github.nucleuspowered.nucleus.configurate.typeserialisers.NucleusItemStackSnapshotSerialiser;
import io.github.nucleuspowered.nucleus.configurate.typeserialisers.NucleusTextTemplateTypeSerialiser;
import io.github.nucleuspowered.nucleus.configurate.typeserialisers.PatternTypeSerialiser;
import io.github.nucleuspowered.nucleus.configurate.typeserialisers.SetTypeSerialiser;
import io.github.nucleuspowered.nucleus.configurate.typeserialisers.Vector3dTypeSerialiser;
import io.github.nucleuspowered.nucleus.configurate.wrappers.NucleusItemStackSnapshot;
import io.github.nucleuspowered.nucleus.internal.text.NucleusTextTemplateImpl;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.commented.SimpleCommentedConfigurationNode;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializerCollection;
import org.spongepowered.api.GameState;
import org.spongepowered.api.Sponge;

import java.util.Set;
import java.util.regex.Pattern;

public class ConfigurateHelper {

    private ConfigurateHelper() {}

    private static TypeSerializerCollection typeSerializerCollection = null;

    public static CommentedConfigurationNode getNewNode() {
        return SimpleCommentedConfigurationNode.root(setOptions(ConfigurationOptions.defaults()));
    }

    /**
     * Set NucleusPlugin specific options on the {@link ConfigurationOptions}
     *
     * @param options The {@link ConfigurationOptions} to alter.
     * @return The {@link ConfigurationOptions}, for easier inline use of this function.
     */
    public static ConfigurationOptions setOptions(ConfigurationOptions options) {
        TypeSerializerCollection tsc = getNucleusTypeSerialiserCollection();

        // Allows us to use localised comments and @ProcessSetting annotations
        return options.setSerializers(tsc).setObjectMapperFactory(NucleusObjectMapperFactory.getInstance());
    }

    private static TypeSerializerCollection getNucleusTypeSerialiserCollection() {
        if (typeSerializerCollection != null) {
            return typeSerializerCollection;
        }

        TypeSerializerCollection tsc = ConfigurationOptions.defaults().getSerializers();

        // Custom type serialisers for Nucleus
        tsc.registerType(TypeToken.of(Vector3d.class), new Vector3dTypeSerialiser());
        tsc.registerType(TypeToken.of(NucleusItemStackSnapshot.class), new NucleusItemStackSnapshotSerialiser());
        tsc.registerType(TypeToken.of(Pattern.class), new PatternTypeSerialiser());
        tsc.registerType(TypeToken.of(NucleusTextTemplateImpl.class), new NucleusTextTemplateTypeSerialiser());
        tsc.registerPredicate(
                typeToken -> Set.class.isAssignableFrom(typeToken.getRawType()),
                new SetTypeSerialiser()
        );

        if (Sponge.getGame().getState() == GameState.SERVER_STARTED) {
            typeSerializerCollection = tsc;
        }

        return tsc;
    }
}
