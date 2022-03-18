import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    
    private String printFingerList(int[]numbers,String hand) {
        Memory firstMemory = new Memory("*","#");
        
        return IntStream.of(numbers)
                .mapToObj((i)->whichFinger(hand,i,firstMemory))
                .collect(Collectors.joining());
    }

    private String whichFinger(String hand,int number, Memory memory){
        // number 0~9
        int[]leftButtons={1,4,7};
        int[]rightButtons={3,6,9};

        // firstNumber in {1, 4, 7}
        if(includes(leftButtons,number)){
            memory.saveLeftFinger(String.valueOf(number));
            return"L";
        }

        // firstNumber in {3, 6, 9}
        if(includes(rightButtons,number)){
            memory.saveRightFinger(String.valueOf(number));
            return"R";
        }

        // firstNumber in {2, 5, 8, 0}
        int leftDistance=this.calculateDistance(memory.getLeftPosition(),number);
        int rightDistance=this.calculateDistance(memory.getRightPosition(),number);

        if(leftDistance<rightDistance){
            memory.saveLeftFinger(String.valueOf(number));

            return"L";
        }

        if(leftDistance>rightDistance){
            memory.saveRightFinger(String.valueOf(number));

            return"R";
        }

        if(hand.equals("left")){
            memory.saveLeftFinger(String.valueOf(number));
        }

        if(hand.equals("right")){
            memory.saveRightFinger(String.valueOf(number));
        }

        return hand.substring(0,1).toUpperCase();
    }

    private int calculateDistance(String currentLeft,int number){
        HashMap<String, int[]> matrix=new HashMap<>();

        matrix.put("1",new int[]{0,3});
        matrix.put("2",new int[]{1,3});
        matrix.put("3",new int[]{2,3});
        matrix.put("4",new int[]{0,2});
        matrix.put("5",new int[]{1,2});
        matrix.put("6",new int[]{2,2});
        matrix.put("7",new int[]{0,1});
        matrix.put("8",new int[]{1,1});
        matrix.put("9",new int[]{2,1});
        matrix.put("*",new int[]{0,0});
        matrix.put("0",new int[]{1,0});
        matrix.put("#",new int[]{2,0});

        return Math.abs(matrix.get(currentLeft)[1]-matrix.get(String.valueOf(number))[1])+
                Math.abs(matrix.get(currentLeft)[0]-matrix.get(String.valueOf(number))[0]);
    }

    private boolean includes(int[]leftButtons,int firstNumber){
        return Arrays.stream(leftButtons).anyMatch(i-> i == firstNumber);
    }
    
}

/**
 * 참고 자료 by Jihwahn 님
 * https://www.youtube.com/channel/UCTywUrqehYVG3vNZN8cqYcw
 */
