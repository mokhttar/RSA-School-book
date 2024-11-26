import java.lang.Math;
import java.math.BigInteger;

class RSA {


    public static Double gcd(Double a, Double b) {
        while (b != 0) {
            Double temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static Integer modInverse(Double e, Double fi_N) {
        e = e % fi_N;
        for (Integer x = 1; x < fi_N; x++) {
            if ((e * x) % fi_N == 1) {
                return x;
            }
        }
        return -1;
    }

    public Double Encryption(Double P, Double Q, Double E, Double message) {
        // public Key array([E,N])
        Double[] Key = new Double[2];
        // Calculate the N
        Double N = P * Q;
        // Calculate Q(N)
        Double FI_N = (P - 1) * (Q - 1);
        // Verify if E is 1<E<FI_N
        if (E > 1 && E < FI_N) {
            Key[0] = E;
            Key[1] = N;
        } else {
            System.out.println("Your E is invalide");
        }
        Double validMessage = Math.floor(message);
        Double CryptedMessage;
        CryptedMessage = (Math.pow(validMessage, Key[0])) % Key[1];

        System.out.println("E is :" + Key[0]);
        System.out.println("N is :" + N);
        System.out.println("Fi_N is:" + FI_N);
        System.out.println("Crypted message is" + CryptedMessage);

        return CryptedMessage;

    }

    public void SquareAndMultiply(Double CypherText, Double Fi_N, Double N, Double E) {
        // find D
        Integer D = modInverse(E, Fi_N);
        // transform D into binary
        String binary_D = Integer.toBinaryString(D);
        Integer result = 0;
        char Splited_D[] = binary_D.toCharArray();
        Integer prevValue = 0;
        for (int i = 0; i < Splited_D.length; i++) {
            int current_int_value = 0;
            if (i == 0) {

                if (Splited_D[i] == '1') {
                    current_int_value = 1;
                    result = (int) ((int) ((int) Math.pow(current_int_value, 2) * CypherText) % N); // 1:==>1
                    prevValue = result;
                }
                if (Splited_D[i] == '0') {
                    current_int_value = 0;
                    result = (int) ((int) Math.pow(current_int_value, 2));
                    prevValue = result;
                }

            } else {
                if (Splited_D[i] == '1') {
                    result = (int) ((int) ((int) Math.pow(prevValue, 2) * CypherText) % N);
                    prevValue = result;
                }
                if (Splited_D[i] == '0') {

                    result = (int) ((int) ((int) Math.pow(prevValue, 2)) % N);
                    prevValue = result;
                }
            }
        }
        System.out.println(result);

    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        Double CryptedMessage = rsa.Encryption(5.0, 11.0, 7.0, 2.0);
        System.out.println("The Crypted Message of 2.0 is :" + CryptedMessage);
        System.out.println("The plain text is:");
        rsa.SquareAndMultiply(CryptedMessage, 40.0, 55.0, 7.0);
    }
}
