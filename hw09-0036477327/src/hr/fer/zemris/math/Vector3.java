package hr.fer.zemris.math;

public class Vector3 {

	private double x;
	private double y;
	private double z;
	
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double norm() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector3 normalized() {
		double norm = norm();
		if(norm == 0){
			throw new IllegalStateException("Ovaj vektor se ne može normalizirati jer u je norma jednaka nula.");
		}
		
		return new Vector3(x/norm, y/norm, z/norm);
	}
	
	public Vector3 add(Vector3 other) {
		if(other == null){
			throw new IllegalArgumentException("Poslan je null vector kao argument.");
		}
		
		return new Vector3(x + other.x, y + other.y, z + other.z);
	}
	
	public Vector3 sub(Vector3 other) {
		if(other == null){
			throw new IllegalArgumentException("Poslan je null vector kao argument.");
		}
		
		return new Vector3(x - other.x, y - other.y, z - other.z);
	}
	
	public double dot(Vector3 other) {
		if(other == null){
			throw new IllegalArgumentException("Poslan je null vector kao argument.");
		}
		
		return x * other.x + y * other.y + z * other.z;
	}
	
	public Vector3 cross(Vector3 other) {
		if(other == null){
			throw new IllegalArgumentException("Poslan je null vector kao argument.");
		}
		
		double newX = y * other.z - z * other.y;
		double newY = z * other.x - x * other.z;
		double newZ = x * other.y - y * other.x;
		
		return new Vector3(newX, newY, newZ);
	}
	
	public Vector3 scale(double s) {
		return new Vector3(x * s, y * s, z * s);
	}
	
	public double cosAngle(Vector3 other) {
		if(other == null || norm() == 0 || other.norm() == 0){
			throw new IllegalArgumentException("Poslan je null vector kao argument ili jedan od vektora ima normu nula.");
		}
		
		return dot(other) / (norm() * other.norm());
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double[] toArray() {
		return new double[] {x,y,z};
	}
	
	public String toString() {
		return String.format("(%f, %f, %f)", x, y, z);
	}
}
