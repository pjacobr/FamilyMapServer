package familyapp;

/**
 * Created by jacob on 4/6/2017.
 */

public class ToolBox {
   static public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
}
