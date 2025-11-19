package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
     * @param nextString next string to print
     */
    void setNextStringToPrint(String nextString);

    /**
     * @return nextString to print
     */
    String getNextStringToPrint();

    /**
     * @return history of printed strings as a list
     */
    List<String> getPrintedStringsHistory();

    /**
     *
     */
    void printCurrentString();
}
