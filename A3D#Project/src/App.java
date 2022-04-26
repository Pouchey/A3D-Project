
//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : App.java
//#     Description : Main class 
//#
//######################################################################//
import Objects.Plane;
import Objects.Sphere;
import Scene.Color;
import Scene.Light;
import Scene.Scene;
import utils.Vec3f;


public class App {
    public static void main(String[] args) throws Exception {

        // Default image name
        String fileString = "image"; 

        if(args.length > 1){
            System.out.println("Usage : java App <output_file>");
            System.exit(1);
        }
        if(args.length == 1){
            fileString = args[0];
        }

        // Create default default scene
        Scene scene = new Scene(new Vec3f(0.f,0.f,0.f),1.f);

        // Creating objects
        Plane p1 = new Plane( new Vec3f(0.f,50.f,0.f), 10.f,  Color.DARK_BLUE, 30.f,0.5,0.f,1.f);
        Plane p2 = new Plane( new Vec3f(0.f,0.f,1.f), 50.f, Color.DARK_YELLOW, 30.f);
        Plane p3 = new Plane( new Vec3f(10.f,0.f,5.f), 20.f, Color.DARK_RED, 30.f);
        Sphere s1 = new Sphere(new Vec3f(0.5f,0.5f,-5.f),0.5f, Color.GREEN, 100.f, 0.f, 0.8f, 1.f);
        Sphere s2 = new Sphere(new Vec3f(2.f,0.1f,-8.f),0.3f, Color.CYAN, 30.f, 0.f, 0.3f, 1.f);
        scene.addObject(p1);
        scene.addObject(p2);
        scene.addObject(p3);
        scene.addObject(s1);
        scene.addObject(s2);

        // Creating lights
        scene.addLight(new Light(new Vec3f(50.f,50.f,25.f),Color.DARK_GRAY,Color.LIGHT_GRAY,Color.BLUE));
        scene.addLight(new Light(new Vec3f(100.f,10.f,40.f),Color.BLACK,Color.DARK_GRAY,Color.RED));
        scene.addLight(new Light(new Vec3f(200.f,25.f,0.f),Color.BLACK,Color.DARK_GRAY,Color.LIGHT_GRAY));
        
        // Loading object into the sene
        scene.initGraphics();

        // Saving the image as <fileString>.tga 
        scene.draw(fileString);
    }
}
