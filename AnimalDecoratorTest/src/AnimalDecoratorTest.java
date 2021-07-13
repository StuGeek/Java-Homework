interface Animal{
	public String getType();
	public void setType(String type);
	public String getName();
	public void eat();
	public void bark();
	public void showSkills();
}

class Dog implements Animal{
	private String type;
	private String name;
	
	public Dog(String name) {
		//��ͨ�������ͳ�ʼ��Ϊ"Dog"
		this.type = "Dog";
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void eat() {
		System.out.println("The " + type + " \"" + name + "\" is eating the food");
	}
	
	public void bark() {
		System.out.println("Woof Woof Woof");
	}
	
	public void showSkills() {
		System.out.println("The " + type + " \"" + name + "\" don't have any skill.");
	}
}

class Cat implements Animal{
	private String type;
	private String name;
	
	public Cat(String name) {
		//��ͨè�����ͳ�ʼ��Ϊ"Cat"
		this.type = "Cat";
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void eat() {
		System.out.println("The " + type + " \"" + name + "\" is eating the food");
	}
	
	public void bark() {
		System.out.println("Meow Meow Meow");
	}
	
	public void showSkills() {
		System.out.println("The " + type + " \"" + name + "\" don't have any skill.");
	}
}

class AnimalDecorator implements Animal{
	private Animal animal;
	
	public AnimalDecorator(Animal animal) {
		this.animal = animal;
	}
	
	public String getType() {
		return animal.getType();
	}
	
	public void setType(String type) {
		animal.setType(type);
	}
	
	public String getName() {
		return animal.getName();
	}
	
	public void eat() {
		System.out.println("The " + animal.getType() + " \"" + animal.getName() + "\" is eating the food");
	}
	
	public void bark() {
		animal.bark();
	}
	
	public void showSkills() {
		animal.showSkills();
	}
}

class TrainedAnimalDecorator extends AnimalDecorator{
	public TrainedAnimalDecorator(Animal animal){
		super(animal);
		//ѵ�����Ķ������ͳ�ʼ��Ϊ"trained " + ԭ���Ķ�������
		super.setType("trained " + super.getType());
	}
	
	private void shakeHands() {
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" is shaking hands with you.");
	}
	
	private void sitDown(){
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" sit down on the floor.");
	}
	
	public void showSkills() {
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" is showing its skills:");
		System.out.print("     ");
		shakeHands();
		System.out.print("     ");
		sitDown();
	}
}

class ScientificAnimalDecorator extends AnimalDecorator{
	public ScientificAnimalDecorator(Animal animal){
		super(animal);
		//��ѧ�������ͳ�ʼ��Ϊ"scientific " + ԭ���Ķ�������
		super.setType("scientific " + super.getType());
	}
	
	private void doExperiment() {
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" is doing experiment.");
	}
	
	private void writePaper(){
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" is writing the paper.");
	}
	
	public void showSkills() {
		System.out.println("The " + super.getType() + " \"" + super.getName() + "\" is showing its skills:");
		System.out.print("     ");
		doExperiment();
		System.out.print("     ");
		writePaper();
	}
}

public class AnimalDecoratorTest {
	public static void main(String args[]) {
		Cat cat = new Cat("Tom");
		Dog dog = new Dog("Herry");
		TrainedAnimalDecorator trainedCat = new TrainedAnimalDecorator(new Cat("Tom"));
		TrainedAnimalDecorator trainedDog = new TrainedAnimalDecorator(new Dog("Herry"));
		ScientificAnimalDecorator scientificCat = new ScientificAnimalDecorator(new Cat("Tom"));
		ScientificAnimalDecorator scientificDog = new ScientificAnimalDecorator(new Dog("Herry"));
		cat.eat();
		cat.bark();
		cat.showSkills();
		System.out.println("");
		dog.eat();
		dog.bark();
		dog.showSkills();
		System.out.println("");
		trainedCat.eat();
		trainedCat.bark();
		trainedCat.showSkills();
		System.out.println("");
		trainedDog.eat();
		trainedDog.bark();
		trainedDog.showSkills();
		System.out.println("");
		scientificCat.eat();
		scientificCat.bark();
		scientificCat.showSkills();
		System.out.println("");
		scientificDog.eat();
		scientificDog.bark();
		scientificDog.showSkills();
		System.out.println("");
	}
}