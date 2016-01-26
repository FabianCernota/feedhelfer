package de.feedhelfer.app.facebook;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;

/**
 * Created by cernota on 24.12.15.
 */
public class FacebookWrapper {
    public FacebookWrapper(){
        try {
            Facebook facebook = new FacebookFactory().getInstance();

            facebook.setOAuthAppId("181963208825075", "d5c8b11495890683a93dd083016635c5");
            facebook.getAuthorization();
            System.out.println(facebook.getAccounts().toString());

        }catch (FacebookException e){
            e.printStackTrace();
        }

    }
}
