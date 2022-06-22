// TODO: 스택을 구현하라.


// push: X를 스택 자료구조에 넣는 연산이다.
// pop: 스택에서 가장 위에 있는 노드를 빼고, 그 노드를 출력한다.
// size: 스택의 크기를 출력한다.
// empty: 스택이 비어있으면 1, 아니면 0을 반환한다.
// top: 스택의 가장 위에있는 노드를 출력한다. 만약, 값이 비어있을 경우 -1을 출력한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution extends Stack {

    public void stack() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder builder = new StringBuilder();

        int numbers = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < numbers; i++) {
            StringTokenizer tokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String token = tokenizer.nextToken();

            if (token.contains("push")) {
                push(Integer.parseInt(tokenizer.nextToken()));
            }

            if (token.contains("pop")) {
                builder.append(pop()).append("\n");
            }

            if (token.contains("size")) {
                builder.append(size()).append("\n");
            }

            if (token.contains("empty")) {
                builder.append(empty()).append("\n");
            }

            if (token.contains("top")) {
                builder.append(top()).append("\n");
            }
        }

        System.out.println(builder);
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        solution.stack();
    }

}
