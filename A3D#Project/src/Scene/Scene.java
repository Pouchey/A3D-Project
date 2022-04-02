package Scene;
import Objects.Plane;
import Objects.RayObject;
import utils.JavaTga;
import utils.Vec3f;

//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Scene.java
//#     Description : Represent a scene, 
//#
//######################################################################//

public class Scene {

    //#-----------------------------------------------------------------
    //#     Attributs
    //#-----------------------------------------------------------------
    private static final int DEFAULT_WIDTH = 1024 ; 
    private static final int DEFAULT_HEIGHT = 768; 
    private static final Vec3f DEFAULT_ORIGIN = new Vec3f(0.f,0.f,0.f);

    private int width; // Image width
    private int height; // Image height
    private Vec3f origin; // Scene origin

    private byte buffer[];

    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Scene(){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.origin = DEFAULT_ORIGIN;
        buffer = new byte[3*width*height];
    }
    public Scene(Vec3f origin){
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.origin = origin;
        buffer = new byte[3*width*height];
    }
    public Scene(int width,int height,Vec3f origin){
        this.width = width;
        this.height = height;
        this.origin = origin;
        buffer = new byte[3*width*height];
    }
    
    //#-----------------------------------------------------------------
    //#     Modify the image size
    //#-----------------------------------------------------------------
    public void setImageSize(int width,int height){
        this.width = width;
        this.height = height;
    }
    //#-----------------------------------------------------------------
    //#     Fill the scene buffer with the objects
    //#-----------------------------------------------------------------
    public void initGraphics(RayObject objects[]) {


        for(int row = 0; row < height; row++){ // for each row of the image
            for(int col = 0; col < width; col++){ // for each column of the image
                
                int index = 3*((row*width)+col); // compute index of color for pixel (x,y) in the buffer
                
                // Ensure that the pixel is black
                buffer[index]=0; // blue : take care, blue is the first component !!!
                buffer[index+1]=0; // green
                buffer[index+2]=0; // red (red is the last component !!!)

                for(RayObject o : objects){

                    // Calculate point (Xp,Yp,Zp) corresponding to pixel (Xe,Ye) 
                    Vec3f direction = new Vec3f(row,col,-20.f);

                    if(o.getIntersection(origin,direction ) > 0){

                        // Color col = getRayCOlor(origin,direction);
                        // buffer[index] = col.x;
                        // buffer[index+1] = col.y;
                        // buffer[index+2] = col.z;
                        buffer[index] = (byte) 255;
                        buffer[index+1] = (byte) 255;
                        buffer[index+2] = (byte) 255;
                    }

                }

            }
        }

    }
    //#-----------------------------------------------------------------
    //#     Create an image that display the scene
    //#-----------------------------------------------------------------
    public void draw(String filename){
        try {
            JavaTga.saveTGA(filename+".tga",buffer,width,height);
        }
        catch(Exception e)
        {
            System.err.println("TGA file not created :"+e);
        }
    }  


}
