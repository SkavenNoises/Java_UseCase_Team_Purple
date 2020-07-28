package classes;

import java.util.HashMap;
import java.util.Map;

import static classes.Animal.*;

public class Resource {
    private int resourceID;
    private static int resourceStaticID = 0;
    private String resourceName;
    private double resourcePrice;
    private String companyName;
    private int quantityResource;

    public enum ResourceType {
        Food, Meds, Other
    }

    private ResourceType resourceType;

    static HashMap<Integer, Resource> resourcesHash = new HashMap<>();

    public Resource(String resourceName, double resourcePrice, String companyName, int quantityResource, classes.Resource.ResourceType resourceType) {
        resourceStaticID++;
        this.resourceID = resourceStaticID;
        this.resourceName = resourceName;
        this.resourcePrice = resourcePrice;
        this.companyName = companyName;
        this.quantityResource = quantityResource;
        this.resourceType = resourceType;
        resourcesHash.put(this.resourceID, this);
    }

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public double getResourcePrice() {
        return resourcePrice;
    }

    public void setResourcePrice(double resourcePrice) {
        this.resourcePrice = resourcePrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getQuantityResource() {
        return quantityResource;
    }

    public void setQuantityResource(int quantityResource) {
        this.quantityResource = quantityResource;
    }

    public classes.Resource.ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(classes.Resource.ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    static void subtractResource(Resource resource) {
        int resourceQuantity = resource.getQuantityResource();
        if(resource.getResourceType() == ResourceType.Food) {
            for(Map.Entry<Integer, Animal> entry : animalsHash.entrySet()) {
                resourceQuantity -= totalFoodEaten(entry.getValue());
            }
        } else if(resource.getResourceType() == ResourceType.Meds) {
            for(Map.Entry<Integer, Animal> entry : animalsHash.entrySet()) {
                resourceQuantity -= totalMedicineTaken(entry.getValue());
            }
        }
        resource.setQuantityResource(resourceQuantity);
    }

    static void addResource(Resource resource, int quantity) {
        int resourceQuantity = resource.getQuantityResource() + quantity;
        resource.setQuantityResource(resourceQuantity);
    }

}
