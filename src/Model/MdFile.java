package Model;

import Model.IMarkdown;
import Sorting.Order;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

/**
 * Represents all the info that is contained in a single markdown file being stored on disk.
 * Requires a file path to instantiate.
 */
public class MdFile implements IMarkdown {

    FileTime lastEdited; //Data Type Stub
    String name;
    String contents;
    public MdFile(File path){
        //TODO: File Parsing

        //Grab Contents
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream(path));
        }
        catch (FileNotFoundException e){
            throw new IllegalStateException("Bad File Path");
        }
        while (sc.hasNext()){
            contents += sc.nextLine() + "\n";
        }

        //Grab name
        this.name = path.getName();

        //Grab Time
        try{
        this.lastEdited = Files.getLastModifiedTime(path.toPath());}
        catch (IOException e){
            throw new IllegalStateException("Bad Path Found");
        }

    }

    //Returns a string representing the summary for this single file
    @Override
    public String generate_summary(Order sortType){
        StringBuilder acc = new StringBuilder("File Name: " + this.name + "\n");
        String sum = contents;
        //TODO: Test this index logic
        while(sum.contains("[[")){
            int start = sum.indexOf("[[");
            int end = sum.indexOf("]]");
            acc.append(sum.substring(start + 2, end));
            acc.append("\n");
            sum = sum.substring(end + 1);
        }
        return acc.toString();
    }

    @Override
    public FileTime get_time() {
        return this.lastEdited;
    }

    @Override
    public String get_name() {
        return this.name;
    }
}
