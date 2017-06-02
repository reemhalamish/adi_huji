package filesprocessing;
import java.io.File;

/**
 * Created by adi on 25/05/17.
 */

/**
 * this class saves all the relevant info of a certain file.
 */
public class FileInfo {

    private static final double BYTES_TO_KB = 1024;

    /* fields */
    public double size;
    public String name;
    public boolean writable;
    public boolean executable;
    public boolean hidden;
    public String absolutePath;

    public FileInfo(double size, String name, boolean writable, boolean executable, boolean hidden, String absolutePath) {
        this.size = size/BYTES_TO_KB;
        this.name = name;
        this.writable = writable;
        this.executable = executable;
        this.hidden = hidden;
        this.absolutePath = absolutePath;
    }

    public FileInfo(File file) {
        this.name = file.getName();
        this.size = file.length()/BYTES_TO_KB;
        this.writable = file.canWrite();
        this.executable = file.canExecute();
        this.hidden = file.isHidden();
        this.absolutePath = file.getAbsolutePath();
    }
}
