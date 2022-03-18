public class Memory {
    private String leftPosition;
    private String rightPosition;
    
    public Memory(String leftPosition, String rightPosition) {
        this.leftPosition = leftPosition;
        this.rightPosition = rightPosition;
    }

    public void saveLeftFinger(String position) {
        this.leftPosition = position;    
    }

    public void saveRightFinger(String position) {
        this.rightPosition = position;
    }

    public String getLeftPosition() {
        return this.leftPosition;
    }

    public String getRightPosition() {
        return this.rightPosition;
    }
}
