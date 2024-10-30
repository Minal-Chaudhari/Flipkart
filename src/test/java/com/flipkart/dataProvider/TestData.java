package com.flipkart.dataProvider;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "productDataProvider")
    public static Object[][] productDataProvider() {
        return new Object[][]{
                {"SAMSUNG Galaxy A15 5G (Blue, 128 GB)  (8 GB RAM)"},
                {"POCO M3 (Cool Blue, 64 GB)  (4 GB RAM)"},
                {"Redmi Note 10 (Shadow Black, 128 GB)  (6 GB RAM)"}
                //add more
        };
    }

}
