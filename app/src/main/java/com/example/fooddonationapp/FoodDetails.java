package com.example.fooddonationapp;

public class FoodDetails {


        // variables for storing our data.
        private String foodItem, NoPackets, TimeDuration,Datelimit,Uid;

        public FoodDetails() {
            // empty constructor
            // required for Firebase.
        }

        // Constructor for all variables.
        public FoodDetails(String fdItem, String noPackets, String timeDuration,String datelimit,String Uid) {
            this.foodItem = fdItem;
            this.NoPackets = noPackets;
            this.TimeDuration = timeDuration;
            this.Uid=Uid;
            this.Datelimit=datelimit;
        }

        // getter methods for all variables.
        public String getFoodItem() {
            return foodItem;
        }

        public void setFoodItem(String foodItem) {
            this.foodItem = foodItem;
        }

        public String getNoPackets() {
            return NoPackets;
        }

        // setter method for all variables.
        public void setNoPackets(String noPackets) {
            this.NoPackets = noPackets;
        }

        public String getTimeDuration() {
            return TimeDuration;
        }

        public void setTimeDuration(String timeDuration) {
            this.TimeDuration = timeDuration;
        }
    public String getUid() {
        return Uid;
    }

    public void setUid(String datelimit) {
        this.Uid = Uid;
    }

    public String getDatelimit() {
        return Datelimit;
    }

    public void setDatelimit(String datelimit) {
        this.Datelimit = datelimit;
    }


}
