package Logic;

import Application.Constants;
import Application.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.*;
import java.lang.*;
import java.security.MessageDigest;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.InputStream;

public class DuplicateFile_Cleaner {

    public File filePath = null;
    public LinkedList<String> lobj = new LinkedList<String>();

    public DuplicateFile_Cleaner(String path) throws Exception
    {
        filePath = new File(path);
        cleanDuplicateFile();
    }

    public void cleanDuplicateFile() throws Exception 
    {
        File files[] = filePath.listFiles();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte buffer[] = new byte[1024];
        int ret = 0;

        try 
        {
            
            if(md == null)
            {
                JOptionPane.showMessageDialog(new JFrame(), "Unable to get MD5", "Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            for(File file : files)
            {
                FileInputStream input = new FileInputStream(file);
            
                if(file.length() != 0)
                {
                    while ((ret = input.read(buffer)) != -1)
                    {
                        md.update(buffer, 0, ret);
                    }
                }
            
                StringBuilder result = new StringBuilder();
                byte byteArray[] = md.digest();
                for(int i = 0; i < byteArray.length; i++) 
                {
                    result.append(String.format("%02x",byteArray[i]));
                }

                input.close();

                if(lobj.contains(result.toString()))
                {
                    if(!file.delete())
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Unable to delete the file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else
                {
                    lobj.add(result.toString());
                }
            }
        } 
        catch (Exception e) 
        {}
    }
    
}
