package com.automationtests.runners;


import com.automationtest.utils.constants.Paths;
import com.automationtest.utils.execution.AfterSuite;
import com.automationtest.utils.execution.BeforeSuite;
import com.automationtest.utils.execution.ControlExecution;
import com.automationtest.utils.execution.CustomCucumberWithSerenityRunner;
import com.automationtest.utils.overwritedata.FeatureOverwrite;
import io.cucumber.junit.CucumberOptions;
import lombok.extern.slf4j.Slf4j;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.automationtest.utils.constants.Constants.EXT_FEATURE;

@Slf4j
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.automationtests.hooks","com.automationtests.steps"},
        plugin = {"json:build/cucumber-reports/json/cucumber.json","summary"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = ""
)
public class WebRunnerTest {

    private WebRunnerTest(){
    }

    @BeforeSuite
    public static void init() throws IOException {
        ControlExecution.featuresSegmentation();
        List<String> features = FeatureOverwrite.listFilesByFolder( new File (Paths.featuresPath()));
        for (String feature : features){
            if (feature.contains(EXT_FEATURE)){
                FeatureOverwrite.overWriteFeatureFileAdd(feature);
            }
        }
        FeatureOverwrite.clearListFilesByFolder();
    }

    @AfterSuite
    public static void after() {

    }

}
