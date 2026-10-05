// 3 ms | 43.7 MB
class Solution {
    public String concatHex36(int n) {
        int square = n * n;
        int cube = n * n * n;

        return convert(square, 16) + convert(cube, 36);
    }

    private String convert(int num, int base) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            int rem = num % base;
            sb.append(digits.charAt(rem));
            num /= base;
        }

        return sb.reverse().toString();
    }
}