package util.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

import application.Main;
 
public class propertiesFile {
 
    private Properties prop = null;
     
    public propertiesFile(String file){
         
        InputStream is = null;
        try {
            this.prop = new Properties();
            is = new FileInputStream(Main.getInstance().getDataFolder() + File.separator + "lang" + File.separator + file);
            //this.getClass().get.getResourceAsStream(Main.getInstance().getDataFolder() + File.separator + "lang" + File.separator + file);
            prop.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public Set<Object> getAllKeys(){
        Set<Object> keys = prop.keySet();
        return keys;
    }
     
    public String getPropertyValue(String key){
        return this.prop.getProperty(key);
    }
}
