package Model;

import Sorting.Order;

import java.nio.file.attribute.FileTime;

public interface IMarkdown {


    /**
     * Extracts all "important" pieces of markdown files contained within file, sorts them by designated parameter, and
     * writes to a markdown file
     */
    String generate_summary(Order sortType);

    //Returns Last edited Time

    FileTime get_time();

    String get_name();


}
