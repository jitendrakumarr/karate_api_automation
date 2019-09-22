package framework;

import com.mifmif.common.regex.Generex;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Pattern;

public class Utils {
    static HashMap<String, String> config = new HashMap<>();

    /*
     * Example: Then def value = Utils.AddConfig('key_name#'+value)
     * */

    public static void AddConfig(String value) {
        String key = value.split(Pattern.quote("#"))[0];
        String val = value.split(Pattern.quote("#"))[1];
        config.put(key, val);
    }

    /*
     * Then def profileID = Utils.returnConfig('key_name')*/
    public static String returnConfig(String key) {
        String value = null;
        if (config.containsKey(key)) {
            value = config.get(key);
        }
        return value;
    }

    /*
     * @Date: 28/Feb/2019
     * This Method is added to remove '=' sign and replace it with ':' for as json required.
     * */

    public static String getPayloadFromString(String rawPayload) {
        String payload = rawPayload.replaceAll("=", ":");
        return payload;
    }

    public static void delete_folder(String folder) {
        File directory = new File(folder);
        if (!directory.exists()) {
            System.out.println("Direcorty Does not Exist");
        } else {
            try {
                delete(directory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Folder Delete :" + folder);
    }

    public static void delete(File file)
            throws IOException {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    delete(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }

	/*public static void handlePaths() {
		if(Constants.Environment.toLowerCase().equals("fmcc")) {
			System.out.println(Constants.Response_Json_Path + Constants.Natco );
			Constants.Response_Json_Path += "/" + Constants.Natco;
			System.out.println("Changed made for path fmcc" + Constants.Environment);
		}else if (Constants.Environment.toLowerCase().equals("hal")) {
			Constants.Response_Json_Path += "/" + Constants.Natco;
			Constants.Endpoints_Path += "/" + Constants.Natco;
			System.out.println("Changed made for path hal");
		}
	}*/

    public static String extractString(String json) {
        String st = json.replace("[\"", "").replace("\"]", "");
        Utils.AddConfig("str_extracted#" + st);
        return st;
    }

    public static String getRandomAlphaString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomStringWithSpecialChars() {
        Generex generex = new Generex("[!@#$%^&*()][!@#$%^&*()][!@#$%^&*()][!@#$%^&*()][!@#$%^&*()]");
        return generex.random();
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String getRandomEmail() {
        String email = "autoApi" + getRandomString(5) + '@' + getRandomString(4) + ".com";
        return email;
    }

    /* Generating random number of N digit */
    private static Random rnd = new Random();

    public static String getRandomNumber(int digCount) {
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('0' + rnd.nextInt(10)));
        return sb.toString();
    }


    public static String touppercase(String param) {
        return param.toUpperCase();
    }

    static public String get_date_time_as_string() {
        DateFormat df = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
        Date dateObj = new Date();
        return df.format(dateObj);
    }

    static public String get_date_time_as_string(String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        Date dateObj = new Date();
        return df.format(dateObj);
    }

    public static boolean stringContains(String string1, String string2) {
        System.out.println(string1.contains(string2));
        return (string1.contains(string2));
    }

}
