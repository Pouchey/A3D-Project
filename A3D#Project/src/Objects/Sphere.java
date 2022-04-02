//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Sphere.java
//#     Description : Represent a sphere object
//#
//######################################################################//
package Objects;

import utils.MyLogger;
import utils.Vec3f;

public class Sphere extends RayObject{


    //#-----------------------------------------------------------------
    //#     Attributs
    //#-----------------------------------------------------------------
    private Vec3f center;
    private float radius;
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Sphere(Vec3f center,Float radius){
        this.center = center;
        this.radius = radius;
    }
    //#-----------------------------------------------------------------
    //#     Calculate the intersection between a sphere and a ray
    //#-----------------------------------------------------------------
    @Override
    public double getIntersection(Vec3f p, Vec3f v) {

        Vec3f cp = new Vec3f(0,0,0).set(p).sub(center);


        double a = v.dotProduct(v);
        double b = 2*(v.dotProduct(cp));
        double c = (cp.dotProduct(cp)) - (radius*radius);

        double delta = (b*b)-(4*a*c);


        if(delta > 0){
            double lambda1 = (-b - Math.sqrt(delta))/2*a;
            double lambda2 = (-b + Math.sqrt(delta))/2*a;

            if(0 < lambda1 && lambda1 < lambda2){
                return lambda1;
            }
            else if(lambda1 < 0 && 0 < lambda2){
                return lambda2;
            }
            else{
                return -1.0;
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
    
}
