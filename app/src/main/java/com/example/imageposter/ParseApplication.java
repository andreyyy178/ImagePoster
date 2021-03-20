package com.example.imageposter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

//this class needs to be referenced in AndroidManifest same as LoginActivity etc
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        // Register your parse models
        ParseObject.registerSubclass(Post.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("GrwHoOi5RPTxRDCAmb6a7XUV6FhW54Z0u9pzc43U")
                .clientKey("YE4fEOVIuuIXkahePdwhB1jq0t3Y5SIRUCdXKKA8")
                .server("https://parseapi.back4app.com")
                .build()
        );


    }
}
