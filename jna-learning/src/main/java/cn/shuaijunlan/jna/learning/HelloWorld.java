package cn.shuaijunlan.jna.learning;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;

/**
 * @author Shuai Junlan[shuaijunlan@gmail.com].
 * @since Created in 10:19 AM 4/12/19.
 */
public class HelloWorld {
    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary)
                Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"),
                        CLibrary.class);
        void printf(String format, Object... args);
    }
    public static void main(String[] args) {
        CLibrary.INSTANCE.printf("Hello, World\n");
    }
}
