//Denotable val is a class to assign type to the val 

public class DenotableValue implements Cloneable{

private int cat;
private Object val;

public DenotableValue (int cat, Object val) {
 this . cat = cat;
 this . val = val;
}
//System.out.println('cat');
public int cat () { return cat; }
//System.out.println('val');
public Object val () { return val; }

public void setValue (Object val) { val=val; }

public String toString () {
 String printString = Category . toString (cat);
 if (cat == Category . VARINT || cat == Category . VARLIST)
   printString = printString + "(" + val + ")";
 return printString;
}
}
