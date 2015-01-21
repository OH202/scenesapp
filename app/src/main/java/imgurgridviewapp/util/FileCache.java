package imgurgridviewapp.util;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Owner on 11/2/2014.
 */
public class FileCache {

    private File cacheDir;

    public FileCache(Context context) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            cacheDir = new File (Environment.getExternalStorageDirectory(), "JsonParseTutorialCache");

        } else {

            cacheDir = context.getCacheDir();

        }

        if (!cacheDir.exists()){cacheDir.mkdirs();}

    }

    public File getFile(String url){

        String filename = String.valueOf(url.hashCode());

        File f = new File (cacheDir, filename);
        return f;

    }

    public void clear() {

        File[] files = cacheDir.listFiles();

        if (files == null){return;}

        for (File f : files){

            f.delete();

        }

    }

}
