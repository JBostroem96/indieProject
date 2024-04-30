package edu.restService;

import edu.matc.entity.Race;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

//Defines the base URI for all resource URIs.
@ApplicationPath("/restService") //You may want to add a value here so that all traffic isn't routed to the class below.

//The java class declares root resource and provider classes
public class RestService extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet webServiceList = new HashSet<Class<?>>();
        webServiceList.add(Races.class);
        webServiceList.add(Teams.class);
        return webServiceList;
    }
}