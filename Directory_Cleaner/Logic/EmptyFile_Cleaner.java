package Logic;

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

public class EmptyFile_Cleaner {

    public File filePath = null;
    public LinkedList<String> lobj = new LinkedList<String>();
    
    public EmptyFile_Cleaner(String path) throws Exception
    {
        filePath = new File(path);
        cleanEmptyFile();
    }

    public void cleanEmptyFile()
    {   
        File files[] = filePath.listFiles();
        for(File file : files)
        {
            if(file.length() == 0)
            {
                if(!file.delete()){   
                    JOptionPane.showMessageDialog(new JFrame(), "Unable to delete empty files","Error",JOptionPane.ERROR_MESSAGE);                }
            }
        }
    }
}
