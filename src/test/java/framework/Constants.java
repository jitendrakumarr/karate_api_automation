package framework;

public class Constants {
    /* Config properties or jenkins parameters */
    public static String env =
            (System.getProperty("env") != null) ? System.getProperty("env").toString().trim() : "environment";
    public static String api =
            (System.getProperty("api") != null) ? System.getProperty("api").toString().trim() : "api";

    /*setting directory paths to the variables*/
    public static final String local_base_url = "http://www.google.com";

    public static final String local_endpoint_path = "src/test/resources/endpoints/";
    public static final String local_request_data_path = "src/test/resources/request_data/";
    public static final String local_request_headers_path = "src/test/resources/request_headers/";
    public static final String local_response_schema_path = "src/test/resources/response_schema/";



    public static final String request_data_path = local_request_data_path;
    public static String endpoints_path = local_endpoint_path;
    public static String response_schema_path = local_response_schema_path;
    public static String request_headers_path = local_request_headers_path;
    public static String base_url = local_base_url;

}
