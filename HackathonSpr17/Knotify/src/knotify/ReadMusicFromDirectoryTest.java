package knotify;

import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jfeld
 */
public class ReadMusicFromDirectoryTest {

    public static void main(String[] args) {
        
        File testDirectory = new File("Load");
        File[] filesInDir = testDirectory.listFiles();
                    for (int i = 0; i < filesInDir.length; i++){
                        if((filesInDir[i].isFile()) && (filesInDir[i].getName().endsWith(".mp3"))){
                            String mp3FileName = filesInDir[i].getName();
                            String truncatedMp3FileName = filesInDir[i].getName().substring(3, mp3FileName.length() - 4);
                            System.out.println("File: " + truncatedMp3FileName);
                            }  
                    }
            }
        }
