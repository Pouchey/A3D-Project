//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Scene.java
//#     Description : Represent a scene, 
//#
//######################################################################//

package Scene;
import java.util.ArrayList;

import Objects.RayObject;
import utils.JavaTga;
import utils.MyLogger;
import utils.Vec3f;


public class Scene {

    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------
    private static final int DEFAULT_WIDTH = 1024 ; 
    private static final int DEFAULT_HEIGHT = 768; 
    private static final Vec3f DEFAULT_ORIGIN = new Vec3f(0.f,0.f,0.f);
    private static final float DEFAULT_DIRECTION = 1.f;
    private static final Color DEFAULT_AMBIENT = Color.BLACK;

    private final int MAX_DEPTH = 100;

    private int width; // Image width
    private int height; // Image height
    private Vec3f origin; // Scene origin
    private float direction; // Direction of the ray

    private Color ambientColor; // Ambient color of the scene

    private ArrayList<RayObject> objects; // Array of objects
    private ArrayList<Light> lights; // Array of lights

    private int depth; // Depth of the ray

    private byte buffer[]; // Image buffer


    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Scene(){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.origin = new Vec3f(DEFAULT_ORIGIN);
        this.direction = DEFAULT_DIRECTION;
        this.ambientColor = DEFAULT_AMBIENT;
        objects = new ArrayList<RayObject>();
        lights = new ArrayList<Light>();
        depth = 0;
        buffer = new byte[3*width*height];
    }
    public Scene(float direction){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.origin = new Vec3f(DEFAULT_ORIGIN);
        this.direction = direction;
        this.ambientColor = DEFAULT_AMBIENT;
        objects = new ArrayList<RayObject>();
        lights = new ArrayList<Light>();
        depth = 0;
        buffer = new byte[3*width*height];
    }
    public Scene(Vec3f origin,float direction){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.origin = new Vec3f(origin);
        this.direction = direction;
        this.ambientColor = DEFAULT_AMBIENT;
        objects = new ArrayList<RayObject>();
        lights = new ArrayList<Light>();
        depth = 0;
        buffer = new byte[3*width*height];
    }
    public Scene(int width,int height,Vec3f origin,float direction){
        this.width = width;
        this.height = height;
        this.origin = new Vec3f(origin);
        this.direction = direction;
        this.ambientColor = DEFAULT_AMBIENT;
        objects = new ArrayList<RayObject>();
        lights = new ArrayList<Light>();
        depth = 0;
        buffer = new byte[3*width*height];
    }
    
    //#-----------------------------------------------------------------
    //#     Set the ambient color of the scene
    //#-----------------------------------------------------------------
    public void setAmbientColor(Color c){
        this.ambientColor = c;
    }

    //#-----------------------------------------------------------------
    //#     Modify the image size
    //#-----------------------------------------------------------------
    public void setImageSize(int width,int height){
        this.width = width;
        this.height = height;
    }
    //#-----------------------------------------------------------------
    //#     Add objects to the scene
    //#-----------------------------------------------------------------
    public void addObject(RayObject object){

        MyLogger.loginfo("Adding object to the scene");
        objects.add(object);
    }
    //#-----------------------------------------------------------------
    //#     Add lights to the scene
    //#-----------------------------------------------------------------
    public void addLight(Light light){

        MyLogger.loginfo("Adding light to the scene");
        lights.add(light);
    }

