package DownloadPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileAlreadyExistsException;


public class DownloadFile {
    private String fileName;
    private String fileDirectory;
    private String fileURL;
    private String fullName;
    private StringBuilder fileStatus = new StringBuilder();
    private boolean isDownloaded;


    public void downloadFile(FileInfo fileInfo, String directory) {
        isDownloaded = false;
        fileName = fileInfo.getFileName();
        fileURL = fileInfo.getFileURL();
        fileDirectory = directory;
        fileStatus.delete(0,fileStatus.length());
        fileStatus.append("FILE URL: " + fileURL + "\nFILE DIRECTORY: " + fileDirectory + "\nFILE NAME: " + fileName + "\nSTATUS: ");

        URL website;
        try {

            website = new URL(fileURL); //get URL
            website.getContent(); //check if there is any content
           // System.out.println("There is content");

        } catch (MalformedURLException e) {
            fileStatus.append("Not downloaded " + "( Wrong URL : " + fileURL+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        } catch (UnknownHostException e) {
            fileStatus.append("Not downloaded " + "( Unknown host : " + fileURL+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        } catch (IOException e) {
            fileStatus.append("Not downloaded " + "( Can't find any content here : " + fileURL+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        }

        ReadableByteChannel rbc; //get a channel

        try {
            rbc = Channels.newChannel(website.openStream());
        } catch (IOException e) {
            fileStatus.append("Not downloaded " + "( Can't open a readable channel : " + fileURL+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        }

        {
            File dir = new File(fileDirectory);
            if (!dir.exists()) {
                fileStatus.append("Not downloaded " + "( No such directory : " + fileDirectory+" )");
                //System.out.println(fileStatus);
                return;
            }
            if (!dir.isDirectory()) {
                fileStatus.append("Not downloaded " + "( No such directory : " + fileDirectory+" )");
                //System.out.println(fileStatus);
                return;

            }
        }


        fullName = fileDirectory + "\\" + fileName;
        File file = new File(fullName);
        try {
            if (file.exists()) throw new FileAlreadyExistsException(fileName);
        } catch (FileAlreadyExistsException e) {
            fileStatus.append("Not downloaded " + "( File name already exists : " + fileName+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        }

        FileOutputStream fos;

        try {
            fos = new FileOutputStream(fullName);
        } catch (FileNotFoundException e) {
            fileStatus.append("Not downloaded " + "( Can't open the output stream for this file\nProbably it is not available to write in this directory) : " + fullName+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        }

        try {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        } catch (IOException e) {
            fileStatus.append("Not downloaded " + "( Download error"+" )");
            //System.out.println(fileStatus);
            //e.printStackTrace();
            return;
        }

        isDownloaded = true;
        fileStatus.append("Downloaded");

    }

    public boolean isFileDownloaded() {
        return isDownloaded;
    }

    public String getFileStatus() {
        return fileStatus.toString();
    }
}
