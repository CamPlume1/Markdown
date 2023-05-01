import Model.IMarkdown;
import Model.MdFolder;
import Sorting.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    //NOTE: This is a rough draft
    // Starting path MUST be a directory, any non-md files may cause a crash, and
    // string parsing is slightly limited. That being said, I believe it works with a very intuitive design


    //MAJOR NOTE: Design flaw: sorting only works on the level of a single folder. In order to implement
    //sorting correctly, I would first return a simple list of MdFiles, sort that, and then serialize.
    //This requires an adapter from Folder to file objects to be implemented, but this would not be difficult. Ran out
    // of free time!!
    public static void main(String[] args) throws IOException {
        IMarkdown root = new MdFolder(new File(args[0]));
        String sorting = args[1];
        Order ord = null;
        switch(sorting){
            case "Ascending":
                ord = Order.ASCENDING;
                break;
            case "Descending":
                ord = Order.DESCENDING;
                break;
            case "Time":
                ord = Order.TIME;
                break;
            default:
                System.out.print("Error: Invalid Sort type. Please see README");
                System.exit(-1);
        }
        String output = root.generate_summary(ord);

        //This should be the content root?
        FileWriter writer = new FileWriter("output.md");
        writer.write(output);
        writer.close();

        System.exit(0);
    }
}
