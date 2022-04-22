# Movies

This is a sample test application written as an answer to a test given to me.  
**This application is by no means a production-grade application. There {can be/are} a number of edge cases which are not covered due to lack of explicit instruction**

## Requirements

Following are the requirements stated in the test

```
Specs

Create a view that contains an infinite scroll list with the most popular tv shows.
Use the following endpoint
Each item of the list should contain an image, the tv show title and the vote average fields.
The list should paginate.
If a list item is clicked, it should load the tv show data in a detail view. This should contain: A
big hero image, the title, the overview... (you can get that info exploring the provided api)
Once in the detail view, the user should be able to navigate between similar tv shows by
swapping horizontally. The first item will be the one that the user has clicked.
Then it will load the related tv shows and the user will be able to navigate using swipe to left
or right.

Rules
Create a commit history that makes sense.
The final code to evaluate must be in the master branch.
```

This project deviates from the last rule for containing code in `master` branch and uses `main` branch instead.
The gist of this deviation can be obtained from [this](https://www.theserverside.com/feature/Why-GitHub-renamed-its-master-branch-to-main) article.

The application assumes the following conditions due to lack of explicit instruction from the given rules:

- A working Internet connection is always available,
- Writing tests is not required,
- The API doesn't returns null data in certain conditions,
- Caching data in persistent storage is not required,
- Device running this application is >= Android 8.1 (Oreo, covers approx 78% devices),
- API key required to compile the application is provided by the party compiling the application

## Libraries & Practices Used

This application uses:

- Retrofit2 and Moshi to communicate with Network for making API calls,
- Paging3 for paging support,
- Dagger-Hilt for dependency injection,
- Coil for loading images async,
- Navigation Components for single activity app architecture,
- Ktlint for linting kotlin specific code,
- LeakCanary to detect memory leaks,
- Lifecycle Components for running coroutines and creating ViewModel,
- [Package by Feature](https://phauer.com/2020/package-by-feature/) for packaging code,

## Build Instructions

In order to build and test this application, kindly set an environment variable `TMDB_API_KEY` which should contain your V3 API key generated from TMDB website.

## Release

The pre-release present in this repository is compiled with my personal `TMDB_API_KEY` and has been tested to work on Android Emulator (Pixel 4 API 32) and Moto G60 (Android 10).