/*
* This Utility class serves to read and stream test data for the automation script,
* It will also provide a method that will load an retrieve properties specified during test execution.
*/
package org.lubanzi.Core_Ops.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
        /**
        * Properties object that will store a series of key-value pairs,
        * this being the test Data Description & value
        **/
        private static Properties properties;

        /**
        * This method is responsible for loading the data stream from the config file under the resource directory of the project.
        * This data is then converted into the properties object in which specified values can be retrieved from the object when needed.
        * @Throws IOException
        * */
        public static void loadProperties(){
                try(FileInputStream testData = new FileInputStream("src/test/resources/Test-Data/coreops.properties")){
                        properties = new Properties();
                        properties.load(testData);
                }
                catch (IOException e){
                        e.printStackTrace();
                        throw new RuntimeException("Failed to load and configure test configuration file");
                }
        }

        /**
         * This method retrieves the specified value needed based on the key of the value in the properties object
         * @Param key This is the unique key identifier used to retrieve the requested value
         * */
        public static String getProperty(String key){
                if(properties == null){
                        loadProperties();
                }
                return properties.getProperty(key);
        }
}
