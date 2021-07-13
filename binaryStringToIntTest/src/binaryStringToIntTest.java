import java.util.Scanner;

public class binaryStringToIntTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//每次测试样例字符串都为"a'10101010101111111110010111001000101010bcd"
		String s = "a'10101010101111111110010111001000101010bcd";
		System.out.println("s:" + s);
		//输入start的值
		System.out.print("start:");
		int start = input.nextInt();
		//输入end的值
		System.out.print("end:");
		int end = input.nextInt();
		input.close();
		//显示结果
		System.out.print("result:");
		try {
			System.out.println(binaryStringToInt(s, start, end));
		}
		//处理显示结果时可能出现的异常
		catch (StringIndexOutOfBoundsException e) {
			System.out.println("string index out of bounds.");
		}
		catch (ArithmeticException e) {
			System.out.println("out of bits size of int.");
		}
		catch (NumberFormatException e) {
			System.out.println("incorrect binary number format.");
		}
	}
	
	static int binaryStringToInt(String s, int start, int end) {
		int result = 0;
		//如果start或end超出字符串的合法范围，抛出下标越界异常
		if (start < 0 || end >= s.length()) {
			throw new StringIndexOutOfBoundsException();
		}
		//如果二进制数位数超过int所能表示的32位，抛出算术异常
		if (end - start > 32) {
			throw new ArithmeticException();
		}
		//如果二进制数中有不是0或1的字符，抛出数字格式异常
		if (s.charAt(start) < '0' || s.charAt(start) > '1') {
			throw new NumberFormatException();
		}
		//二进制数是正数，直接计算原码
		else if (s.charAt(start) == '0') {
			for (int i = start; i < end; ++i) {
				if (s.charAt(i) < '0' || s.charAt(i) > '1') {
					throw new NumberFormatException();
				}
				if (i == start) continue;
				result *= 2;
				result += s.charAt(i) - '0';
			}
		}
		//二进制数是负数，需要按照补码形式进行运算
		else if (s.charAt(start) == '1') {
			for (int i = start; i < end; ++i) {
				if (s.charAt(i) < '0' || s.charAt(i) > '1') {
					throw new NumberFormatException();
				}
				if (i == start) continue;
				result *= 2;
				result += s.charAt(i) - '0' == 0 ? 1 : 0;
			}
			result += 1;
		}
		//根据符号位判断是正数还是负数
		result = s.charAt(start) == '0' ? result : -result;
		return result;
	}
}