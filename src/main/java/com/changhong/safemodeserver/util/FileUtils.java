package com.changhong.safemodeserver.util;

import com.changhong.safemodeserver.SafeModeServerApplication;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author konb
 */
public class FileUtils {

    private final static String TEMP_FILE_PATH = "D:/tmpFile/";

    public static File multipartFileToFile(MultipartFile file) {
        File newFile = new File(TEMP_FILE_PATH + System.currentTimeMillis());
        if (file == null || file.isEmpty()) {
            return null;
        }
        if (!newFile.exists()) {
            newFile.mkdirs();
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFile;
    }


}
