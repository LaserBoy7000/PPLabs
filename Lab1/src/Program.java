package Lab1.src;

import java.util.Scanner;

public class Program{
    public static void main(String[] args){
        System.out.println("Enter the number:");
        var sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        var ls = new LukesNumberProvider().GenerateN(n);
        var res = new LukesNumberAnalyzer().GetOfPow3p1(ls);

        System.out.println("\nAppear as x^3+1 can the following numbers: ");
        for (LukesNumber ln : res) {
            System.out.printf("\t№%d:  %d\n", ln.GetNumber(), ln.GetValue());
        }

    }
}