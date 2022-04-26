//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Sphere.java
//#     Description : Represent a sphere object
//#
//######################################################################//
package Objects;

import Scene.Color;
import Scene.Ray;
import utils.Vec3f;

public class Sphere extends RayObject{


    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------
    private Vec3f center;
    private float radius;
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Sphere(Vec3f center,Float radius, Color color, double shininess){
        super(color, shininess);
        this.center = new Vec3f(center);
        this.radius = radius;
    }
    public Sphere(Vec3f center,Float radius, Color color,double shininess, double reflectivity, double transparency, double refractionIndex){
        super(color, shininess, reflectivity, transparency, refractionIndex);
        this.center = new Vec3f(center);
        this.radius = radius;
    }
    //#-----------------------------------------------------------------
    //#     Calculate the intersection between a sphere and a ray
    //#-----------------------------------------------------------------
    @Override
    public double getIntersection(Ray ray) {

        Vec3f cp = ray.getOrigin().sub(center);

        double a = ray.getDirection().lengthSquare();
        double b = 2.f*(ray.getDirection().dotProduct(cp));

        double c = cp.lengthSquare() - (radius*radius);

        double delta = (b*b)-(4.f*a*c);


        if(delta > 0){
            double lambda1 = (-b - Math.sqrt(delta))/ (2.f*a);
            double lambda2 = (-b + Math.sqrt(delta))/ (2.f*a);

            if(lambda1 > 0){
                return lambda1;
            }
            else if(lambda2 > 0){
                return lambda2;
            }
            else{
                return -1;
            }
        }
        else if(delta == 0){
            double lambda = (-b)/2*a;
            if(lambda > 0){
                return lambda;
            }
            else{
                return -1.0;
            }
        }
        else{
            return -1.0;
        }
    }

    //#-----------------------------------------------------------------
    //#
    //#     Compute the normal vector of an object at a given point
    //#     P.
    //#
    //#     Return the normal vector.
    //#
    //#-----------------------------------------------------------------
    @Override
    public Vec3f getNormal(Vec3f intersectionPoint) {

        Vec3f ci = new Vec3f(intersectionPoint).sub(center);
        
        return ci.normalize();
    }
    
}
