package com.automationtest.utils.execution;

import com.automationtest.utils.constants.Paths;
import com.automationtest.utils.overwritedata.FeatureOverwrite;
import com.automationtest.utils.templates.EnvironmentConfig;
import static com.automationtest.utils.constants.Messages.*;
import static com.automationtest.utils.constants.Constants.*;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class ControlExecution {

    static List<String> allFeatures = new ArrayList<>();
    static EnvironmentConfig environmentConfig = new EnvironmentConfig();
    private static boolean parameterizedSegmentation;

    private ControlExecution() {
        throw new IllegalStateException(ST_UTILITY_CLASS);
    }

    public static void featuresSegmentation(){

        String totalAgents = environmentConfig.getVariable(ENV_SYSTEM_TOTALJOBSINPHASE);
        String agentNumber = environmentConfig.getVariable(ENV_SYSTEM_JOBPOSITIONINPHASE);
        log.info("=====> Total Agents '" + totalAgents + "' | Agent number '" + agentNumber + "'");
        allFeatures = FeatureOverwrite.listFilesByFolder( new File(Paths.featuresPath()));

        if(valitateParalelExecution(totalAgents)){
            List<String> pathsFeaturesToRemove = getPathsFeatureToRemove(agentNumber);
            if(!parameterizedSegmentation){
                pathsFeaturesToRemove.clear();
                pathsFeaturesToRemove = getPathsFeatureToRemoveDefault(totalAgents,agentNumber);
            }
            removeFeatures(pathsFeaturesToRemove);
        }
        FeatureOverwrite.clearListFilesByFolder();
    }

    private static boolean valitateParalelExecution(String totalAgents){
        totalAgents = totalAgents.isEmpty() ? "1" : totalAgents;
        return Integer.parseInt(totalAgents) > 1;
    }

    private static List<String> getPathsFeatureToRemove(String agentNum){
        List<String> featuresToDelete = new ArrayList<>();
        for(String featurePath : allFeatures){
            String data;

            boolean isPresentAgent = false;
            try(BufferedReader bufferedReader = Files.newBufferedReader(java.nio.file.Paths.get(featurePath), StandardCharsets.UTF_8)) {
                while ((data = bufferedReader.readLine()) !=null){
                    if(data.toLowerCase().trim().contains(" @agent")) parameterizedSegmentation = true;
                    isPresentAgent = data.toLowerCase().trim().contains("@agent" + agentNum);
                    if (isPresentAgent){
                        break;
                    }
                }
                if(!isPresentAgent){
                    featuresToDelete.add(featurePath);
                }
            } catch (IOException e){
                throw new IllegalStateException(ERROR_CREATING_LIST_OF_FEATURES_TO_DELETE + e.getMessage(), e);
            }
        }
        return featuresToDelete;
    }

    private static List<String> getPathsFeatureToRemoveDefault(String totalAgentsExecution, String agentNumberExecution) {

        List<String> featuresPathToRemove = new ArrayList<>();
        double totalAgents = Double.parseDouble(totalAgentsExecution);
        int agentNumber = Integer.parseInt(agentNumberExecution);
        double totalFeatures = allFeatures.size();
        double asignedFeatures = totalFeatures / totalAgents;
        double asignedFeaturesRounded = Math.round(asignedFeatures);
        log.info(String.format(FEATURES_ASIGNED, totalFeatures, asignedFeatures, asignedFeaturesRounded));

        if (asignedFeatures >= 1) {
            int numFeatureFrom = (int) ((agentNumber * asignedFeaturesRounded) - asignedFeaturesRounded) + 1;
            int numFeatureTo = Math.min((int) (agentNumber * asignedFeaturesRounded), allFeatures.size());

            for (int i = 0; i < allFeatures.size(); i++) {
                if (i < numFeatureFrom - 1 || i > numFeatureTo - 1) {
                    if (agentNumber == totalAgents && numFeatureFrom < allFeatures.size() && i >= numFeatureTo) {
                        int featuresExtra = allFeatures.size() - numFeatureTo;
                        log.info(FEATURES_EXTRA_TO_EXECUTE + featuresExtra);
                    } else {
                        featuresPathToRemove.add(allFeatures.get(i));
                    }
                }
            }
        } else {
            throw new IllegalStateException(ST_NOT_SEGMENTATION_PROCESS);
        }
        return featuresPathToRemove;

    }

        private static void removeFeatures(List<String> pathsFeatureToRemove){
            pathsFeatureToRemove.forEach(feature -> log.info(" =====> Feature to delete: " + feature));

            for(String featurePath : pathsFeatureToRemove){
                try{
                    Files.delete(java.nio.file.Paths.get(featurePath));
                } catch (IOException e){
                    throw new IllegalStateException(ERROR_DELETING_FILE + featurePath + " - " + e.getMessage(),e);
                }
            }
        }
}

