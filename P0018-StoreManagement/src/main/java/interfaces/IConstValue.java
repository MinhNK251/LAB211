package interfaces;

import java.time.format.DateTimeFormatter;

public interface IConstValue {
    static final String[] FILENAMES = {
        "PRODUCT", "WAREHOUSE"
    };
    static final String WAREHOUSECODEPATTERN = "\\d{7}";
    static final DateTimeFormatter TIMEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    
    static final String PRODUCTCODEPATTERN = "P\\d{4}";
    static final String PRODUCTNAMEPATTERN = "^[a-zA-Z[ ]]{1,50}$";
    static final DateTimeFormatter DATEPATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
}