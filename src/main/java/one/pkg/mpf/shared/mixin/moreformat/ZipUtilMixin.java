package one.pkg.mpf.shared.mixin.moreformat;

import one.pkg.pchf.shared.util.ZipTarget;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ZipUtil.class, remap = false)
public class ZipUtilMixin {
    @Inject(method = "supportsMethodOf", at = @At(value = "HEAD"), cancellable = true)
    private static void mpf$supportsMethodOf(ZipArchiveEntry entry, CallbackInfoReturnable<Boolean> cir) {
        if (entry.getMethod() == ZipTarget.ZSTD_METHOD)
            cir.setReturnValue(true);
    }
}
