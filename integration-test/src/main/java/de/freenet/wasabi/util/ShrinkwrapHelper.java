package de.freenet.wasabi.util;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: RSI
 * Date: 18.01.12
 * Time: 15:45
 * To change this template use File | Settings | File Templates.
 */
public class ShrinkwrapHelper {
    public static final String TARGET_CLASSES_META_INF_MANIFEST_MF = "/target/classes/META-INF/MANIFEST.MF";

    public static File manifestFromModule(String moduleName) {
        return new File(new File(moduleName),TARGET_CLASSES_META_INF_MANIFEST_MF);
    }
}
