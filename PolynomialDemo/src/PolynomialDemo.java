/*
测试样例：
input : None
output : 
Set Method Test 
F(x)=10.0x²+9.0x+8.0 
F(x)=-10.0x²+9.0x+8.0 
F(x)=-10.0x²+-1908.0x+8.0 
F(x)=-10.0x²+-1908.0x+190908.87 

Calculation Test 
F(x)=1.0x²+0.0x+0.0 
F(x)=1.0x²+0.0x+2.0 

f1(0)=0.0 
f2(0)=2.0 
f1(1)=1.0 
f2(1)=3.0 
f1(3)=9.0 
f2(3)=11.0 

Solution Test 
y = 0 
f1:true 
solution: -0.0 
f2:false 

y = 1 
f1:true 
solution: 1.0, -1.0 
f2:false 

y = 3 
f1:true 
solution: 1.7320508075688772, -1.7320508075688772 
f2:true 
solution: 1.0, -1.0
 */

import java.math.*;

class Polynomial {
	double a = 0;
	double b = 0;
	double c = 0;

	Polynomial(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	void setA(double a) {
		this.a = a;
	}

	void setB(double b) {
		this.b = b;
	}

	void setC(double c) {
		this.c = c;
	}

	void showPolynomial() {
		System.out.println("F(x)=" + a + "x²+" + b + "x+" + c);
	}

	double getY(double x) {
		return a * x * x + b * x + c;
	}

	public boolean hasSolution(double y) {
		return (b * b - 4 * a * (c - y)) >= 0;
	}

	void showSolution(double y) {
		if (!hasSolution(y)) {
			System.out.println("no solution");
		} else {
			double x1 = (-b + Math.sqrt(b * b - 4 * a * (c - y))) / (2 * a);
			double x2 = (-b - Math.sqrt(b * b - 4 * a * (c - y))) / (2 * a);
			System.out.println("solution:" + x1 + "," + x2);
		}
	}
}

public class PolynomialDemo {
	public static void main(String[] args) {
		Polynomial f = new Polynomial(10, 9, 8);
		Polynomial f1 = new Polynomial(1, 0, 0);
		Polynomial f2 = new Polynomial(1, 0, 2);
		int[] tempArray = { 0, 1, 3 };
		// Set Method Test
		System.out.println("Set Method Test");
		f.showPolynomial();
		f.setA(-10);
		f.showPolynomial();
		f.setB(-1908);
		f.showPolynomial();
		f.setC(190908.87);
		f.showPolynomial();
		// F(x) Calculation Test
		System.out.println("\nCalculation Test");
		f1.showPolynomial();
		f2.showPolynomial();
		System.out.println("");
		for (int i = 0; i < tempArray.length; i++) {
			System.out.println("f1(" + String.valueOf(tempArray[i]) + ")=" + String.valueOf(f1.getY(tempArray[i])));
			System.out.println("f2(" + String.valueOf(tempArray[i]) + ")=" + String.valueOf(f2.getY(tempArray[i])));
		}
		// F(x)=0 Solution Test
		System.out.println("\nSolution Test");
		for (int i = 0; i < tempArray.length; i++) {
			System.out.println("y = " + String.valueOf(tempArray[i]));
			boolean result1 = f1.hasSolution(tempArray[i]);
			boolean result2 = f2.hasSolution(tempArray[i]);
			System.out.print("f1:");
			System.out.println(result1);
			if (result1) {
				f1.showSolution(tempArray[i]);
			}
			System.out.print("f2:");
			System.out.println(result2);
			if (result2) {
				f2.showSolution(tempArray[i]);
			}
			System.out.println("");
		}
	}
}