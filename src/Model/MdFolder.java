package Model;

import Sorting.Ascending;
import Sorting.Order;
import Sorting.TimerCheck;
import Sorting.descending;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class MdFolder implements IMarkdown {

    List<IMarkdown> contents = new ArrayList<>();
    String name;

    //Some form of Date Representation: Using int stub.
    FileTime lastEdited;

    public MdFolder(File path){
        //Grab name
        name = path.getName();

        //Grab Time
        try{
            this.lastEdited = Files.getLastModifiedTime(path.toPath());}
        catch (IOException e){
            throw new IllegalStateException("Bad Path Found");
        }

        //For every item in the folder, create corresponding object and add to contents list
        for (File p : Objects.requireNonNull(path.listFiles())) {
            if(p.isDirectory()){
                contents.add(new MdFolder(p));
            }
            else {
                contents.add(new MdFile(p));
            }
        }

    }

    @Override
    public String generate_summary(Order sortType) {
        String acc = "Folder Name:" + this.name + "\n";
        //Select comparator function
        Comparator<IMarkdown> comp;
        if (sortType == Order.ASCENDING){
            comp = new Ascending();
        }
        else if (sortType == Order.DESCENDING){
            comp = new descending();
        }
        else if (sortType == Order.TIME){
        comp = new TimerCheck();}
        else {
            throw new IllegalArgumentException("Bad Sorting.Order");
        }

        //sort List
        contents.sort(comp);

        //Generate Summary File:
        for (IMarkdown cur: this.contents) {
            acc += cur.generate_summary(sortType);
        }
        return acc + "\n";
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
