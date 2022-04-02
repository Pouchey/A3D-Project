//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Planes.java
//#     Description : Represent a plane object
//#
//######################################################################//
package Objects;

import java.lang.System.Logger;

import utils.MyLogger;
import utils.Vec3f;

public class Plane extends RayObject{


    //#-----------------------------------------------------------------
    //#     Attributs
    //#-----------------------------------------------------------------

    private final Vec3f normal; // Normal that define the orientation
    private final double d; // Translation of the plan
    
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Plane(Vec3f normal,float z){
        this.normal = normal;
        this.d = z;
    }
    //#-----------------------------------------------------------------
    //#     Calculate the intersection between a plan and a ray
    //#-----------------------------------------------------------------
    @Override
    public double getIntersection(Vec3f p, Vec3f v) {


        if(normal.dotProduct(v) != 0){

            
            double scalarP = normal.dotProduct(p) - d;
            double scalarV = normal.dotProduct(v);

            double lambda = (-scalarP)/scalarV;
            
            if(lambda > 0){
                // MyLogger.loginfo("Calculating intersection : lambda = " + lambda);
                return lambda;
            }


        }
        return -1;

    }
    
}
