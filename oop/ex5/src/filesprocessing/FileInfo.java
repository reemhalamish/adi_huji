package filesprocessing;
import java.io.File;

/**
 * Created by adi on 25/05/17.
 */

/**
 * this class saves all the relevant info of a certain file.
 */
public class FileInfo {

    private double BYTES_TO_KB = 1/1024;

    /* fields */
    public double size;
    public String name;
    public boolean writable;
    public boolean executable;
    public boolean hidden;


    public FileInfo(File file) {
        this.name = file.getName();
        this.size = file.length()*BYTES_TO_KB;
        this.writable = file.canWrite();
        this.executable = file.canExecute();
        this.hidden = file.isHidden();

    }
}
