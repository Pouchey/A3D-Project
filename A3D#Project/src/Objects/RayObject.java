//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : RayObjedct.java
//#     Description : Abstract class to represent an object to trace
//#
//######################################################################//
package Objects;

import utils.Vec3f;

public abstract class RayObject {

    //#-----------------------------------------------------------------
    //#
    //#     Compute the (first if multiple) intersection of an object
    //#     with a ray (a half-line) defined by a starting point P and
    //#     a direction vector V.
    //#
    //#     Return the parameter lambda.
    //#
    //#-----------------------------------------------------------------
    abstract double getIntersection(Vec3f p, Vec3f v);
}
