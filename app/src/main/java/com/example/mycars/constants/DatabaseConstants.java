package com.example.mycars.constants;

public class DatabaseConstants {

    private DatabaseConstants() {}

    public static String VEHICLEID  = "vehicleId";

    public static class VEHICLES {
        public static final String TABLE_MODEL = "Vehicles";

        public static class COLUMNS {
            public static final String ID = "id";
            public static final String NAME = "name";
            public static final String YEAR = "year";
            public static final String PRICE = "price";
            public static final String IMAGE = "image";
        }
    }
}
