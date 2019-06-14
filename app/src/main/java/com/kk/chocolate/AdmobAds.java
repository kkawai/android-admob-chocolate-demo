package com.kk.chocolate;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

class AdmobAds extends BaseAds implements RewardedVideoAdListener {

    private static final String TAG = "AdmobAds";

    private RewardedVideoAd rewardedVideoAd;
    private InterstitialAd interstitialAd;

    AdmobAds(Context context, TextView logView, ViewGroup inviewParent) {
        super(context, logView, inviewParent);
        MobileAds.initialize(context, Config.ADMOB_REWARDED_AD_UNIT_ID);
        MobileAds.initialize(context, Config.ADMOB_INTERSTITIAL_AD_UNIT_ID);
        MobileAds.initialize(context, Config.ADMOB_INVIEW_AD_UNIT_ID);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        rewardedVideoAd.setRewardedVideoAdListener(this);
        interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(Config.ADMOB_INTERSTITIAL_AD_UNIT_ID);
        interstitialAd.setAdListener(this.adListener);
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        log(TAG, "onRewardedVideoAdLoaded");
        handler.post(new Runnable() {
            @Override
            public void run() {
                rewardedVideoAd.show();
            }
        });
    }

    @Override
    public void onRewardedVideoAdOpened() {
        log(TAG, "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        log(TAG, "onRewardedVideoStarted");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        log(TAG, "onRewardedVideoAdClosed");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        log(TAG, "onRewarded");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        log(TAG, "onRewardedVideoAdLeftApplication");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        log(TAG, "onRewardedVideoAdFailedToLoad");
    }

    @Override
    public void onRewardedVideoCompleted() {
        log(TAG, "onRewardedVideoAdLoaded");
    }

    private AdListener adListener = new AdListener() {
        @Override
        public void onAdClosed() {
            log(TAG, "onAdClosed");
        }

        @Override
        public void onAdFailedToLoad(int i) {
            log(TAG, "onAdFailedToLoad");
        }

        @Override
        public void onAdLeftApplication() {
            log(TAG, "onAdLeftApplication");
        }

        @Override
        public void onAdOpened() {
            log(TAG, "onAdOpened");
        }

        @Override
        public void onAdLoaded() {
            log(TAG, "onAdLoaded");
            interstitialAd.show();
        }

        @Override
        public void onAdClicked() {
            log(TAG, "onAdClicked");
        }

        @Override
        public void onAdImpression() {
            log(TAG, "onAdImpression");
        }
    };

    void loadRewardedAd() {
        log(TAG, "loadRewardedAd");
        rewardedVideoAd.setRewardedVideoAdListener(this);
        rewardedVideoAd.loadAd(Config.ADMOB_REWARDED_AD_UNIT_ID, new AdRequest.Builder().build());
    }

    void loadInterstitialAd() {
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }

    void loadInviewAd() {
        log(TAG, "onRewardedVideoAdLoaded");
    }

}
