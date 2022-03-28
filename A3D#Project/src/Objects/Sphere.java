//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Sphere.java
//#     Description : Represent a sphere object
//#
//######################################################################//
package Objects;

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
    double getIntersection(Vec3f p, Vec3f v) {

        return 0;
    }
    
}
