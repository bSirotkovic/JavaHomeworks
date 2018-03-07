package hr.fer.zemris.java.raytracer.model;

public class Sphere extends GraphicalObject{

	private Point3D center;
	private double radius;
	private double kdr;
	private double kdg;
	private double kdb;
	private double krr;
	private double krg;
	private double krb;
	private double krn;
	
	public Sphere(Point3D center, double radius, double kdr, double kdg,
		double kdb, double krr, double krg, double krb, double krn) {
			this.center = center;
			this.radius = radius;
			this.kdb = kdb;
			this.kdg = kdg;
			this.kdr = kdr;
			this.krb = krb;
			this.krg = krg;
			this.krn = krn;
			this.krr = krr;
		}

	public RayIntersection findClosestRayIntersection(Ray ray) {
		Point3D temp = ray.start.sub(center);
		double b = ray.direction.scalarProduct(temp);
		double c = temp.scalarProduct(temp) - radius*radius;
		double d1 = -b + Math.sqrt(b*b - c);
		double d2 = -b - Math.sqrt(b*b - c);
		
		if((Double.isNaN(d1) && Double.isNaN(d2)) || (d1 < 0 && d2 < 0)) return null;
		
		double minDistance;
		
		if(Double.isNaN(d1) || d1 < 0) minDistance = d2;
		else if(Double.isNaN(d2) || d2 < 0) minDistance = d1;
		else{
			minDistance = Math.min(d1, d2);
		}
		
		Point3D intersection = ray.start.add(ray.direction.scalarMultiply(minDistance));
		boolean outer = intersection.sub(ray.start).norm() > radius;
				
		return new RayIntersection(intersection, minDistance, outer) {
			
			@Override
			public Point3D getNormal() {
				return intersection.sub(center).normalize();
			}
			
			@Override
			public double getKrr() {
				return krr;
			}
			
			@Override
			public double getKrn() {
				return krn;
			}
			
			@Override
			public double getKrg() {
				return krg;
			}
			
			@Override
			public double getKrb() {
				return krb;
			}
			
			@Override
			public double getKdr() {
				return kdr;
			}
			
			@Override
			public double getKdg() {
				return kdg;
			}
			
			@Override
			public double getKdb() {
				return kdb;
			}
		};
	}

}
