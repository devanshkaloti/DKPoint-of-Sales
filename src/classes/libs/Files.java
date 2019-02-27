package classes.libs;

import javafx.stage.DirectoryChooser;
import classes.DKHelpers;
import javafx.stage.FileChooser;

import javax.activation.MimetypesFileTypeMap;
import java.io.*;
// Author: Devansh

public class Files extends DKHelpers {

    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static String fileChooser() {
        final FileChooser fileChooser = new FileChooser();
        File fileLocation = fileChooser.showOpenDialog(null);

        if (fileLocation != null) {
            print("Path: %s", fileLocation.getAbsolutePath());
            return fileLocation.getAbsolutePath();
        } else {
            throw new NullPointerException();
        }

    }

}
