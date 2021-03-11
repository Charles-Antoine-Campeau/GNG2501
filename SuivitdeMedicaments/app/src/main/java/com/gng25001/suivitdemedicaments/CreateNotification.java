package com.gng25001.suivitdemedicaments;

import androidx.core.app.NotificationCompat;

public class CreateNotification {

    //**********************************************************************************************
    //VARIABLES
    private static CreateNotification instance;
    //END OF VARIABLES
    //**********************************************************************************************

    //**********************************************************************************************
    //CONSTRUCTOR

    /**
     * Constructor of the class
     */
    private CreateNotification() {
        instance = this;
    }
    //END OF CONSTRUCTOR
    //**********************************************************************************************

    //**********************************************************************************************
    //PUBLIC METHODS
    /**
     * Get the singleton instance of the class
     * @return the instance of the class
     */
    public CreateNotification getInstance() {
        if (instance == null) {
            new CreateNotification();
        }
        return instance;
    }
    //END OF PUBLIC METHODS
    //**********************************************************************************************

    //**********************************************************************************************
    //PRIVATE METHODS
    
}
