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
import utils.MyMath;
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
    double getIntersection(Vec3f p, Vec3f v) {


        if(MyMath.scalarProd(p,v) != 0){

            double scalarP = -MyMath.scalarProd(v, normal) - d;
            double scalarV = MyMath.scalarProd(p,v);

            double lambda = scalarP/scalarV;
            
            if(lambda > 0){
                MyLogger.loginfo("Calculating intersection : lambda = " + lambda);
                return lambda;
            }
            else{
                MyLogger.logwarning("Calculating intersection : lambda is negative.");
                return -1;
            }

        }
        else {
            MyLogger.logsevere("Calculating intersection : Ray is not valid.");
            return -1;
        }

    }
    
}
