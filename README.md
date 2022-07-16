# BranchDeepLink
Sample guide for integrating Branch deeplink in kotlin language.

## How to Start?
    You can add the branch deeplink in two options
        1.Using Branch SDK
                or
        2.Using Branch Api(Now in development)

## 1.Branch SDK Integration Guide
    SDK Size: 187kb
    Speed: Median 80ms to 250ms
    Minimum OS Version: API Level 21+
    //Add when you using SDK.(Required)

    dependencies { implementation 'io.branch.sdk.android:library:5.+'(Now in 5.2.0) }
[***Check New Version***](https://help.branch.io/developers-hub/docs/android-version-history)

## 1.1 Branch Dashboard Setup
[***Sign Up***](https://dashboard.branch.io/) the branch dashboard

    a.Click Configuration page to setup the android redirects.
        > Uri Sheme (https://)
        > when your app published on playstore add the link othervice add custom url.
        > Declare package name
        > Need SHA256 Cert Fingerprints(generate it (gradle>app>signinreport))
        > Enable app link

    b. Click Account Settings
        > You can get the branch live and test key
        > 

    b.You can check the integration status and new version update.
        > This is show the progress of our setup process.
        > When you integerate check the ststus.






