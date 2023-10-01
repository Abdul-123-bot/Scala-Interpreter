

public class Category {

public static final int VARINT  = 0;
public static final int VARLIST  = 1;
public static final int IDFUNC = 2;
public static final int VARBOOLEAN = 3;

public static String toString (int cat) {
 switch (cat) {
   case VARINT  : return "Int ";
   case VARLIST  : return "List ";
   case IDFUNC : return "Function";
   case VARBOOLEAN : return "Boolean";
   default        : return null;
 }
}

}