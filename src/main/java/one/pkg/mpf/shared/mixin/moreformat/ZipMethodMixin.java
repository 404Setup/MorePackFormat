package one.pkg.mpf.shared.mixin.moreformat;

import one.pkg.pchf.shared.util.ZipTarget;
import org.apache.commons.compress.archivers.zip.ZipMethod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ZipMethod.class, remap = false)
public class ZipMethodMixin {
    @Inject(method = "getMethodByCode", at = @At(value = "HEAD"), cancellable = true)
    private static void mpf$getMethodByCode(int code, CallbackInfoReturnable<ZipMethod> cir) {
        if (code == ZipTarget.ZSTD_METHOD)
            cir.setReturnValue(ZipMethod.UNKNOWN);
    }
}
