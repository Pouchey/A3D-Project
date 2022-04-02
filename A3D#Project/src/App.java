import Objects.Plane;
import Objects.RayObject;
import Objects.Sphere;
import Scene.Scene;
import utils.Vec3f;

//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : App.java
//#     Description : Main class 
//#
//######################################################################//

public class App {
    public static void main(String[] args) throws Exception {

        // Create default scene
        Scene scene = new Scene();

        // Creating objects
        Plane p1 = new Plane( new Vec3f(5.f,0.f,-5.f), 50.f );
        Plane p2 = new Plane( new Vec3f(-5.f,0.f,5.f), 10.f );
        // Sphere s = new Sphere(new Vec3f(25.f,25.f,-25.f),25.f);

        RayObject objects[] = {p1,p2};

        // Loading object into the sene
        scene.initGraphics(objects);

        // Saving the image as test.tga
        scene.draw("test");
    }
}
