/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarte_cliamte;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author imed
 */
public class Calcule_math {
    
    private Float ecart_type(List<Float> S) {

        int ecrt_val;

        Collections.sort(S);
        if ((S.size() % 2) != 0) {
            ecrt_val = (S.size() + 1) / 2;
            return S.get(ecrt_val);
        } else {
            return (S.get(S.size() / 2) + S.get((S.size() + 1) / 2)) / 2;
        }
    }

    
    public float difference(ArrayList<Float> s, ArrayList<Float> n) {

        return (abs(ecart_type(s) - ecart_type(n)));
    }

    
}
