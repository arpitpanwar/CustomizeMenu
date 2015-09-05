package menu.customiz.customizmenu.utils;

import android.util.Xml;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Arpit on 9/5/2015.
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String s){
        return (null == s) || s.isEmpty();
    }

    public static boolean isNullOrWhitespace(String s){
        return (null == s) || (isWhitespace(s));
    }

    /**
     * Try to encode parameter using UTF-8 else give up and return the original parameter
     * @param parameter
     * @return
     */
    public static String encodeParameter(String parameter){
        try{
            return URLEncoder.encode(parameter, Xml.Encoding.UTF_8.toString()).replaceAll("\\+","%20");
        }catch(UnsupportedEncodingException use){
            return parameter;
        }
    }

    private static boolean isWhitespace(String s){
        int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length - 1; start <= middle; start++, end--) {
                if (!Character.isSpaceChar(s.charAt(start)) || !Character.isSpaceChar(s.charAt(end))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}
