package classes;

import java.util.ArrayList;


 public class AnimalEnclosure {
        static ArrayList<String> AnimalEnclosure = new ArrayList<String>();
        boolean isClean;
        boolean isEnriched;
        boolean needsModified;

     public AnimalEnclosure(boolean isClean, boolean isEnriched, boolean needsModified) {
         this.isClean = isClean;
         this.isEnriched = isEnriched;
         this.needsModified = needsModified;
     }

     public boolean isClean() {
         return isClean;
     }

     public void setClean(boolean clean) {
         isClean = clean;
     }

     public boolean isEnriched() {
         return isEnriched;
     }

     public void setEnriched(boolean enriched) {
         isEnriched = enriched;
     }

     public boolean isNeedsModified() {
         return needsModified;
     }

     public void setNeedsModified(boolean needsModified) {
         this.needsModified = needsModified;
     }

     public ArrayList<String> getAnimalEnclosure() {
         return AnimalEnclosure;
     }
 }

