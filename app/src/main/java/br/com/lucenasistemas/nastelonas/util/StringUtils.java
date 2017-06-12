package br.com.lucenasistemas.nastelonas.util;

import java.text.Normalizer;

/**
 * Created by Weverton on 10/06/2017.
 */

public class StringUtils {

    public static String removerCaracteresEspeciais(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }

    public static boolean isEmpty(String string){
        if (string == null)
            return true;
        if (string.trim().equals(""))
            return true;
        return false;
    }

    public static String join(String[] array, String separator){
        String res = "";
        for(int i = 0; i < array.length ; i++){

            res += array[i];
            if (i != array.length - 1)
                res += separator;
        }
        return res;
    }
}
