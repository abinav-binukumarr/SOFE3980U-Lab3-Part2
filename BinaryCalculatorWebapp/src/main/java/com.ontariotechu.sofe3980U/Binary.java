package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
    private String number = "0"; // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored. Trailing zeros will be excluded and empty string will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0"; // Default to "0" for null or empty input
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0"; // Default to "0" for invalid input
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // If all digits are '0', ensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        // Ensure empty strings are replaced with "0"
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adding two binary variables.
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value of <i>num1+num2</i>.
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    /**
     * Multiplication of two binary numbers.
     *
     * @param num1 The first multiplicand
     * @param num2 The second multiplicand
     * @return A binary variable with a value of <i>num1 * num2</i>.
     */
    public static Binary multiply(Binary num1, Binary num2) {
        int decimal1 = Integer.parseInt(num1.number, 2);
        int decimal2 = Integer.parseInt(num2.number, 2);
        int product = decimal1 * decimal2;
        return new Binary(Integer.toBinaryString(product));
    }

    /**
     * Bitwise AND operation between two binary numbers.
     *
     * @param num1 The first operand
     * @param num2 The second operand
     * @return A binary variable with a value of <i>num1 & num2</i>.
     */
    public static Binary and(Binary num1, Binary num2) {
        int length = Math.max(num1.number.length(), num2.number.length());
        String paddedNum1 = String.format("%" + length + "s", num1.number).replace(' ', '0');
        String paddedNum2 = String.format("%" + length + "s", num2.number).replace(' ', '0');

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append((paddedNum1.charAt(i) == '1' && paddedNum2.charAt(i) == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    /**
     * Bitwise OR operation between two binary numbers.
     *
     * @param num1 The first operand
     * @param num2 The second operand
     * @return A binary variable with a value of <i>num1 | num2</i>.
     */
    public static Binary or(Binary num1, Binary num2) {
        int length = Math.max(num1.number.length(), num2.number.length());
        String paddedNum1 = String.format("%" + length + "s", num1.number).replace(' ', '0');
        String paddedNum2 = String.format("%" + length + "s", num2.number).replace(' ', '0');

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append((paddedNum1.charAt(i) == '1' || paddedNum2.charAt(i) == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }
}
