//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Ray.java
//#     Description : Represent a ray object
//#
//######################################################################//

package raytracer.Scene;

import raytracer.utils.Vec3f;

public class Ray {

    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------
    private final Vec3f origin;
    private final Vec3f direction;
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Ray(Vec3f origin, Vec3f direction) {
        this.origin = new Vec3f(origin);
        this.direction = new Vec3f(direction);
    }

    public Ray(Ray ray) {
        this.origin = new Vec3f(ray.origin);
        this.direction = new Vec3f(ray.direction);
    }
    //#-----------------------------------------------------------------
    //#     Getters
    //#-----------------------------------------------------------------
    public Vec3f getOrigin() {
        return new Vec3f(origin);
    }

    public Vec3f getDirection() {
        return new Vec3f(direction);
    }

    //#-----------------------------------------------------------------
    //#    Override
    //#-----------------------------------------------------------------
    @Override
    public String toString() {
        return "Ray{" + "origin=" + origin.x + "," + origin.y + "," + origin.z + ", direction=" + direction.x + "," + direction.y + "," + direction.z + '}';
    }

}
