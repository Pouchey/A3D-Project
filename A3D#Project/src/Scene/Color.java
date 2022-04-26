//######################################################################//
//#
//#     Auteur : Théophile Rouchet
//#     Fichier : Color.java
//#     Description : Represent a color (BGR colorspace) encoded 
//#     in bytes
//#
//######################################################################//
package Scene;

public class Color {
    

    
        //#-----------------------------------------------------------------
        //#     Attributes
        //#-----------------------------------------------------------------
        private final float b;
        private final float g;
        private final float r;
    
        //#-----------------------------------------------------------------
        //#     Constructors
        //#-----------------------------------------------------------------
        public Color(float b, float g, float r) {
            this.b = b;
            this.g = g;
            this.r = r;
        }
    
        public Color(int b, int g, int r) {
            this((float)b, (float)g ,(float)r);
        }
    
    
        public Color(Color color) {
            
            this(color.b, color.g, color.r);
        }

        //#-----------------------------------------------------------------
        //#     Getter
        //#-----------------------------------------------------------------
        public byte getB() {
            return (byte) (Math.min(this.b,1.f)*255);
        }
        public byte getG() {
            return (byte) (Math.min(this.g,1.f)*255);
        }
        public byte getR() {
            return (byte) (Math.min(this.r,1.f)*255);
        }

        //#-----------------------------------------------------------------
        //#     Methods
        //#-----------------------------------------------------------------
        public Color add(Color c) {
            
            float newB = Math.min(this.b + c.b,1.f);
            float newG = Math.min(this.g + c.g,1.f);
            float newR = Math.min(this.r + c.r,1.f);

            return new Color(newB, newG, newR);
        }

        public Color multiply(Color c) {
            float newR = this.r * c.r;
            float newG = this.g * c.g;
            float newB = this.b * c.b;
            return new Color(newR,newG,newB);
        }

        public Color scale(double f) {
            
            float newB = this.b * (float)f;
            float newG = this.g * (float)f;
            float newR = this.r * (float)f;
            return new Color(newR,newG,newB);
        }
        //#-----------------------------------------------------------------
        //#     Default color
        //#-----------------------------------------------------------------
        public static final Color RED = new Color(1.f,0.f,0.f);
        public static final Color GREEN = new Color(0.f,1.f,0.f);
        public static final Color BLUE = new Color(0.f,0.f,1.f);
        public static final Color WHITE = new Color(1.f,1.f,1.f);
        public static final Color BLACK = new Color(0.f,0.f,0.f);
        public static final Color YELLOW = new Color(1.f,1.f,0.f);
        public static final Color CYAN = new Color(0.f,1.f,1.f);
        public static final Color MAGENTA = new Color(1.f,0.f,1.f);
        public static final Color GRAY = new Color(0.5f,0.5f,0.5f);
        public static final Color DARK_GRAY = new Color(0.25f,0.25f,0.25f);
        public static final Color LIGHT_GRAY = new Color(0.75f,0.75f,0.75f);
        public static final Color DARK_RED = new Color(0.5f,0.f,0.f);
        public static final Color DARK_GREEN = new Color(0.f,0.5f,0.f);
        public static final Color DARK_BLUE = new Color(0.f,0.f,0.5f);
        public static final Color DARK_YELLOW = new Color(0.5f,0.5f,0.f);
        


        @Override
        public String toString() {
            return "Color{" +
                    "b=" + b +
                    ", g=" + g +
                    ", r=" + r +
                    '}';
        }




}
