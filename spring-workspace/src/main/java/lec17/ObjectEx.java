package lec17;

public class ObjectEx {
    private Object object;

    public ObjectEx() {
        System.out.println("--default constructor");
    }

    @Override
    protected void finalize() throws  Throwable {
        System.out.println("--finalize() method--");
        super.finalize();
    }
}
