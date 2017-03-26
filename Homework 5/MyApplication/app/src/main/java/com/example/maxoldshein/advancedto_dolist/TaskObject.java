package com.example.maxoldshein.advancedto_dolist;

import java.io.Serializable;

/**
 * Created by maxoldshein on 3/25/17.
 */

public class TaskObject implements Serializable {
    String shortDescription;
    String longDescription;
    boolean isComplete;

    public TaskObject(String shortDescription, String longDescription) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.isComplete = false;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescripton(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public boolean getIsComplete() {
        return this.isComplete;
    }
}
