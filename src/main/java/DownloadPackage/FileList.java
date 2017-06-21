package DownloadPackage;

import java.util.Vector;

public class FileList {
    private static Vector<FileInfo> fileList=new Vector<>();

    public void append(FileInfo fileInfo)
    {
        fileList.add(fileInfo);
    }

    public int size()
    {
        return fileList.size();
    }

    public FileInfo get(int i)
    {
        return fileList.elementAt(i);
    }

}
