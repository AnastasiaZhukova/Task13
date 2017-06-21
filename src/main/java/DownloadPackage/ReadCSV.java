package DownloadPackage;

import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCSV {
    private boolean isRead;
    private String CSVname;
    private StringBuilder CSVstatus = new StringBuilder();

    public FileList read(String CSVfilename) {
        isRead = false;
        this.CSVname = CSVfilename;
        CSVstatus.append("CSV file name: " + CSVname);
        CSVReader csvReader = null;
        FileList fileList = new FileList();
        try {
            csvReader = new CSVReader(new FileReader(CSVname), ',', '"', 0);

        } catch (FileNotFoundException e) {
            CSVstatus.append("STATUS:\n Couldn't find the file\n");
            System.out.println(CSVstatus);
            //e.printStackTrace();
            return null;
        }
        String[] infoArray = null;
        FileInfo tempInfo;
        try {
            while ((infoArray = csvReader.readNext()) != null) {
                try {
                    if(infoArray.length!=2) throw new ArrayIndexOutOfBoundsException();
                    tempInfo = new FileInfo(infoArray[0], infoArray[1]);
                    fileList.append(tempInfo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    CSVstatus.append("STATUS:\n CSV file is damaged. Some data may be lost:\nLINE:\n ");
                    for (int i=0; i<infoArray.length; i++)
                    {
                        CSVstatus.append(infoArray[i]);
                    }
                    CSVstatus.append("\n");

                }catch (NullPointerException e) {
                    CSVstatus.append("STATUS:\n CSV file is damaged\n");
                    return null;
                }

            }
        } catch (IOException e) {
            CSVstatus.append("STATUS:\n Error while reading CSV file\n");
            //e.printStackTrace();
        }
        isRead = true;
        CSVstatus.append("STATUS:\n CSV file is read\n");
        return fileList;
    }

    public boolean isRead() {
        return isRead;
    }

    public void getCSVstatus()
    {
        System.out.println(CSVstatus);
    }
}
