/*
 * This file is part of Nucleus, licensed under the MIT License (MIT). See the LICENSE.txt file
 * at the root of this project for more details.
 */
package io.github.nucleuspowered.nucleus.modules.kit.commands.kit;

import io.github.nucleuspowered.nucleus.api.nucleusdata.Kit;
import io.github.nucleuspowered.nucleus.argumentparsers.KitArgument;
import io.github.nucleuspowered.nucleus.internal.annotations.RunAsync;
import io.github.nucleuspowered.nucleus.internal.annotations.command.NoModifiers;
import io.github.nucleuspowered.nucleus.internal.annotations.command.Permissions;
import io.github.nucleuspowered.nucleus.internal.annotations.command.RegisterCommand;
import io.github.nucleuspowered.nucleus.internal.command.AbstractCommand;
import io.github.nucleuspowered.nucleus.internal.permissions.SuggestedLevel;
import io.github.nucleuspowered.nucleus.modules.kit.commands.KitFallbackBase;
import io.github.nucleuspowered.nucleus.modules.kit.handlers.KitHandler;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.annotation.NonnullByDefault;

@Permissions(prefix = "kit", suggestedLevel = SuggestedLevel.ADMIN)
@RegisterCommand(value = {"setfirstjoin", "firstjoin"}, subcommandOf = KitCommand.class)
@RunAsync
@NoModifiers
@NonnullByDefault
public class KitSetFirstJoinCommand extends KitFallbackBase<CommandSource> {

    private final String toggle = "true|false";

    @Override
    public CommandElement[] getArguments() {
        return new CommandElement[] {
            GenericArguments.onlyOne(new KitArgument(Text.of(KIT_PARAMETER), false)),
            GenericArguments.onlyOne(GenericArguments.bool(Text.of(toggle)))
        };
    }

    @Override
    public CommandResult executeCommand(final CommandSource player, CommandContext args) throws Exception {
        Kit kitInfo = args.<Kit>getOne(KIT_PARAMETER).get();
        boolean b = args.<Boolean>getOne(toggle).orElse(false);

        // This Kit is a reference back to the version in list, so we don't need
        // to update it explicitly
        kitInfo.setFirstJoinKit(b);
        KIT_HANDLER.saveKit(kitInfo);
        player.sendMessage(plugin.getMessageProvider().getTextMessageWithFormat(
                b ? "command.kit.setfirstjoin.on" : "command.kit.setfirstjoin.off",
                kitInfo.getName()));

        return CommandResult.success();
    }
}
