import java.util.Scanner;
public class EncryptorRunner {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Write numRows:");
        int numRows = s.nextInt();
        System.out.print("Write numCols:");
        int numCols = s.nextInt();
        Encryptor e = new Encryptor(numRows,numCols);
        System.out.print("Write a message to decrypt:");
        Scanner s2 = new Scanner(System.in);
        String str = s2.nextLine();
        String str2 = e.decryptMessage(str);
        System.out.println(str2);


    }
}
