import java.util.Scanner;

public class binaryStringToIntTest {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//ÿ�β��������ַ�����Ϊ"a'10101010101111111110010111001000101010bcd"
		String s = "a'10101010101111111110010111001000101010bcd";
		System.out.println("s:" + s);
		//����start��ֵ
		System.out.print("start:");
		int start = input.nextInt();
		//����end��ֵ
		System.out.print("end:");
		int end = input.nextInt();
		input.close();
		//��ʾ���
		System.out.print("result:");
		try {
			System.out.println(binaryStringToInt(s, start, end));
		}
		//������ʾ���ʱ���ܳ��ֵ��쳣
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
		//���start��end�����ַ����ĺϷ���Χ���׳��±�Խ���쳣
		if (start < 0 || end >= s.length()) {
			throw new StringIndexOutOfBoundsException();
		}
		//�����������λ������int���ܱ�ʾ��32λ���׳������쳣
		if (end - start > 32) {
			throw new ArithmeticException();
		}
		//��������������в���0��1���ַ����׳����ָ�ʽ�쳣
		if (s.charAt(start) < '0' || s.charAt(start) > '1') {
			throw new NumberFormatException();
		}
		//����������������ֱ�Ӽ���ԭ��
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
		//���������Ǹ�������Ҫ���ղ�����ʽ��������
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
		//���ݷ���λ�ж����������Ǹ���
		result = s.charAt(start) == '0' ? result : -result;
		return result;
	}
}