    //#-----------------------------------------------------------------
    //#      Ray tracing algorithm 
    //#-----------------------------------------------------------------
    public  Color findColor(Ray ray) {
        
        //#-----------------------------------------------------------------
        //#      Find the closest object to the ray
        //#-----------------------------------------------------------------
        RayObject closestObject = null;
        float closestIntersection = Float.MAX_VALUE;
        
        for (RayObject object : this.objects) {

            double intersection = object.getIntersection(ray);
            if (intersection > 0.001 && intersection < closestIntersection) {
                closestObject = object;
                closestIntersection = (float) intersection;
            
            }
        }
        //#-----------------------------------------------------------------
        //#      If there is an intersection
        //#-----------------------------------------------------------------
        if (closestObject != null) {

            //#-----------------------------------------------------------------
            //#      Intersection point
            //#-----------------------------------------------------------------
            Vec3f intersectionPoint = ray.getOrigin().add(ray.getDirection().scale(closestIntersection));
            //#-----------------------------------------------------------------
            //#      Normal of the intersection point
            //#-----------------------------------------------------------------
            Vec3f normal = closestObject.getNormal(intersectionPoint);
            Boolean isInside = false;
            if(ray.getDirection().dotProduct(normal) > 0){
                normal.inverse();
                isInside = true;
            }

            //#-----------------------------------------------------------------
            //#      Calculate the color of the light
            //#-----------------------------------------------------------------
            Color objectColor = new Color(closestObject.getColor());

            objectColor = objectColor.multiply(this.ambientColor);

            for (Light light : this.lights) {
                boolean vis = true;
                
                Vec3f IS = light.getPosition().sub(intersectionPoint);

                Ray shadowRay = new Ray(new Vec3f(normal).scale(0.0001f), IS);

                for (RayObject object : this.objects) {
                    double intersection = object.getIntersection(shadowRay);
                    if (intersection > 0  && intersection < 1) {
                        vis = false;
                        break;
                    }
                }

                if(vis){

                    // Calculate the diffuse ratio
                    float diffuseRatio = (float) (1.f / (1.f + closestObject.getReflectivity() + closestObject.getTransparency()));

                    // Lambertian 
                    Color diffuseColor = new Color(0,0,0);
                    IS.normalize();
                    float diffuse = Math.max(0, new Vec3f(normal).dotProduct(IS));

                    diffuseColor = closestObject.getColor().multiply(light.getDiffuseColor().scale(diffuse));
                    diffuseColor.scale(diffuseRatio);

                    // Phong
                    Color specularColor = new Color(0,0,0);
                    Vec3f R = new Vec3f(IS).add(ray.getDirection().scale(-1));
                    R.normalize();
                    float specular = Math.max(0, new Vec3f(normal).dotProduct(R));
                    specularColor = light.getSpecularColor().scale(Math.pow(specular, closestObject.getShininess()));
                    specularColor.scale(diffuseRatio);

                    // Add the color
                    objectColor = objectColor.add(diffuseColor).add(specularColor);
                }
            }


            if( (closestObject.getReflectivity() > 0 || closestObject.getTransparency() > 0) || depth < MAX_DEPTH){
                
                //#-----------------------------------------------------------------
                //#      Calculate the color of the reflection
                //#-----------------------------------------------------------------
                Color reflectionColor = new Color(0,0,0);
                if(closestObject.getReflectivity() > 0){
                    Ray reflectedRay = new Ray(new Vec3f(intersectionPoint).addScale(-0.0001f, normal), ray.getDirection().sub(normal.scale(2*ray.getDirection().dotProduct(normal)).normalize()));
                    reflectionColor = findColor(reflectedRay).scale(closestObject.getReflectivity());
                        
                    float reflRatio = (float) (closestObject.getReflectivity() / (1.f + closestObject.getReflectivity()+ closestObject.getTransparency()));
                    reflectionColor.scale(reflRatio);
                }
                //#-----------------------------------------------------------------
                //#      Calculate the color of the refraction
                //#-----------------------------------------------------------------
                Color refractionColor = new Color(0,0,0);
                if(closestObject.getTransparency() > 0){
                    float refractionIndex;
                    if(isInside)
                        refractionIndex = (float) (1.f / closestObject.getRefractionIndex());
                    else
                        refractionIndex = (float) closestObject.getRefractionIndex();

                    float refractedScale = (float) (refractionIndex*(Math.sqrt(1 - refractionIndex*refractionIndex*(1 - ray.getDirection().dotProduct(normal)*ray.getDirection().dotProduct(normal)))));
                    Ray refractedRay = new Ray(new Vec3f(intersectionPoint).addScale(-0.0001f, normal),ray.getDirection().scale(refractionIndex).addScale(refractedScale,normal).normalize());
                    refractionColor = findColor(refractedRay).scale(closestObject.getTransparency());

                    float transRatio = (float) (closestObject.getTransparency() / (1.f + closestObject.getReflectivity()+ closestObject.getTransparency()));
                    refractionColor.scale(transRatio);
                }

                //#-----------------------------------------------------------------
                //#      Add the colors
                //#-----------------------------------------------------------------
                objectColor = objectColor.add(reflectionColor);
                objectColor = objectColor.add(refractionColor);

                //#-----------------------------------------------------------------
                //#      Add the depth
                //#-----------------------------------------------------------------
                depth++;

            
            }

            return objectColor;
        }
        //#-----------------------------------------------------------------
        //#      If there is no intersection
        //#-----------------------------------------------------------------
        else {
            return Color.BLACK;
        }

    }

    //#-----------------------------------------------------------------
    //#     Fill the scene buffer with the objects
    //#-----------------------------------------------------------------
    public void initGraphics() {

        MyLogger.loginfo("Initializing the scene");

        for(int row = 0; row < height; row++){ // for each row of the image
            for(int col = 0; col < width; col++){ // for each column of the image
                
                int index = 3*((row*width)+col); // compute index of color for pixel (x,y) in the buffer
                
                // Ensure that the pixel is black
                buffer[index]=0; // blue : take care, blue is the first component !!!
                buffer[index+1]=0; // green
                buffer[index+2]=0; // red (red is the last component !!!)

                float x = (float) (col - width/2.f) / (height);
                float y = (float) (row - height/2.f) / (height);

                
                // Calculate point (Xp,Yp,Zp) corresponding to pixel (Xe,Ye) 

                Ray ray = new Ray(origin,new Vec3f(x,y,-direction).normalize());

                // Compute the color of the pixel
                Color color = findColor(ray);

                buffer[index] = color.getB();
                buffer[index+1] = color.getG();
                buffer[index+2] = color.getR();


            }
        }

    }
    //#-----------------------------------------------------------------
    //#     Create an image that display the scene
    //#-----------------------------------------------------------------
    public void draw(String filename){

        MyLogger.loginfo("Drawing the scene");
        MyLogger.loginfo("Creating image : " + filename);
        
        try {
            JavaTga.saveTGA(filename+".tga",buffer,width,height);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }  


}
