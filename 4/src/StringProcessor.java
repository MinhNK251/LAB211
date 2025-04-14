public class StringProcessor {
    public static boolean match_student_id(String s){
        String IdPattern = "se\\d{4,6}$";
        String IdPattern1 = "he\\d{4,6}$";
        String IdPattern2 = "qe\\d{4,6}$";
        return s.equalsIgnoreCase(IdPattern) || s.equalsIgnoreCase(IdPattern1) || s.equalsIgnoreCase(IdPattern2);
    }
    
    public static String format_camel(String s){
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if(str[i])
                
        }
    }
}
