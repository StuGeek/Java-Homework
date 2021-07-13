import java.util.ArrayList;
import java.util.List;

abstract class Food{
	protected String name;
	protected int price;
	
	public Food(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public abstract void take();
}

class Drink extends Food{
	public Drink(String name, int price) {
		super(name, price);
	}
	
	public void take(){
		System.out.println("The Drink " + name + " is taken");
	}
}

class Dishes extends Food{
	public Dishes(String name, int price) {
		super(name, price);
	}
	
	public void take() {
		System.out.println("The Dishes " + name + " is taken");
	}
}

class Order{
	private List<Food> foodList = new ArrayList<>();
	private int peopleAmount;
	
	public Order(int peopleAmount) {
		this.peopleAmount = peopleAmount;
	}
	
	public void addFood(Food newFood) {
		foodList.add(newFood);
		System.out.println("The food \"" + newFood.getName() +
				"\" is added to the order");
	}
	
	public void setPeopleAmount(int peopleAmount) {
		this.peopleAmount = peopleAmount;
	}
	
	public void showBill() {
		System.out.println("\nBill of the Order:");
		for (Food food : foodList) {
			System.out.println("name:" + food.getName() +
					",price: " + food.getPrice() + " yuan");
		}
		int averagePay = 0;
		try {
			//���û�˽��ˣ������dbz������׳������쳣
			if (peopleAmount < 1) {
				throw new ArithmeticException();
			}
			//���˽��ˣ������ÿ��Ӧ������Ǯ
			int pay = 0;
			for (Food food : foodList) {
				pay += food.getPrice();
			}
			averagePay = pay / peopleAmount;
			System.out.println("Each Person should pay: " + averagePay + " yuan\n");
		}
		catch (ArithmeticException e) {
			System.out.println("Error : Should be at least one person pay for the bill\n");
		}
	}
	
	public void takeFood() {
		try {
			//���ʳ���Ѿ���ȡ�꣬�׳��±�Խ���쳣
			if (foodList.isEmpty()){
			throw new ArrayIndexOutOfBoundsException();
			}
			//ÿ�ΰ������˳��ȡʳ�����ʳ��ɾ��
			foodList.get(0).take();
			foodList.remove(0);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error : All the food have already taken\n");
		}
	}
	
}

public class OrderFoodTest {
	public static void main(String args[]) {
		Order order = new Order(0);
		order.addFood(new Drink("Coke", 4));
		order.addFood(new Drink("Coffee", 10));
		order.addFood(new Drink("Juice", 6));
		order.addFood(new Drink("Tea", 5));
		order.addFood(new Dishes("tofu", 15));
		order.addFood(new Dishes("stir-fried vegetable", 20));
		order.addFood(new Dishes("fried chicken", 30));
		order.addFood(new Dishes("streamed fished", 35));
		order.showBill();
		order.addFood(new Dishes("Chow mein", 35));
		order.setPeopleAmount(4);
		order.showBill();
		for (int i = 0; i < 10; i++) {
			order.takeFood();
		}
	}
}