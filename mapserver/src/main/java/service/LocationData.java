package service;

/**
 * Created by jacob on 3/6/2017.
 */
class LocationData{
    Location[] data;
    //public Location[] getData(){return data;}
    public void print(){
        for(Location loc : data) {
            System.out.println(loc.city);
            System.out.println(loc.country);
            System.out.println(loc.latitude);
            System.out.println(loc.longitude);
        }

    }
}