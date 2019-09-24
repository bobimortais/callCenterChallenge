import java.util.*;

/**
 *Challenge to check need number of attendants to support a queue of calls
 * on a call center. Every call is represented by a start and end time
 */
public class CallCenter
{

    public static void main(String[] args)
    {
        List<String> callsList = new ArrayList<>();
        callsList.add("1-10");
        callsList.add("2-10");
        callsList.add("2-10");
        callsList.add("3-10");
        callsList.add("4-10");
        callsList.add("5-10");
        callsList.add("6-10");
        callsList.add("7-11");
        callsList.add("9-15");
        callsList.add("10-15");
        System.out.println("Number of Attendants: " + numberOfAttendants(callsList) + "\n");

        List<String> callsList1 = new ArrayList<>();
        callsList1.add("1-3");
        callsList1.add("2-3");
        callsList1.add("2-5");
        callsList1.add("3-7");
        callsList1.add("4-6");
        callsList1.add("5-6");
        callsList1.add("6-20");
        callsList1.add("7-9");
        callsList1.add("7-12");
        callsList1.add("9-15");
        System.out.println("Number of Attendants: " + numberOfAttendants(callsList1) + "\n");

        List<String> callsList2 = new ArrayList<>();
        callsList2.add("1-3");
        callsList2.add("2-3");
        System.out.println("Number of Attendants: " + numberOfAttendants(callsList2) + "\n");

        List<String> callsList3 = new ArrayList<>();
        callsList3.add("1-3");
        callsList3.add("4-5");
        System.out.println("Number of Attendants: " + numberOfAttendants(callsList3) + "\n");

        List<String> callsList4 = new ArrayList<>();
        System.out.println("Number of Attendants: " + numberOfAttendants(callsList4) + "\n");

        System.out.println("Number of Attendants: " + numberOfAttendants(null) + "\n");
    }

    /**
     * Method to check the needed number of attendants
     * @param callsList - list of calls
     * @return - number of needed attendants
     */
    public static int numberOfAttendants(List<String> callsList)
    {
        if(callsList == null || callsList.size() == 0)
            return 0;

        int numberOfAttendants = 0;
        int maxCallStartTime = 0;
        int time = 1;
        int freeAttendants = 0;

        //Collect max call start time
        for(String call : callsList)
        {
            Integer callStart = Integer.parseInt(call.split("-")[0]);
            if(callStart > maxCallStartTime)
                maxCallStartTime = callStart;
        }

        //Run over the list of calls until time reaches the last call start time
        while(time <= maxCallStartTime)
        {
            for(Iterator<String> it = callsList.iterator(); it.hasNext();)
            {
                String call = it.next();
                Integer callStart = Integer.parseInt(call.split("-")[0]);
                Integer callEnd = Integer.parseInt(call.split("-")[1]);

                if(callStart == time)
                {
                    if(freeAttendants > 0)
                    {
                        freeAttendants--;
                    }
                    else
                    {
                        numberOfAttendants++;
                    }
                }
                else if(callEnd <= time)
                {
                    freeAttendants++;
                    it.remove();
                }
                else if(callStart > time)
                {
                    break;
                }
            }
            time++;
        }
        return numberOfAttendants;
    }
}
