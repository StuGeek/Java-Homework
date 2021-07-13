/*在Run Configurations中输入测试样例
 Example 1:
Input:
["a","a","b","b","c","c","c"] 
Output:
6, "a2b2c3"
Example 2:
Input:
["a"] 
Output:
1, "a"
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"] 
Output:
4, "ab12"
 */
public class StringCompression {
	public static void main(String []args) 
	{
		int [] characters = new int[26];
		for (int i = 0; i < args[0].length(); ++i) 
		{
			if (args[0].charAt(i) >= 'a' && args[0].charAt(i) <= 'z') 
			{
				characters[(int)args[0].charAt(i) - (int)'a']++;
			}
		}
		int count = 0;
		for (int i = 0; i < 26; ++i)
		{
			int num = characters[i];
			if (num > 0)
			{
				count++;
				if (num > 1)
				{
					while(num > 0)
					{
						count++;
						num /= 10;
					}
				}
			}
		}
		System.out.print(count + ", \"");
		for (int i = 0; i < 26; ++i)
		{
			int num = characters[i];
			if (num > 0)
			{
				System.out.print((char)((int)'a' + i));
				if (num > 1)
				{
					System.out.print(num);
				}
			}
		} 
		System.out.print("\"");
	}
}
