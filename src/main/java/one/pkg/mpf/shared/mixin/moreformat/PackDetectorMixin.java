package one.pkg.mpf.shared.mixin.moreformat;

import net.minecraft.server.packs.repository.PackDetector;
import one.pkg.pchf.shared.util.ZipTarget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PackDetector.class)
public class PackDetectorMixin {
    @Redirect(method = "detectPackResources", at = @At(value = "INVOKE", target = "Ljava/lang/String;endsWith(Ljava/lang/String;)Z"))
    private boolean mpf$endsWith(String string, String suffix) {
        int supported = ZipTarget.isSupported(string);
        return supported > 0 && supported < 4;
    }
}
