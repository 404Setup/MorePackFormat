package one.pkg.mpf.shared.mixin.pchf;

import one.pkg.pchf.shared.api.MoreFormatAPI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value = MoreFormatAPI.class, remap = false)
public class MoreFormatAPIMixin {
    /**
     * @author 404
     * @reason Enable api
     */
    @Overwrite
    public static boolean isAPIEnabled() {
        return true;
    }
}
