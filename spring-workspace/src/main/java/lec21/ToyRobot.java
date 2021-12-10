package lec21;

public class ToyRobot implements Toy{
    @Override
    public void walk() {
        System.out.println("The ToyRobot can walk");
    }

    @Override
    public void run() {
        System.out.println("The ToyRobot can run");
    }

    @Override
    public void alarm() {
        System.out.println("The ToyRobot has no alarm function");
    }

    @Override
    public void light() {
        System.out.println("The ToyRobot has light function");
    }
}
