package edu.restService;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * This class' purpose is to add all the classes where you want to integrate the web service
 */
@ApplicationPath("/restService")

public class RestService extends Application {

    /**
     * This method's purpose is to store all the corresponding classes
     * @return webServiceList a list of the webService classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet webServiceList = new HashSet<Class<?>>();
        webServiceList.add(Races.class);
        webServiceList.add(Teams.class);
        webServiceList.add(Categories.class);
        return webServiceList;
    }
}