public class Stack {
    public int[] stack = new int[100];
    public int size = 0;

    public void push(int number) {
        stack[size] = number;
        size++;
    }

    public int pop() {
        if (size == 0) {
            return -1;
        }
        return stack[size--];
    }

    public int size() {
        return size;
    }

    public int empty() {
        if (size == 0) {
            return 1;
        }
        else {
            return 0;
        }
    }

    public int top() {
        if (size == 0) {
            return -1;
        }
        return stack[size--];
    }
}
