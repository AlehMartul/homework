public class Example {
    public static void main(String[] args) {
        Student student = new Student("Alex", true, 17);
        student.Introdeuce();
        student.SayHi();
        student.Adult();

        Methods methods = new Methods();
        methods.Minimal(7,5);
        methods.EvenCheck(5);
        methods.SquMe(11);
        methods.CubMe(3);
    }
}
