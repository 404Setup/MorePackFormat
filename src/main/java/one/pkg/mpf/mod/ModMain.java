package one.pkg.mpf.mod;

import com.aayushatharva.brotli4j.Brotli4jLoader;
import net.fabricmc.api.ModInitializer;

public class ModMain implements ModInitializer {
    @Override
    public void onInitialize() {
        Brotli4jLoader.ensureAvailability();
    }
}
