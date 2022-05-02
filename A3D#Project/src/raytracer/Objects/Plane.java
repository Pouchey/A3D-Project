//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Planes.java
//#     Description : Represent a plane object
//#
//######################################################################//
package raytracer.Objects;

import raytracer.Scene.Color;
import raytracer.Scene.Ray;
import raytracer.utils.Vec3f;

public class Plane extends RayObject{


    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------

    private final Vec3f normal; // Normal that define the orientation
    private final double d; // Translation of the plan
    
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Plane(Vec3f normal,float z,Color color,double shininess){
        super(color,shininess);
        this.normal = new Vec3f(normal).normalize();
        this.d = z;
    }
    public Plane(Vec3f normal,float z,Color color,double shininess,double reflectivity,double transparency,double refractionIndex){
        super(color,shininess,reflectivity,transparency,refractionIndex);
        this.normal = new Vec3f(normal).normalize();
        this.d = z;
        
    }
    //#-----------------------------------------------------------------
    //#     Calculate the intersection between a plan and a ray
    //#-----------------------------------------------------------------
    @Override
    public double getIntersection(Ray ray) {

        double lambda = -1;

        double n = new Vec3f(normal).dotProduct(ray.getDirection());
        if(n != 0){
            double v = new Vec3f(normal).dotProduct(ray.getOrigin()) + this.d;
            lambda = (-v)/n;
        }
        return lambda;
        

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
        return new Vec3f(normal);
    }
    
}
