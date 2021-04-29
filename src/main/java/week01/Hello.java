package week01;

public class Hello {
    public static void main(String[] args) {
        int tmp = 10;
        tmp += 1;
        tmp -= 2;
        tmp /= 3;
        tmp *= 4;
        for (int i = 0; i < 5; i++) {
            tmp++;
        }
        if (tmp / 2 == 0)
            System.out.println("even");
        else
            System.out.println("odd");
    }
}
