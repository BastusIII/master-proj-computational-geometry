package edu.fh.cs.compgeometry.stramm.linesweep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Basti on 26.05.2015.
 */
public abstract class AbstractEventList extends SimpleErrorList  implements EventList {

    private StringBuilder errors = new StringBuilder();

    @Override
    public int compare(Event o1, Event o2) {
        return Double.compare(o1.getXVal(), o2.getXVal());
    }

    public void addError(String error) {
        this.errors.append(error).append(System.lineSeparator());
    }

    public String getErrors() {
        return errors.toString();
    }
}
