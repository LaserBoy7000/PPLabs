package Lab1.src;

import java.util.ArrayList;
import java.util.List;

public class LukesNumberAnalyzer{
    public List<LukesNumber> GetOfPow3p1(Iterable<LukesNumber> list){
        var res = new ArrayList<LukesNumber>();
        for (LukesNumber ln : list) {
            if(new MathExtensions().IsPerfectCube(ln.GetValue() - 1))
                res.add(ln);
        }
        return res;
    }
}