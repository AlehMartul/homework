public class Methods {
    public int Minimal(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public boolean EvenCheck(int a) {
        if (a % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int SquMe(int a) {
        return a * a;
    }

    public int CubMe(int a) {
        return a * a * a;
    }

}
