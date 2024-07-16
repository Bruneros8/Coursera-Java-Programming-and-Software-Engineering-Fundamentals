/**
 * Assignment 3: Last Word in Title Comparator
 * 
 * @author Deny Kiantono
 * @version 1.0
 */

import java.util.*;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int spaceIndex1 = q1.getInfo().lastIndexOf(" ");
        String lastWord1 = q1.getInfo().substring(spaceIndex1 + 1, q1.getInfo().length()); 
        
        int spaceIndex2 = q2.getInfo().lastIndexOf(" ");
        String lastWord2 = q2.getInfo().substring(spaceIndex2 + 1, q2.getInfo().length());
        
        if (lastWord1.compareTo(lastWord2) < 0) {
            return -1;
        }
        else if (lastWord1.compareTo(lastWord2) > 0) {
            return 1;
        }
        else {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        }
    }
}