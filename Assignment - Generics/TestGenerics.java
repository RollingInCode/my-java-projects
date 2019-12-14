public class TestGenerics {
    public static void main(String[] args) {
        MyFirstObject myObj1 = new MyFirstObject();
        MySecondObject myObj2 = new MySecondObject();
        MyGenerics mg = new MyGenerics();
        Integer[] array = {2,4,6,8};

        System.out.println( mg.partOne(array, array.length, 6) );

        System.out.println( mg.partTwo(myObj1, myObj2) );
    }
}