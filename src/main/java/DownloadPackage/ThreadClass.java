package DownloadPackage;

import java.util.logging.SocketHandler;

public class ThreadClass {
    public static int downloaded=0;
    public static StringBuilder finalStatus= new StringBuilder();
    public static class NewThread implements Runnable
    {
        String name;
        FileInfo info;
        public Thread t;
        String filedir;
        public NewThread(String name)
        {
            this.name=name;
            t=new Thread(this, name);
            System.out.println("New Thread: "+t);
        }

        public NewThread(String threadname, FileInfo fileInfo, String filedir)
        {
            name=threadname;
            info=fileInfo;
            this.filedir=filedir;
            t=new Thread(this,name);
            //System.out.println("New Thread: "+t);
            t.start();
        }

        public void run()
        {
            //System.out.println(name);
            DownloadFile downloadFile=new DownloadFile();
            downloadFile.downloadFile(info,filedir);
            System.out.println("\n***"+info.getFileName()+"***"+"\n"+downloadFile.getFileStatus());
        }
    }
}
