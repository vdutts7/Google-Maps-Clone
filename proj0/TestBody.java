public class TestBody {
	public static void main(String[] args) {
		Body b1 =  new Body(3, 4, 4.0, 5.0, 155.9, "asteroid.gif");
		Body b2 = new Body(2, 1, 3.0, 8.0, 234.1, "electron.png");
		System.out.println("Force between bodies is " + b1.calcForceExertedBy(b2));
	}
}