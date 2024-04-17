public class Planet{
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	private static final double G=6.67e-11;

	public Planet(double xp,double yp,double xV,
					double yV, double m, String img){
		xxPos=xp;
		yyPos=yp;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	public double calcDistance(Planet other){
		return Math.sqrt((this.xxPos-other.xxPos)*(this.xxPos-other.xxPos)
				        +(this.yyPos-other.yyPos)*(this.yyPos-other.yyPos));
	}

	public double calcForceExertedBy(Planet other){
		double distant =this.calcDistance(other);
		return G*this.mass*other.mass/(distant*distant);
	}

	public double calcForceExertedByX(Planet other){
		double xxdistant=this.xxPos>other.xxPos?(this.xxPos-other.xxPos):
										(other.xxPos-this.xxPos);
		return this.calcForceExertedBy(other)*xxdistant/this.calcDistance(other);
	}

	public double calcForceExertedByY(Planet other){
		double yydistant=this.yyPos>other.yyPos?(this.yyPos-other.yyPos):
										(other.yyPos-this.yyPos);
		return this.calcForceExertedBy(other)*yydistant/this.calcDistance(other);
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double xxTotalForce=0;
		for(Planet a_planet : allPlanets ){
			if (this.equals(a_planet)) continue;
			xxTotalForce+=calcForceExertedByX(a_planet);
		}
		return xxTotalForce;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double yyTotalForce=0;
		for(Planet a_planet : allPlanets){
			if (this.equals(a_planet)) continue;
			yyTotalForce+=calcForceExertedByY(a_planet);
		}
		return yyTotalForce;
	}
	public void update(double dt,double Fx,double Fy){
		double xxAcc=Fx/this.mass;
		double yyAcc=Fy/this.mass;
		this.xxVel=this.xxVel+xxAcc*dt;
		this.yyVel=this.yyVel+yyAcc*dt;
		this.xxPos=this.xxPos+this.xxVel*dt;
		this.yyPos=this.yyPos+this.yyVel*dt;
	}

	public void draw(){
		StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
	}
}
