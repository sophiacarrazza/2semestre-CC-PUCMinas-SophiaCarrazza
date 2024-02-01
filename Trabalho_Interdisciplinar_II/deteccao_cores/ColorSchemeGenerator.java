public class ColorSchemeGenerator {

    // ... outros métodos ...

    public static String[] generateAnalogousColors(String baseColor, int numColors, float angle) {
        java.awt.Color baseRGB = java.awt.Color.decode("#" + baseColor);
        float[] hsb = java.awt.Color.RGBtoHSB(baseRGB.getRed(), baseRGB.getGreen(), baseRGB.getBlue(), null);

        String[] analogousColors = new String[numColors];
        for (int i = 0; i < numColors; i++) {
            float hue = (hsb[0] + i * angle / 360) % 1.0f;
            java.awt.Color analogousColor = new java.awt.Color(java.awt.Color.HSBtoRGB(hue, hsb[1], hsb[2]));
            analogousColors[i] = String.format("#%02X%02X%02X", analogousColor.getRed(), analogousColor.getGreen(), analogousColor.getBlue());
        }

        return analogousColors;
         System.out.println("Cores Análogas:");
        for (String color : analogousColors) {
            System.out.println(color);
        }
    }

    public static void main(String[] args) {
        // Altere a linha abaixo para receber a cor base do JavaScript
        String baseColor = args.length > 0 ? args[0] : "6A493B"; // Código hexadecimal da cor base padrão (exemplo: laranja)

        int numColors = 5;
        float angle = 30;

        //String[] analogousColors = generateAnalogousColors(baseColor, numColors, angle);

        System.out.println("Cores Análogas:");
        for (String color : analogousColors) {
            System.out.println(color);
        }
    }
}
