package hr.fer.zemris.java.raytracer;

import java.util.Random;

import hr.fer.zemris.java.raytracer.model.GraphicalObject;
import hr.fer.zemris.java.raytracer.model.IRayTracerProducer;
import hr.fer.zemris.java.raytracer.model.IRayTracerResultObserver;
import hr.fer.zemris.java.raytracer.model.LightSource;
import hr.fer.zemris.java.raytracer.model.Point3D;
import hr.fer.zemris.java.raytracer.model.Ray;
import hr.fer.zemris.java.raytracer.model.RayIntersection;
import hr.fer.zemris.java.raytracer.model.Scene;
import hr.fer.zemris.java.raytracer.viewer.RayTracerViewer;

public class RayCaster {

	public static void main(String[] args) {
		RayTracerViewer.show(getIRayTracerProducer(), new Point3D(10, 0, 0), new Point3D(0, 0, 0),
				new Point3D(0, 0, 10), 20, 20);
	}

	private static IRayTracerProducer getIRayTracerProducer() {
		return new IRayTracerProducer() {
		
			@Override
			public void produce(Point3D eye, Point3D view, Point3D viewUp,
					double horizontal, double vertical, int width, int height,
					long requestNo, IRayTracerResultObserver observer) {
			
				System.out.println("Započinjem izračune...");
				short[] red = new short[width*height];
				short[] green = new short[width*height];
				short[] blue = new short[width*height];
				Point3D zAxis = view.sub(eye).normalize();
				Point3D yAxis = viewUp.sub(view.sub(eye).scalarMultiply(view.sub(eye).scalarProduct(viewUp))).normalize();
				Point3D xAxis = view.sub(eye).vectorProduct(yAxis).normalize();
				Point3D screenCorner = view.sub(xAxis.scalarMultiply(horizontal/2)).add(yAxis).scalarMultiply(vertical/2);
				Scene scene = RayTracerViewer.createPredefinedScene();
				short[] rgb = new short[3];
				int offset = 0;
				for(int y = 0; y < height; y++) {
					for(int x = 0; x < width; x++) {
						Point3D screenPoint = screenCorner.add(xAxis.scalarMultiply(x * horizontal / (width - 1.0)))
                                .sub(yAxis.scalarMultiply(y * vertical / (height - 1.0)));
						Ray ray = Ray.fromPoints(eye, screenPoint);
						tracer(scene, ray, rgb);
						red[offset] = rgb[0] > 255 ? 255 : rgb[0];
						green[offset] = rgb[1] > 255 ? 255 : rgb[1];
						blue[offset] = rgb[2] > 255 ? 255 : rgb[2];
						offset++;
					}
				}
				System.out.println("Izračuni gotovi...");
				observer.acceptResult(red, green, blue, requestNo);
				System.out.println("Dojava gotova...");
			}

			private void tracer(Scene scene, Ray ray, short[] rgb) {
				RayIntersection closest = null;
				for(GraphicalObject object : scene.getObjects()){
					RayIntersection intersection = object.findClosestRayIntersection(ray);
					if(closest == null || intersection.getDistance() < closest.getDistance()){
						closest = intersection;
					}
				}
				
				if(closest == null){
					rgb[0] = rgb[1] = rgb[2] = 255;
					return;
				}
				
				determineColorForS(closest, scene, rgb);
			}

			private short[] determineColorForS(RayIntersection closest, Scene s, short[] rgb) {
				rgb[0] += 115;
				rgb[1] += 115;
				rgb[2] += 115;
				
				int i = 0;
				for(LightSource ls : s.getLights()){
					System.out.println(i);
					rgb[0] += ls.getR();
					rgb[1] += ls.getG();
					rgb[2] += ls.getB();
					i++;
				}
				
				return rgb;
			}
		};
	}
}
