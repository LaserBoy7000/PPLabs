package Lab1.src;

import java.util.ArrayList;
import java.util.List;

public class LukesNumberProvider{
    public LukesNumberProvider(){};
    //returns two min.
    public List<LukesNumber> GenerateN(int n){
        List<LukesNumber> res = new ArrayList<LukesNumber>();

        res.add(new LukesNumber(1, 2));
        res.add(new LukesNumber(2, 1));

        for(int i = 2; i < n; i++){
            LukesNumber nw;
            long pr1, pr2;
            pr1 = res.get(i-2).GetValue();
            pr2 = res.get(i-1).GetValue();
            nw = new LukesNumber(i+1, pr1 + pr2);
            res.add(nw);
        }

        return res;
    }
}