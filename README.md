# Dineout
A restaurants finder android app illustrating the building of a good, modular and scalable Android app using Kotlin, Android Architecture Components (LiveData, ViewModel & Room), Dagger, RxJava and RxAndroid among others.

# Features
Some of the features of the app include
- **Near by Restaurants** - Displaying the nearest 50 restaurants to any location using Foursquare's API
[Developer Guide](https://developer.foursquare.com/docs/api-reference/venues/explore/) at [Developer Site] (https://developer.foursquare.com).

# Best Practices
- **Kotlin** - This app is completely written in Kotlin.
- **Android Architecture Components** - Lifecycle awareness has been achieved using a combination of LiveData and ViewModels.
- **MVVM Architecture** - Using the lifecycle aware ViewModels, the view observes changes in the model / repository.
- **Modular** - The app is broken into modules of features and libraries.
- **Dependency Injection** - Common elements like `context`, `networking` interface are injected using Dagger 2.
- **Effective Networking** - Using a combination of Retrofit, Rx and LiveData, we are able to handle networking in the most effective way. 

# Libraries used
* [Dagger 2](https://google.github.io/dagger/)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)

# Build information:
  - Android Studio - 3.6.3
  - Min SDK - 19 
  - Compile SDK - 29
  - Target SDK - 29
  
# Reference
* [Writing a modular project on Android](https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37)

# License

    MIT License
    
    Copyright (c) 2020 Vishal Kumar
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.