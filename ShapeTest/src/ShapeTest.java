import java.math.*;
import java.text.DecimalFormat;

abstract class Shape {
	 // fields
	 protected String name;
	 // methods
	 public Shape(String name){
	 this.name = name;
	 }
	 public String getName() {
	 return name;
	 }
	 public void setName(String name) {
	 this.name = name;
	 }
	 // abstract method
	 public abstract double getArea();
	 public abstract void showDescription(); 
}

//Բ��
class Circle extends Shape{
	private double radius;//԰�İ뾶
	
	public Circle(double radius){
		 super("Circle");
		 this.radius = radius;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		return Math.PI * radius * radius;//Բ�����=��*r��ƽ��
	}
	
	public double getPerimeter() {
		return 2 * Math.PI * radius;//԰���ܳ�=2��r
	}
	public void showDescription() {
		System.out.println("Shape: " + name);
		System.out.println("radius: " + new DecimalFormat("0.0000").format(radius));//����λС����ʽ���
		System.out.println("Area: " + new DecimalFormat("0.0000").format(getArea()));
		System.out.println("Perimeter: " + new DecimalFormat("0.0000").format(getPerimeter()));
		System.out.println("");
	}
}

//Բ��
class Cylinder extends Shape{
	private double radius;
	private double height;
	
	public Cylinder(double radius, double height){
		 super("Cylinder");
		 this.radius = radius;
		 this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getRadius(){
		return radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		return 2 * Math.PI * radius * (radius + height);//Բ���ı����=2��*r(r+h)
	}
	
	public double getVolume() {
		return Math.PI * radius * radius * height;//Բ�������=��*h*r��ƽ��
	}
	
	public void showDescription() {
		System.out.println("Shape: " + name);
		System.out.println("radius: " + new DecimalFormat("0.0000").format(radius));
		System.out.println("height: " + new DecimalFormat("0.0000").format(height));
		System.out.println("Area: " + new DecimalFormat("0.0000").format(getArea()));
		System.out.println("Volume: " + new DecimalFormat("0.0000").format(getVolume()));
		System.out.println("");
	}
}

public class ShapeTest {
	public static void main(String args[]){
		Shape shape1 = new Circle(3.0);
		Shape shape2 = new Cylinder(3.0, 4.0);
		double sumAreaOfShape = shape1.getArea() + shape2.getArea();
		System.out.println("Sum area of shape is: " +
				new DecimalFormat("0.0000").format(sumAreaOfShape));
		System.out.println("The name of shape1 is: " +
				shape1.getName());
		System.out.println("The name of shape2 is: " +
				shape2.getName());
		System.out.println("");
		shape1.showDescription();
		shape2.showDescription();
	}
}

