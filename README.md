# RetrofitSynchronizedCalling
After Finished Retrofit Call Next Line will be exicuted

Instruction:
---------------
`
1. At First add your APIKey in gradle.properties
2. build.gradle: buildConfigField("String", "API_KEY", API_KEY) // add This Line Only

android {
    compileSdkVersion 28
    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 15
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "API_KEY", API_KEY) // add This Line 
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

3. Add Your server_ip_address here: res/xml/network_security_config.xml

4. Add api_link_without_base_url in GetdataService.class

5. Add BASE_URL in RetrofitClientInstance.class

`
Now Build The Application.
