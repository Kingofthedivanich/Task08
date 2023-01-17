import java.util.ArrayList;

public class Solution {

    public static boolean neUbivaet(int[] inputArg){
        boolean j = true;
        for (int i = 1; i < inputArg.length; i++){
            if (inputArg[i] < inputArg[i-1]){
                j = false;
            }
        }
        return j;
    }

    public static int[][] solution(int [][] userArr){
        int schet = 0;
        int[][] newArr = new int[userArr.length][userArr[0].length];

        for(int r = 0; r < userArr.length; r++){
            if (neUbivaet(userArr[r])){
                newArr[schet] = userArr[r];
                schet++;
            }
        }

        for(int r = 0; r < userArr.length; r++){
            if (!neUbivaet(userArr[r])){
                newArr[schet] = userArr[r];
                schet++;
            }
        }
        return newArr;
    }

}
