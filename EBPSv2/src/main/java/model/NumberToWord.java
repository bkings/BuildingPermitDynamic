package model;

class ConstNumtoLetter {

String[] unitdo = {"", " One", " Two", " Three", " Four", " Five", " Six", " Seven", " Eight", " Nine", " Ten", " Eleven", " Twelve", " Thirteen", " Fourteen", " Fifteen", " Sixteen", " Seventeen", " Eighteen", " Nineteen"};
String[] tens = {"", "Ten", " Twenty", " Thirty", " Forty", " Fifty", " Sixty", " Seventy", " Eighty", " Ninety"};
String[] digit = {"", " Hundred", " Thousand", " Lakh", " Crore"};
int r;

//Count the number of digits in the input number
int numberCount(int num) {
    int cnt = 0;

    while (num > 0) {
        r = num % 10;
        cnt++;
        num = num / 10;
    }

    return cnt;
}

//Function for Conversion of two digit
String twonum(int numq) {
    int numr, nq;
    String ltr = "";

    nq = numq / 10;
    numr = numq % 10;

    if (numq > 19) {
        ltr = ltr + tens[nq] + unitdo[numr];
    } else {
        ltr = ltr + unitdo[numq];
    }

    return ltr;
}

//Function for Conversion of three digit
String threenum(int numq) {
    int numr, nq;
    String ltr = "";

    nq = numq / 100;
    numr = numq % 100;

    if (numr == 0) {
        ltr = ltr + unitdo[nq] + digit[1];
    } else {
        ltr = ltr + unitdo[nq] + digit[1] + " and" + twonum(numr);
    }
    return ltr;
}
}

public class NumberToWord {

public static String convert(int num) {

    //Defining variables q is quotient, r is remainder
    int len, q = 0, r = 0;
    String ltr = " ";
    String Str = " ";
    ConstNumtoLetter n = new ConstNumtoLetter();

    if (num <= 0) {
        System.out.println("Zero or Negative number not for conversion");
    }

    while (num > 0) {

        len = n.numberCount(num);

        //Take the length of the number and do letter conversion
        switch (len) {
        case 8:
            q = num / 10000000;
            r = num % 10000000;
            ltr = n.twonum(q);
            Str = Str + ltr + n.digit[4];
            num = r;
            break;

        case 7:
        case 6:
            q = num / 100000;
            r = num % 100000;
            ltr = n.twonum(q);
            Str = Str + ltr + n.digit[3];
            num = r;
            break;

        case 5:
        case 4:

            q = num / 1000;
            r = num % 1000;
            ltr = n.twonum(q);
            Str = Str + ltr + n.digit[2];
            num = r;
            break;

        case 3:

            if (len == 3) {
                r = num;
            }
            ltr = n.threenum(r);
            Str = Str + ltr;
            num = 0;
            break;

        case 2:

            ltr = n.twonum(num);
            Str = Str + ltr;
            num = 0;
            break;

        case 1:
            Str = Str + n.unitdo[num];
            num = 0;
            break;
        default:

            num = 0;
            return ("Exceeding Crore....No conversion");

        }

    }
    return Str;
}

public static String convert(double number) {
    String temp = String.valueOf(number);
    int Rupies = Integer.parseInt(temp.substring(0, temp.indexOf(".")));
    int Paisa = Integer.parseInt(temp.substring(temp.indexOf(".") + 1, temp.length()));
    String word = "";
    if (Rupies > 0) {
        word = convert(Rupies) + " Rupees";
    }
    if (Paisa > 0) {
        word += convert(Paisa) + " Paisa";
    }
    word += " Only.";
    return word;
}

public static String convert(float number) {
    String temp = String.valueOf(number);
    int Rupies = Integer.parseInt(temp.substring(0, temp.indexOf(".")));
    int Paisa = Integer.parseInt(temp.substring(temp.indexOf(".") + 1, temp.length()));
    String word = "";
    if (Rupies > 0) {
        word = convert(Rupies) + " Rupees";
    }
    if (Paisa > 0) {
        word += convert(Paisa) + " Paisa";
    }
    word += " Only.";
    return word;
}
//    public static void main(String[] args) throws Exception{
//        double Rs=1252.23;       
//         System.out.println(convert(Rs));
//    }
}
