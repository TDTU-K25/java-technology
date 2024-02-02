import vn.edu.tdtu.ArrayHandler;
import vn.edu.tdtu.ArrayOutput;

public class Program {
    public static void main(String[] args) {
        int[] a = new int[] { 12, 2, 34, 5, 6 };
        int[] b = new int[] { 47, 25, 59 };
        ArrayOutput.print(a);
        ArrayOutput.print(b);
        int[] c = ArrayHandler.merge(a, b);
        ArrayHandler.sort(c);
        ArrayOutput.print(c);
        ArrayOutput.write(c, "output.txt");
    }
}
