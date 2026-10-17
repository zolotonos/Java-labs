import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;

public class MainHardTask {
    public static void main(String[] args) throws Exception {
        File sourceFile = new File("TestModule.java");
        long lastModified = 0;
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        while (true) {
            if (sourceFile.lastModified() > lastModified) {
                compiler.run(null, null, null, sourceFile.getPath());
                lastModified = sourceFile.lastModified();

                CustomClassLoader loader = new CustomClassLoader(".");
                Class<?> clazz = loader.loadClass("TestModule");
                Object instance = clazz.getDeclaredConstructor().newInstance();
                System.out.println(instance.toString());
            }
            Thread.sleep(2000);
        }
    }
}