package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class VetClinic {

        Animal animal;
        private int year = 2020; //current Date met.
        private ArrayList<String> sickAnimalList = new ArrayList<String>();
        private ArrayList<String> specialCare = new ArrayList<String>();
        int totalSickAnimal;
        int totalSpecialCare;

    public VetClinic(Animal animal, int year, int totalSickAnimal, int totalSpecialCare, HashMap<String, Integer> sickAnimalsIndex) {
        this.animal = animal;
        this.year = year;
        this.totalSickAnimal = totalSickAnimal;
        this.totalSpecialCare = totalSpecialCare;
        this.sickAnimalsIndex = sickAnimalsIndex;
    }

    public int amountOfSickAnimal() {
            return sickAnimalList.size();
        }

        public HashMap<String, Integer> sickAnimalsIndex = new HashMap<String, Integer>(); {
            return sickAnimalsIndex;

    }
        public int amountOfSpecialCare() {
            return specialCare.size();

        }

    }


