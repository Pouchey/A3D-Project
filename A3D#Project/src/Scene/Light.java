//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Light.java
//#     Description : Represent a phong light 
//#
//######################################################################//

package Scene;

import utils.Vec3f;

public class Light {
    
    //#-----------------------------------------------------------------
    //#     Attributes
    //#-----------------------------------------------------------------
    private final Vec3f position;
    private final Color ambientColor;
    private final Color diffuseColor;
    private final Color specularColor;
    
    //#-----------------------------------------------------------------
    //#     Constructors
    //#-----------------------------------------------------------------
    public Light(Vec3f position,Color ambientColor,Color diffuseColor,Color specularColor){
        this.position = new Vec3f(position);
        this.ambientColor = ambientColor;
        this.diffuseColor = diffuseColor;
        this.specularColor = specularColor;
    }

    public Light(Light light){
        this.position = new Vec3f(light.position);
        this.ambientColor = new Color(light.ambientColor);
        this.diffuseColor = new Color(light.diffuseColor);
        this.specularColor = new Color(light.specularColor);
    }
    //#-----------------------------------------------------------------
    //#     Getters
    //#-----------------------------------------------------------------
    public Vec3f getPosition() {
        return new Vec3f(position);
    }

    public Color getAmbientColor() {
        return new Color(ambientColor);
    }

    public Color getDiffuseColor() {
        return new Color(diffuseColor);
    }

    public Color getSpecularColor() {
        return new Color(specularColor);
    }



    

}
