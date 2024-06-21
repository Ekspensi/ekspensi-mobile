# Ekspensi-Mobile
This repository contains Adnroid APP that consumes API from this [API](https://github.com/Ekspensi/ekspensi-backend)

## Introduction
Ekspensi is a money management app that have an auto classify expenditure user input feature that is based on Natural Language Processing

## APK Download Link
https://github.com/Ekspensi/ekspensi-mobile/releases

## Tech Used
Cultivitea android app is developed using:
- Kotlin
- Android Studio

## Steps to Replicate
To replicate Ekspensi setup and run the app using Android Studio, follow these steps:

### Prerequisite
- Android Studio
- Java Development Kit

### Dependencies
| Dependency                                                     | Description                                        |
|----------------------------------------------------------------|----------------------------------------------------|
| androidx.core:core-ktx:1.13.1                                  | Kotlin extensions for Android core libraries       |
| androidx.appcompat:appcompat:1.7.0                             | Support library for backward compatibility         |
| com.google.android.material:material:1.12.0                    | Material Design Components for Android             |
| androidx.activity:activity:1.9.0                               | Support library for Android activities             |
| androidx.constraintlayout:constraintlayout:2.1.4               | Layout library for creating complex UI             |
| androidx.navigation:navigation-fragment-ktx:2.6.0              | Navigation component fragment library for KTX      |
| androidx.navigation:navigation-ui-ktx:2.6.0                    | Navigation component UI library for KTX            |
| junit:junit:4.13.2                                             | Unit testing framework for Java                    |
| androidx.test.ext:junit:1.1.5                                  | AndroidX JUnit extension                           |
| androidx.test.espresso:espresso-core:3.5.1                     | AndroidX Espresso core                             |
| com.squareup.retrofit2:retrofit:2.9.0                          | HTTP client for Android and Java                   |
| com.squareup.retrofit2:converter-gson:2.9.0                    | Converter for JSON to Java objects using Gson      |
| com.squareup.okhttp3:logging-interceptor:4.11.0                | Logs HTTP request and response data                |
| androidx.lifecycle:lifecycle-runtime-ktx:2.6.1                 | Kotlin extensions for Android lifecycle            |
| androidx.datastore:datastore-preferences:1.0.0                 | DataStore implementation for storing preferences   |
| androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1               | Kotlin extensions for ViewModel lifecycle          |
| androidx.lifecycle:lifecycle-livedata-ktx:2.6.1                | Kotlin extensions for LiveData lifecycle           |
| androidx.activity:activity-ktx:1.9.0                           | Compose integration for Android activities         |
| androidx.paging:paging-runtime-ktx:3.1.0                       | Kotlin extensions for Paging library               |
| androidx.core:core-splashscreen:1.0.0                          | Splash screen API for Android                      |


#### 1. Clone this Project
`git clone https://github.com/Ekspensi/ekspensi-mobile.git`

#### 2. Open the Project in Android Studio
> 1. Open Android Studio.
> 2. Click on File > Open.
> 3. Navigate to the cloned project directory and select it.
> 4. Click OK to open the project.

#### 3. Set Up the Project
> 1. Ensure you have the required SDK and build tools installed. Android Studio should prompt you to install any missing dependencies.
> 2. Sync the project with Gradle files by clicking on the Sync Project with Gradle Files button.

#### 4. Build the Project
> Once the project is synced, build the project by clicking on Build > Make Project or using the Ctrl + F9 shortcut.

#### 5. Run the App on an Emulator or Device
- To run the app on an Android emulator:
> 1. Open the AVD Manager from the toolbar.
> 2. Create a new virtual device if you don't have one already.
> 3. Start the emulator.

- To run the app on a physical device:
> 1. Connect your Android device to your computer using a USB cable.
> 2. Ensure USB debugging is enabled on your device.
> 3. Click on the Run button or use the Shift + F10 shortcut to run the app.

#### 6. Grant Permissions
> If prompted, grant any necessary permissions for the app to function correctly, such as camera and storage permissions.
