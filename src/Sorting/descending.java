package Sorting;

import Model.IMarkdown;

import java.util.Comparator;

public class descending implements Comparator<IMarkdown> {


    @Override
    public int compare(IMarkdown o1, IMarkdown o2) {
        return o2.get_name().compareTo(o1.get_name());
    }
}
