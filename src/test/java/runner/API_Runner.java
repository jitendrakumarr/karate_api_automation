package runner;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import cucumber.api.CucumberOptions;
import framework.Constants;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;


@CucumberOptions(features = "src/test/scripts/", tags = {"@module"})
public class API_Runner {

    @BeforeClass
    public static void before() {
        System.setProperty("request_data_path", Constants.request_data_path);
        System.setProperty("endpoints_path", Constants.endpoints_path);
        System.setProperty("response_schema_path", Constants.response_schema_path);
        System.setProperty("request_headers_path", Constants.request_headers_path);
        System.setProperty("base_url", Constants.base_url);
    }

    @Test
    public void testParallel() {
        String karateOutputPath = "target/surefire-reports";
        KarateStats stats = CucumberRunner.parallel(getClass(), 1, karateOutputPath);
        generateReport(karateOutputPath);
        assertTrue("there are scenario failures", stats.getFailCount() == 0);
    }

    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] { "json" }, true);
        List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "API Test");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();
    }
}

