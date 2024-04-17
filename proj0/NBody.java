public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int num = in.readInt();
		Planet [] planets = new Planet[num];
		in.readDouble();
		for(int i=0;i<num;i++){
			double xxpos = in.readDouble();
			double yypos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxpos,yypos,xxVel,yyVel,mass,imgFileName);
		}
		return planets;
	}
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);

		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-radius,radius);
		StdDraw.picture(0,0, "images/starfield.jpg");

		for(Planet planet : planets) {
			planet.draw();
		}

		int number_planets = planets.length;
		for(double time = 0;time<T;time+=dt){
			double[] xForce =  new double[number_planets];
			double[] yForce = new double[number_planets];

			for(int i =0; i<number_planets;i++){
				xForce[i] = planets[i].calcNetForceExertedByX(planets);
				yForce[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0;i<number_planets;i++){
				planets[i].update(dt,xForce[i],yForce[i]);
			}

			StdDraw.picture(0,0, "images/starfield.jpg");
			for(Planet planet : planets) {
				planet.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    	}
	}
} 