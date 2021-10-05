package Servlets;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemReader {
    private final String _path;
    private final String _parentPath;
    private final File[] _files;

    public FileSystemReader(String path)
    {
        _path = path;
        File currentFile = new File(_path);
        _parentPath = currentFile.getParent();
        _files = currentFile.listFiles();
    }

    public String getParentDirectory(){
        return _parentPath;
    }

    public String getPath(){
        return _path;
    }

    public List<File> getDirectories()
    {
        List<File> dirs = new ArrayList<>();
        if (_files == null) return dirs; // Пустой лист
        for (File file : _files)
        {
            if (file.isDirectory()){
                dirs.add(file);
            }
        }
        return dirs;
    }

    public List<File> getFiles()
    {
        List<File> files = new ArrayList<>();
        if (_files == null) return files;
        for (File file : _files)
        {
            if (file.isFile()){
                files.add(file);
            }
        }
        return files;
    }
}
