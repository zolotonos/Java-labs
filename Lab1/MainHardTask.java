import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class MainHardTask {
    public static void main(String[] args) throws Exception {
        Path sourcePath = Path.of("TestModule.java");
        byte[] lastBytes = new byte[0];
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        while (true) {
            byte[] currentBytes = Files.readAllBytes(sourcePath);
            if (currentBytes.length > 0 && !Arrays.equals(currentBytes, lastBytes)) {
                Thread.sleep(100);
                if (compiler.run(null, null, null, sourcePath.toString()) == 0) {
                    lastBytes = currentBytes;
                    CustomClassLoader loader = new CustomClassLoader(".");
                    Class<?> clazz = loader.loadClass("TestModule");
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    System.out.println(instance);
                }
            }
            Thread.sleep(500);
        }
    }
}