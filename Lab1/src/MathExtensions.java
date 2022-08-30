package Lab1.src;

public class MathExtensions{
    public boolean IsPerfectCube(long value){
        var rt = Math.pow((double)value, 1.0/3);
        var rd = (long)Math.round(rt);
        var res = rd*rd*rd;
        System.out.printf("\ncrt(%d)=%f~%d -> ^3 -> %d =? %d", value, rt, rd, res, value);
        if(res == value)
            return true;
        else return false;
    }
}