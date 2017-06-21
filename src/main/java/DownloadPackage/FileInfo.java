package DownloadPackage;

public class FileInfo {
    private String fileName;
    private String fileURL;

    public FileInfo()
    {
        fileName="";
        fileURL="";
    }
    public FileInfo(String fileURL, String fileName)
    {
        this.fileName=fileName;
        this.fileURL=fileURL;
    }

    public String getFileName()
    {
        return fileName;
    }

    public String getFileURL() {
        return fileURL;
    }


}
