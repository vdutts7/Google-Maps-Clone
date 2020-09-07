import java.lang.Double;
public class NBody {

	/** Given a file name as a String, returns a double corresponding to the radius of the universe in that file. */
	public static double readRadius(String fileName) {
		In in = new In(fileName);

		int firstItemInFile = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	/** Given a file name, returns an array of Bodys corresponding to the bodies in the file. */
	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);

		int numPlanets = in.readInt();
		double radius = in.readDouble();

		Body[] bodies = new Body[numPlanets];
		for (int i = 0; i<numPlanets; i++) {
			Body b = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
			bodies[i] = b;
		}

		return bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);

		String filename = args[2];
		Body[] bodies = readBodies(filename);
		double radius = readRadius(filename);
		
		StdDraw.setScale(-512, 512);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Body b : bodies) {
			b.draw();
		}
		StdDraw.enableDoubleBuffering();

		double time = 0;
		while (time<T) {
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];


			for (int i = 0; i<bodies.length; i++) {
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int j = 0; j<bodies.length; j++) {
				bodies[j].update(dt, xForces[j], yForces[j]);
			}

			StdDraw.setScale(-512, 512);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body b : bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            			  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
     					  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
	}
}