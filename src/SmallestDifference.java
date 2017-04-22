import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SmallestDifference {

    public static void main(String[] args){
        smallestDifference(null,null);
    }

    static int smallestDifference(int[] a1, int[] a2){

        Integer[] a = {1,2,3,4,5,6,7};
        List<Integer> l = Arrays.asList(a);

        Collections.rotate(l,0);
        System.out.println(Collections.binarySearch(l,1));

        System.out.println(l);
        return 0;
    }

}
