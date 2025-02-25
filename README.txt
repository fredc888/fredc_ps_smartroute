Platform Sciences Project 


Summary

(Full disclosure: I’m not an expert at Assignment Problems, and I learned most about it the past few days). This problem appears to be an “Assignment Problem”, with a slight variation. 

The “weights” of the driver:destination, is part of the company’s algorithm to determine. But once it’s determined, there are several algorithms available to solve the Assignment Problem. 
* The Hungarian Algorithm is very popular: O(n^3)
* Successive Shortest Path Algorithm: O(n^3logn)
* Google OR-Tools (even more optimized?)

The above is normally used to minimize a cost matrix. In this problem’s case, the goal is to maximize the cost matrix.  I multiplied the shipping score matrix from this project’s algorithm by -1 to turn this project’s maximize cost problem into  into a minimize cost problem that the algorithms can solve. An alternative approach could have been to first find the largest element in the cost matrix, and subtract every other element from it. But if this was a very large matrix, this might be time consuming..


Regarding the algorithm for the shipping score, I significantly simplified when ‘y’ is considered a vowel. Unfortunately, there are many corner cases when ‘y’ is a vowel, but it does not easily fit into a general rule. 

For the purpose of this assigned, my code considers ‘y’ is vowel when it occurs last in a word:
* ‘y’ occurs at the end of the string.
* ‘y’ occurs right before a blankspace character
But this is far from foolproof. I also included a compile time flag that allows one to disable considering ‘y’ as a vowel completely


I created a copy of the shipmentinfo.json file and put it into the app’s “asset/” folder as a default file that is loaded by the app at startup. In addition, I created a toolbar button to allow one to load a shippinginfo.json file over the internet. The pre-filled URL is the sample json file I checked into my git repository 
"https://raw.githubusercontent.com/fredc888/fredc_ps_smartroute/refs/heads/main/SmartRoute/samplejson/shipmentinfo.json"


Finally, in the app toolbar, one can toggle developer mode on/off to show or hide the shipment score of each driver’s optimal assigned shipment.










Source code and App Location and Build Instructions


SmartShipper is an app made with Android Studio project using Android Studio 2024.2.1 (Ladybug)




Source Location and Download :

git clone https://github.com/fredc888/fredc_ps_smartroute


Inside the ‘fredc_ps_smartroute’/


SmartRoute/ is the top level folder of the Android Project
releases_unsigned/ contains a prebuilt apk :  smartshipper_unsigned.apk


Prebuilt App Installation

To install, you can sideload the app with ADB

adb install -r smartshipper_unsigned.apk

OR

You can download the apk from your android device, , and open it up it from the file manager, which give a prompt to allow you to install the apk. (You might need to disable app signing check on the device first).


Building the App:

The app can be built and deployed inside Android Studio as a project.

OR


You can use gradle 8.9 included in the project to build it from command line.:

1. Set ANDROID_HOME environment variable to SDK root directory
2. Got to directory  fredc_ps_smartroute/SmartRoute
3. ./gradew assembleDebug
4. The built apk will be located in fredc_ps_smartroute/SmartRoute/app/build/outputs/apk/debug