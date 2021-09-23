package code;

import java.io.File;
import java.util.Date;

public class FileSystemReader {
    private final String _path;
    private final String _backPath;
    private final File[] _files;

    public FileSystemReader(String path)
    {
        _path = path;
        File currentFile = new File(_path);
        _backPath = currentFile.getParent();
        _files = currentFile.listFiles();
    }

    public String getBackPath(){
        if (_backPath == null) return "";
        return String.format("<a href=\"file?path=%s\">Вверх: %s</a>",_backPath,_backPath );
    }

    public String getPath(){
        return _path;
    }

    public String getDirectories()
    {
        if (_files == null) return "";

        StringBuilder sb = new StringBuilder();
        for (File file : _files)
        {
            if (file.isDirectory()){
                sb.append(
                    String.format(
                    "<p>Dir: <a href=\"file?path=%s\">%s</a><br>Date: %s</p>",
                    file.getAbsolutePath(),
                    file.getName(),
                    new Date(file.lastModified())
                ));
            }
        }
        return sb.toString();
    }

    public String getFiles()
    {
        if (_files == null) return "";

        StringBuilder sb = new StringBuilder();
        for (File file : _files)
        {
            if (file.isFile()){
                sb.append(
                    String.format(
                    "<p>File: <a download href=\"%s\">%s</a><br>Size: %d B<br>Date: %s</p>",
                    file.getName(),
                    file.getName(),
                    file.length(),
                    new Date(file.lastModified())
                ));
            }
        }
        return sb.toString();
    }
}
