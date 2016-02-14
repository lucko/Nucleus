package uk.co.drnaylor.minecraft.quickstart.commands.teleport;


import com.google.inject.Inject;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import uk.co.drnaylor.minecraft.quickstart.Util;
import uk.co.drnaylor.minecraft.quickstart.api.PluginModule;
import uk.co.drnaylor.minecraft.quickstart.internal.CommandBase;
import uk.co.drnaylor.minecraft.quickstart.internal.annotations.*;
import uk.co.drnaylor.minecraft.quickstart.internal.services.TeleportHandler;

/**
 * /tpdeny.
 */
@Modules(PluginModule.TELEPORT)
@Permissions(root = "teleport", includeUser = true, includeMod = true)
@NoWarmup
@NoCooldown
@NoCost
public class TeleportDenyCommand extends CommandBase<Player> {
    @Inject private TeleportHandler teleportHandler;

    @Override
    public CommandSpec createSpec() {
        return CommandSpec.builder().executor(this).build();
    }

    @Override
    public String[] getAliases() {
        return new String[] { "tpdeny", "teleportdeny" };
    }

    @Override
    public CommandResult executeCommand(Player src, CommandContext args) throws Exception {
        boolean denied = teleportHandler.remove(src.getUniqueId());
        src.sendMessage(Text.of(TextColors.GREEN, Util.messageBundle.getString(denied ? "command.tpdeny.deny" : "command.tpdeny.fail")));
        return CommandResult.success();
    }
}
