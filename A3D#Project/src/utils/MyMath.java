package utils;
//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : MyMath.java
//#     Description : Mathematics operation s
//#
//######################################################################//

public class MyMath {
    
    private MyMath(){};

    //#-----------------------------------------------------------------
    //#     Do a scalar product with 2 Vec3f
    //#-----------------------------------------------------------------
    public static double scalarProd(Vec3f a, Vec3f b){

        double tmp = a.x*b.x + a.y*b.y + a.z + b.z;
        return tmp;
    }

}
