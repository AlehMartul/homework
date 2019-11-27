public class Student extends Person {
    private String name;
    private boolean male;
    private int age;

    public void Introdeuce() {
        System.out.println("Hi, my name is " + name + " , I'm " + age);
    }

    void Adult() {
        if (age < 18) {
            System.out.println("I'm sorry, i can't sell you cigarettes");
        } else {
            System.out.println("Please, smoke everywhere you want");
        }
    }

    protected void SayHi() {
        if (male == true) {
            System.out.println("Hi, mr " + name);
        } else {
            System.out.println("Hi, mrs " + name);
        }
    }

    private int CheckAge() {
        return age;
    }

    public Student(String name, boolean male, int age) {
        this.name = name;
        this.male = male;
        this.age = age;
    }
}