package SystemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class APIRateLimiter {
    static Map<String, Queue<Long>> holder = new HashMap<>();
    // set the time limit
    static int TIME_LIMIT = 1000;
    // set the number of hits for this time window
    static int MAX_HITS = 4;

    public static void hitAPI(String clientId, long timeOfHit) {
        boolean result = isAllowed(clientId, timeOfHit);
        if(result)
            System.out.println("hit @ " + timeOfHit + "ms");
        else
            System.out.println("blocked @ " + timeOfHit + "ms");
    }

    public static boolean isAllowed(String clientId, long timeOfHit) {
        boolean result = false;
        if(holder.containsKey(clientId)) {
            //client has hit before, keep polling the queue till you reach a time whose diff with timeOfHit is less than 1000
            Queue<Long> timings = holder.get(clientId);
            while(!timings.isEmpty() && timeOfHit - timings.peek() >= TIME_LIMIT) {
                timings.poll();
            }
            //the remaining queue is the number of hits within the 1000 ms
            if(timings.size() < MAX_HITS) {
                //add the timeOfHit
                timings.add(timeOfHit);
                //update the holder
                holder.put(clientId, timings);
                result = true;
            }
        } else {
            //client hitting first time, simply put it in holder
            Queue<Long> timings = new LinkedList<Long>();
            timings.add(timeOfHit);
            holder.put(clientId, timings);
            result = true;
        }
        return result;
    }

    /*----------------Driver Code------------------------*/
    public static void main(String[] args) {
        //limit is set to 4 requests/sec (4 requests/1000ms)
        List<Input> inputs = new ArrayList<Input>();
        //clientId: A
        inputs.add(new Input("A", 1000));
        inputs.add(new Input("A", 1002));
        inputs.add(new Input("A", 1020));
        inputs.add(new Input("A", 1030));
        inputs.add(new Input("A", 1050));
        inputs.add(new Input("A", 1051));
        inputs.add(new Input("A", 1099));
        inputs.add(new Input("A", 1100));
        inputs.add(new Input("A", 1199));
        inputs.add(new Input("A", 2001));
        inputs.add(new Input("A", 2010));
        inputs.add(new Input("A", 2050));
        inputs.add(new Input("A", 3000));

        for(Input input : inputs) {
            hitAPI(input.clientId, input.timeOfHit);
        }
    }
}

class Input {
    public String clientId;
    public long timeOfHit;

    public Input(String clientId, long timeOfHit) {
        this.clientId = clientId;
        this.timeOfHit = timeOfHit;
    }
}






