package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class CSVUtil {
    public static String sFunName = "";
    public static HashMap<String, String> hData;
    public static BufferedReader br;

    public static void loadCSV() throws Exception {
        String sLine;
        hData = new HashMap<String, String>();
        try {
            String path = System.getProperty("user.dir");
            path = path + "\\src\\main\\resources\\TestData.csv";
            br = new BufferedReader(new FileReader(path));
            while ((sLine = br.readLine()) != null) {
                // use comma as separator
                String[] sKeyValue = sLine.split(",");
                String sKey = sKeyValue[0];
                String sVal = sKeyValue[1];
                hData.put(sKey, sVal);
            }
        } catch (Exception e) {
        }
        br.close();
    }

    public static String getTestData(String sKey) {
        String sVal = "";
        boolean bFound = false;
        if (hData.containsKey(sKey)) {
            sVal = hData.get(sKey);
            bFound = true;
        } else {
        }
        return sVal;
    }
}
