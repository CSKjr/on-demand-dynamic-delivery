# on-demand-dynamic-delivery

//START

At initial stage used google reference to learn Dynamic Delivery 
Link : https://developer.android.com/codelabs/on-demand-dynamic-delivery#0

Here I've created two modules 
1. OnInstall
2. OnDemand

OnInstall is installed when the app is installed.
OnDemand is installed when it requires. 

Added Play core library in the gradle 

    implementation 'com.google.android.play:core:1.10.3'

we have to implement the play core library in main gradle file and also in OnDemand Gradle file.

I've used one Simple and small toast library which i've created.

To use that :

 Step 1. Add the JitPack repository to your build file Add the following in your root build.gradle at the end of repositories:

          allprojects { repositories { ... maven { url 'https://jitpack.io' } } }

 Step 2. Add the dependency

          dependencies { implementation 'com.github.CSKjr:Simple-Kotlin-Toast-Library:Tag' }
  
 Step 3 : Implementation
 
          ToasterLibrary.useToastAnywhere(this, "Your Toast Message")

          
For testing we can use two ways: 

  Bundle Tools
  Internal app sharing
  
  Internal app sharing is best and it will give the real user experience. ( My personal Opinion )

Local testing also can be done using FakeSplitInstallManagerFactory, will do R&D about it and let you know guys.

As of now it is a simple and small code.
Will add more functionalities and UI updation in next releases.

//END

Thank you!
Enjoy Coding 💚
