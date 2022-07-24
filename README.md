# BranchDeepLink <img src="https://user-images.githubusercontent.com/56442417/180644117-c35cc232-7aee-4736-88c7-9c1b317d0850.png" width="50" height="50" />
Sample guide for integrating Branch deeplink in kotlin language.
 <img src="https://user-images.githubusercontent.com/56442417/180644569-3e79ae4d-0345-4046-a7b4-40ff75bcbe27.png" width="100" height="50" />

    You can add the branch deeplink in two options
    
        1.Using Branch SDK
               OR
        2.Using Branch Api(Funtional api in development)

## 1.Branch SDK Integration Guide
    SDK Size: 187kb
    Speed: Median 80ms to 250ms
    Minimum OS Version: API Level 21+
    //Add when you using SDK.(Required)
```kotlin
    dependencies { implementation 'io.branch.sdk.android:library:5.+'(Now in 5.2.0) }
```
[***Check New Version***](https://help.branch.io/developers-hub/docs/android-version-history)

## 1.1 Branch Dashboard Setup
[***Sign Up***](https://dashboard.branch.io/) the branch dashboard and create a app.

a.Click Configuration page to setup the android redirects.

        > Uri Sheme (https://)
        > when your app published on playstore add the link othervice add custom url.
        > Declare package name
        > Need SHA256 Cert Fingerprints(generate it (gradle>app>signinreport))
        > Enable app link
        > Link Domain option -- you shound customize the link(this is your manifest host name)

b.Click Account Settings

        > You can get the branch live and test key

c.Click Integration Status

        > New version update
        > This is show the progress of our setup process.
        > When you integerate check the ststus.

## 1.2 Initialize Branch
> Step 1 : Manifest file add the below code in launcher activity.
```XML
    <activity 
        android:name=".ui.activity.DashboardActivity"
        android:exported="true">
            <intent-filter>
                <data android:host="open" android:scheme="samplebranchdeeplink" />
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
            </intent-filter>
    </activity>

        <meta-data android:name="io.branch.sdk.BranchKey" android:value="key_live_kaFuWw8WvY7yn1d9yYiP8gokwqjV0Sw" />
        <meta-data android:name="io.branch.sdk.BranchKey.test" android:value="key_test_hlxrWC5Zx16DkYmWu4AHiimdqugRYMr" />
        <meta-data android:name="io.branch.sdk.TestMode" android:value="false" />

        </application>

        <queries>
        <intent>
            <action android:name="android.intent.action.SEND" />
            <data android:mimeType="text/plain" />
        </intent>
    </queries>
```

> Step 2: Application class add the below code

```kotlin
        @override fun onCreate() {
        super.onCreate()
        val branch = Branch.getAutoInstance(this)
        Branch.enableTestMode()
        }
```

> Step 3: Launcher activity add the below code in onstart method
```kotlin
        @override fun onStart() {
        super.onStart()
        Branch.sessionBuilder(this).withCallback(object : Branch.BranchReferralInitListener {
            override fun onInitFinished(referringParams: JSONObject?, error: BranchError?) {
                if (error == null) {
                    //do stuff
                } else {
                    Log.e("BRANCH SDK", error.message)
                }
            }
        }).withData(this.intent.data).init()
    }
```
> Now we succesfully initialized the branch SDK also check the integration status

## 1.3 Create DeepLink
> Branch UniversalObject hold the some data. so we can initialize it anywere and call the object.
```kotlin
        val buo = BranchUniversalObject()
        .setCanonicalIdentifier("content/12345")
        .setTitle("My Content Title")
        .setContentDescription("My Content Description")
        .setContentImageUrl("https://lorempixel.com/400/400")
        .setContentIndexingMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
        .setLocalIndexMode(BranchUniversalObject.CONTENT_INDEX_MODE.PUBLIC)
        .setContentMetadata(ContentMetadata().addCustomMetadata("key1", "value1"))
```
> LinkProperties we customize the link.
```kotlin
        val lp = LinkProperties()
        .setChannel("facebook")
        .setFeature("sharing")
        .setCampaign("content 123 launch")
        .setStage("new user")
        .addControlParameter("$desktop_url", "http://example.com/home")
        .addControlParameter("custom", "data")
        .addControlParameter("custom_random", Long.toString(Calendar.getInstance().getTimeInMillis()))
```

> Branch provide the dialog sheet
```kotlin
          val ss = ShareSheetStyle(this@MainActivity, "Check this out!", "This stuff is awesome: ")
        .addPreferredSharingOption(SharingHelper.SHARE_WITH.FACEBOOK)
        .addPreferredSharingOption(SharingHelper.SHARE_WITH.EMAIL)
        .addPreferredSharingOption(SharingHelper.SHARE_WITH.MESSAGE)
        .addPreferredSharingOption(SharingHelper.SHARE_WITH.HANGOUT)
        .setAsFullWidthStyle(true)
        .setSharingTitle("Share With")

        buo.showShareSheet(this, lp, ss, object : Branch.BranchLinkShareListener {
        override fun onShareLinkDialogLaunched() {}
        override fun onShareLinkDialogDismissed() {}
        override fun onLinkShareResponse(sharedLink: String?, sharedChannel: String?, error: BranchError?) {}
        override fun onChannelSelected(channelName: String) {}
         })
```

        





