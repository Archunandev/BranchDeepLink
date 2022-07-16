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
[***Sign Up***](https://dashboard.branch.io/) the branch dashboard and create a app

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
    #Step 1 : Manifest file add the below code in launcher activity.
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

    #Step 2: Applicationn class add the below code
        > override fun onCreate() {
        super.onCreate()
        val branch = Branch.getAutoInstance(this)
        Branch.enableTestMode()
    }

    #Step 3: Launcher activity add the below code inn start method
        > override fun onStart() {
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


        





