package com.kk.chocolate;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyAppOptions;
import com.amazon.device.ads.AdRegistration;
import com.vdopia.ads.lw.Chocolate;
import com.vdopia.ads.lw.InitCallback;
import com.vdopia.ads.lw.LVDOAdRequest;
import com.vdopia.ads.lw.LVDOAdSize;
import com.vdopia.ads.lw.LVDOBannerAd;
import com.vdopia.ads.lw.LVDOBannerAdListener;
import com.vdopia.ads.lw.LVDOConstants;
import com.vdopia.ads.lw.LVDOInterstitialAd;
import com.vdopia.ads.lw.LVDOInterstitialListener;
import com.vdopia.ads.lw.LVDORewardedAd;
import com.vdopia.ads.lw.RewardedAdListener;
import com.vdopia.ads.lw.VdopiaLogger;

class ChocolateAds extends BaseAds implements RewardedAdListener, LVDOBannerAdListener, LVDOInterstitialListener {

    private static final String TAG = "ChocolateDemo";

    private LVDORewardedAd rewardedAd;
    private LVDOInterstitialAd interstitialAd;
    private LVDOAdRequest adRequest;
    private LVDOBannerAd bannerAd;

    ChocolateAds(Activity activity, TextView logView, ViewGroup inviewParent) {
        super(activity, logView, inviewParent);
        adRequest = new LVDOAdRequest(context);
        VdopiaLogger.enable(true);
        AdColonyAppOptions adColonyAppOptions = new AdColonyAppOptions();
        adColonyAppOptions.setTestModeEnabled(true);
        AdColony.setAppOptions(adColonyAppOptions); //AdColony test mode
        Chocolate.enableChocolateTestAds(true);
        AdRegistration.enableTesting(true); //amazon test mode

        LVDOAdRequest adRequest = new LVDOAdRequest(context);
        //Please use your company and app values
        adRequest.setAppStoreUrl("https://play.google.com/store/apps/details?id=com.democompany.android");
        adRequest.setRequester("Chocolate Demo Company");
        adRequest.setAppDomain("http://democompany.com/test");
        adRequest.setAppName("Chocolate Demo");
        adRequest.setCategory("IAB2");
        adRequest.setPublisherDomain("http://democompany.com/");

        Chocolate.init((Activity)context, Config.CHOCOLATE_API_KEY, adRequest, new InitCallback() {
            @Override
            public void onSuccess() {
                log(TAG,"Chocolate initialized");
            }

            @Override
            public void onError(String msg) {
                log(TAG, "Chocolate not initialized");
            }
        });
    }

    void loadInviewAd() {
        ChocolatePartners.choosePartners(ChocolatePartners.INVIEW, context, (dialog, which) -> {
            ChocolatePartners.setInviewPartners(adRequest);
            if (bannerAd == null) {
                bannerAd = new LVDOBannerAd(context, LVDOAdSize.IAB_MRECT, Config.CHOCOLATE_API_KEY,ChocolateAds.this);
            }
            bannerAd.loadAd(adRequest);
        });
    }

    void loadRewardedAd() {
        ChocolatePartners.choosePartners(ChocolatePartners.REWARDED, context, (dialog, which) -> {
            ChocolatePartners.setRewardedPartners(adRequest);
            if (rewardedAd == null) {
                rewardedAd = new LVDORewardedAd(context, Config.CHOCOLATE_API_KEY, this);
            }
            if (rewardedAd.isReady()) {
                rewardedAd.showRewardAd("mysecret", "myuserid", "coins", "30");
            } else {
                rewardedAd.loadAd(adRequest);
            }
        });
    }

    void loadInterstitialAd() {
        if (interstitialAd == null) {
            interstitialAd = new LVDOInterstitialAd(context, Config.CHOCOLATE_API_KEY, this);
        }
        interstitialAd.loadAd(adRequest);
    }

    @Override
    public void onRewardedVideoLoaded(LVDORewardedAd lvdoRewardedAd) {
        handler.post(()-> rewardedAd.showRewardAd("mysecret", "myuserid", "coins", "30"));
    }

    @Override
    public void onRewardedVideoFailed(LVDORewardedAd lvdoRewardedAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        log(TAG, "onRewardedVideoFailed. errorCode: " + lvdoErrorCode);
        toast("Rewarded Ad: " + lvdoErrorCode);
    }

    @Override
    public void onRewardedVideoShown(LVDORewardedAd lvdoRewardedAd) {
        log(TAG, "onRewardedVideoShown.  winner: " + lvdoRewardedAd.getWinningPartnerName());
    }

    @Override
    public void onRewardedVideoShownError(LVDORewardedAd lvdoRewardedAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        log(TAG, "onRewardedVideoShownError. " + lvdoRewardedAd.getWinningPartnerName() + " error: " + lvdoErrorCode);
    }

    @Override
    public void onRewardedVideoDismissed(LVDORewardedAd lvdoRewardedAd) {
        log(TAG, "onRewardedVideoDismissed. winner: " + lvdoRewardedAd.getWinningPartnerName());
    }

    @Override
    public void onRewardedVideoCompleted(LVDORewardedAd lvdoRewardedAd) {
        log(TAG, "onRewardedVideoCompleted. winner: " + lvdoRewardedAd.getWinningPartnerName());
    }

    @Override
    public void onBannerAdLoaded(View view) {
        log(TAG, "onBannerAdLoaded. winner: " + bannerAd.getWinningPartnerName());
        inviewParent.removeAllViews();
        inviewParent.addView(view);
    }

    @Override
    public void onBannerAdFailed(View view, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        log(TAG, "onBannerAdLoaded. error: "+lvdoErrorCode);
    }

    @Override
    public void onBannerAdClicked(View view) {
        log(TAG, "onBannerAdClicked. winner: " + bannerAd.getWinningPartnerName());
    }

    @Override
    public void onBannerAdClosed(View view) {
        log(TAG, "onBannerAdClosed. winner: " + bannerAd.getWinningPartnerName());
    }

    @Override
    public void onInterstitialLoaded(LVDOInterstitialAd lvdoInterstitialAd) {
        interstitialAd.show();
    }

    @Override
    public void onInterstitialFailed(LVDOInterstitialAd lvdoInterstitialAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {

    }

    @Override
    public void onInterstitialShown(LVDOInterstitialAd lvdoInterstitialAd) {

    }

    @Override
    public void onInterstitialClicked(LVDOInterstitialAd lvdoInterstitialAd) {

    }

    @Override
    public void onInterstitialDismissed(LVDOInterstitialAd lvdoInterstitialAd) {

    }
}
