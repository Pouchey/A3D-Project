//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : RayObjedct.java
//#     Description : Abstract class to represent an object to trace
//#
//######################################################################//
package raytracer.Objects;

import raytracer.Scene.Color;
import raytracer.Scene.Ray;
import raytracer.utils.Vec3f;

public abstract class RayObject {


    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------
    protected Color color;
    protected double shininess;
    protected double reflectivity;
    protected double transparency;
    protected double refractionIndex;

    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public RayObject(Color color, double shininess, double reflectivity, double transparency, double refractionIndex) {
        this.color = color;
        this.shininess = shininess;
        this.reflectivity = reflectivity;
        this.transparency = transparency;
        this.refractionIndex = refractionIndex;
    }
    
    public RayObject(Color color, double shininess) {
        this.color = color;
        this.shininess = shininess;
        this.reflectivity = 0;
        this.transparency = 0;
        this.refractionIndex = 0;
    }

    //#-----------------------------------------------------------------
    //#     Methods
    //#-----------------------------------------------------------------
    public Color getColor() {
        return color;
    }
    public double getShininess() {
        return shininess;
    }
    public void setShininess(double shininess) {
        this.shininess = shininess;
    }
    public double getReflectivity() {
        return reflectivity;
    }
    public double getTransparency() {
        return transparency;
    }
    public double getRefractionIndex() {
        return refractionIndex;
    }

    //#-----------------------------------------------------------------
    //#
    //#     Compute the (first if multiple) intersection of an object
    //#     with a ray (a half-line) defined by a starting point P and
    //#     a direction vector V.
    //#
    //#     Return the parameter lambda.
    //#
    //#-----------------------------------------------------------------

    abstract public double getIntersection(Ray ray);

    //#-----------------------------------------------------------------
    //#
    //#     Compute the normal vector of an object at a given point
    //#     P.
    //#
    //#     Return the normal vector.
    //#
    //#-----------------------------------------------------------------
    abstract public Vec3f getNormal(Vec3f point);


}
