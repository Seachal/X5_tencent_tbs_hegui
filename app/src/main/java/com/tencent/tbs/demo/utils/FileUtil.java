package com.tencent.tbs.demo.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    public static void writeBytesToFile(InputStream is, File file) throws IOException {
        if (file.exists()) {
            return;
        }
        FileOutputStream fos = null;
        try {
            byte[] data = new byte[2048];
            int nbread = 0;
            fos = new FileOutputStream(file);
            while ((nbread = is.read(data)) > -1) {
                fos.write(data,0, nbread);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            if (fos != null) {
                fos.close();
            }
        }
    }

    public static String getFileType(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return null;
    }
}
