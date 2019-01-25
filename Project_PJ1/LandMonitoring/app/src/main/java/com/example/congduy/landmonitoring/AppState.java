package com.example.congduy.landmonitoring;

import android.util.Log;

import com.paditech.core.helper.PrefUtils;
import com.paditech.core.helper.StringUtil;

import static com.paditech.core.common.BaseConstant.EMPTY;

public class AppState implements AppConstant {

    private static AppState instance;
    private String currentUser = EMPTY;
    private boolean isSigned = false;

    static synchronized AppState getInstance() {
        if (instance == null) {
            instance = new AppState();
        }
        return instance;
    }

    private AppState() {
    }

    public boolean isLogin() {
        return !StringUtil.isEmpty(currentUser);
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public void updateLoginUser(String user) {
        this.currentUser = user;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = EMPTY;
        this.isSigned = false;
        PrefUtils.getPreferences(MainApplication.getAppContext()).edit().clear().apply();
    }

    protected void saveToCache() {
        try {
            PrefUtils.savePreferences(MainApplication.getAppContext(), PREF_USER, currentUser);
            PrefUtils.savePreferences(MainApplication.getAppContext(), PREF_SIGNED, isSigned);
        } catch (Exception e) {
        }
    }

    public void loadFromCache() {
        try {
            currentUser = PrefUtils.getPreferences(MainApplication.getAppContext(), PREF_USER, EMPTY);
            isSigned = PrefUtils.getPreferences(MainApplication.getAppContext(), PREF_SIGNED);
            Log.i("Cache", "Load from cache success");
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Cache", "Load from cache failure");
        }
    }

    public void saveHistories(String histories) {
        PrefUtils.savePreferences(MainApplication.getAppContext(), PREF_HISTORY, histories);
    }

//    public List<History> getHistories() {
//        String json = PrefUtils.getPreferences(MainApplication.getAppContext(), PREF_HISTORY, EMPTY);
//        List<History> results = new Gson().fromJson(json, new TypeToken<List<History>>() {}.getType());
//        return results != null ? results : new ArrayList<History>();
//    }
//
//    public List<Profile> getProfiles() {
//        String profileJson = PrefUtils.getPreferences(MainApplication.getAppContext(), PREF_PROFILES, EMPTY);
//        List<Profile> results = new Gson().fromJson(profileJson, new TypeToken<List<Profile>>() {}.getType());
//        return results != null ? results : new ArrayList<Profile>();
//    }
//
//    public void addProfile(Profile profile) {
//        if (profile == null) return;
//        List<Profile> storeList = getProfiles();
//        storeList.add(profile);
//        String profilesSave = new Gson().toJson(storeList);
//        PrefUtils.savePreferences(MainApplication.getAppContext(), PREF_PROFILES, profilesSave);
//    }
//
//
//    public void editProfile(Profile profile, int position) {
//        List<Profile> storeList = getProfiles();
//        if (profile == null || position < 0 || position > storeList.size()) return;
//        Profile editProfile = storeList.get(position);
//        editProfile.setName(profile.getName());
//        editProfile.setBackPath(profile.getBackPath());
//        editProfile.setFontPath(profile.getFontPath());
//        editProfile.setProfilePath(profile.getProfilePath());
//        editProfile.setSignPath(profile.getSignPath());
//        String profilesSave = new Gson().toJson(storeList);
//        PrefUtils.savePreferences(MainApplication.getAppContext(), PREF_PROFILES, profilesSave);
//    }
//
//    public void saveType(String types){
//        PrefUtils.savePreferences(MainApplication.getAppContext(),PREF_LIST_TYPE,types);
//
//    }
//    public List<Type> getTypes() {
//        String profileJson = PrefUtils.getPreferences(MainApplication.getAppContext(), PREF_LIST_TYPE, EMPTY);
//        List<Type> results = new Gson().fromJson(profileJson, new TypeToken<List<Type>>() {}.getType());
//        return results != null ? results : new ArrayList<Type>();
//    }

}
