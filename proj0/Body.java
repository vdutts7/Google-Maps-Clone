import java.lang.Math;
public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	/** Body constructor. */
	public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	/** Body constructor copy. */
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;

	}

	/** Calculates distance between this instance of the Body and another body, b2. */
	public double calcDistance(Body b2) {
		return Math.sqrt(Math.pow((xxPos - b2.xxPos), 2) + Math.pow((yyPos - b2.yyPos), 2));
	}

	/** Takes in a Body, and returns a double describing the force exerted on this body by the given body. */
	public double calcForceExertedBy(Body b2) {
		return (6.67*Math.pow(10, -11))*mass*b2.mass/Math.pow(calcDistance(b2), 2);
	}

	/** Takes in a Body, and returns a double describing the force exerted on this body by the given body in the x direction. */
	public double calcForceExertedByX(Body b2) {
		return calcForceExertedBy(b2)*(b2.xxPos - xxPos)/calcDistance(b2);
	}

	/** Takes in a Body, and returns a double describing the force exerted on this body by the given body in the y direction. */
	public double calcForceExertedByY(Body b2) {
		return calcForceExertedBy(b2)*(b2.yyPos - yyPos)/calcDistance(b2);
	}

	/** Take in an array of Bodys and calculates the net X force exerted by all bodies in that array upon the current Body. */
	public double calcNetForceExertedByX(Body[] bodies) {
		double netForceExertedByX = 0;
		for (int i = 0; i<bodies.length; i++) {
			if (! (bodies[i].equals(this))) {
				netForceExertedByX += calcForceExertedByX(bodies[i]);
			}
		}
		return netForceExertedByX;
	}

	/** Take in an array of Bodys and calculates the net Y force exerted by all bodies in that array upon the current Body. */
	public double calcNetForceExertedByY(Body[] bodies) {
		double netForceExertedByY = 0;
		for (int i = 0; i<bodies.length; i++) {
			if (! (bodies[i].equals(this))) {
				netForceExertedByY += calcForceExertedByY(bodies[i]);
			}
		}
		return netForceExertedByY;
	}

	/** Determines how much the forces fX and fY exerted on the body will cause that body to accelerate, and the resulting change in 
	* the body’s velocity and position in a small period of time dt. update the body’s position and velocity instance variables. */
	public void update(double dt, double fX, double fY) {
		double netAccelerationX = fX/mass;
		double netAccelerationY = fY/mass;
		xxVel = xxVel + dt*netAccelerationX;
		yyVel = yyVel + dt*netAccelerationY;
		xxPos = xxPos + dt*xxVel;
		yyPos = yyPos + dt*yyVel;
	}

	/** Uses the StdDraw API to draw the Body’s image at the Body’s position, divided by the pre- calculated scale 
	/* equal to 488281250, (based on: universe radius, Sun's position, canvas size). 
	*/
	public void draw() {
		StdDraw.picture(xxPos/488281250, yyPos, "images/" + imgFileName);
	}


}