### Before writing your first code, make sure to install following plugins in your IDE and make required changes in editor settings as below:

### 1) Detekt

Moj android app uses [detekt](https://github.com/detekt/detekt) for lint and static code analysis. make sure you have plugin installed so that you can see the errors reported by the plugin in real time. More regarding lint on wiki page.

### How to setup detekt:

1) Install ***detekt*** plugin from Android Studio Preferences -> Plugins -> Marketplace -> search for ***detekt***
2) Once installed, restart android studio, navigate Android Studio Preferences -> tools -> detekt and enable  the **enable detekt**, **enable formatting (ktlint) rules**, **treat detekt findings as errors** options.
3) In *Configuration Files*, put the following path (modify the initial path as per your username) ***/Users/username/StudioProjects/cloned-directory-name/static-analysis/detekt-config.yml***
4) In *Baseline File*, put the following path (modify the path as per your username)
***/Users/username/StudioProjects/cloned-directory-name/static-analysis/combined-detekt-baseline.xml***
5) Apply and save


### 2) Setting the correct Java version

Moj App is using [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependecy injection which requires gradle JDK to be **11**. Make sure it is set as 11 in the Android studio settings

### How to check and update JDK version

1) Navigate Android Studio Preferences -> Build, Execution, Deployment -> Build tools -> Gradle.
2) If Gradle JDK is set to 11, ignore step 3 and 4
3) If Gradle JDK is not set to 11, click on the dropdown and select **11 version**
4) Click on Apply and save