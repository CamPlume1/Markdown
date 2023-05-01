package Sorting;

import Model.IMarkdown;

import java.util.Comparator;

public class TimerCheck implements Comparator<IMarkdown> {

    @Override
    public int compare(IMarkdown o1, IMarkdown o2) {
        return o1.get_time().compareTo(o2.get_time());
    }
}
