package SystemDesign;

import java.util.List;

public class TestCode {

    /*------------------Driver Code ------------*/
    public static void main(String[] args) {
        //The system have already tracked down the following sentences and their corresponding times
        String[] sentences = {"i love you", "island","ironman", "i love India"};
        int[] times = {5,3,2,2};
        AutocompleteSystem obj = new AutocompleteSystem(sentences, times);

        //Operation: input('i')
        //Output: ["i love you", "island","i love India"]
        List<String> list1 = obj.input('i');
        System.out.println("List1 : " + list1);

        //Operation: input(' ')
        //Output: ["i love you","i love India"]
        List<String> list2 = obj.input(' ');
        System.out.println("List2 : " + list2);

        //Operation: input('a')
        //Output: []
        List<String> list3 = obj.input('a');
        System.out.println("List3 : " + list3);

        //Operation: input('#')
        //Output: []
        List<String> list4 = obj.input('#');
        System.out.println("List4 : " + list4);


        /* Output :
            List1 : [i love you, island, i love India]
            List2 : [i love you, i love India]
            List3 : []
            List4 : []
         */
    }
}
