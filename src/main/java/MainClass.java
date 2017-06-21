

import DownloadPackage.*;
import sun.rmi.runtime.NewThreadAction;

public class MainClass {
    public static void main(String[] args) {
        String CSVfilename;
        String filedir;
        int threadNum;
        if (args.length != 3) {
            System.out.println("Wrong number of arguments.\n !Notice: FileDirectory NumberOfThreads");
            System.exit(0);
        }
        CSVfilename = args[0];
        filedir = args[1];
        threadNum = Integer.parseInt(args[2]);
        if (threadNum <= 0) {
            System.out.println("Wrong thread num");
            System.exit(0);
        }
        System.out.println("CSV file name:" + CSVfilename);
        System.out.println("Directory:" + filedir);
        System.out.println("Number of threads:" + threadNum + "\n");

        ReadCSV csvChannel = new ReadCSV();
        FileList fileList = csvChannel.read(CSVfilename);
        csvChannel.getCSVstatus();
        System.out.println();
        if (csvChannel.isRead()) {

            //DownloadFile downloadFile = new DownloadFile();
            int size = fileList.size();
            ThreadClass.NewThread[] thread = new ThreadClass.NewThread[threadNum];
            for (int i = 0; i < threadNum; i++) {
                thread[i] = new ThreadClass.NewThread("Thread " + i);
            }
            int count = 0;
            while (count != size) {
                for (int i = 0; i < threadNum; i++) {
                    if (!(thread[i].t.isAlive()) && count != size) {
                        thread[i] = new ThreadClass.NewThread("Name " + i, fileList.get(count), filedir);
                        count++;
                    }
                }

            }

        }


    }
}
