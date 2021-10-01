package com.example.task.Models;

public class OnBoardingModel {
    public int onBoardingImage;
    public String onBoardingHeading;

    public OnBoardingModel(int onBoardingImage, String onBoardingHeading) {
        this.onBoardingImage = onBoardingImage;
        this.onBoardingHeading = onBoardingHeading;
    }

    public int getOnBoardingImage() {
        return onBoardingImage;
    }

    public void setOnBoardingImage(int onBoardingImage) {
        this.onBoardingImage = onBoardingImage;
    }

    public String getOnBoardingHeading() {
        return onBoardingHeading;
    }

    public void setOnBoardingHeading(String onBoardingHeading) {
        this.onBoardingHeading = onBoardingHeading;
    }
}
