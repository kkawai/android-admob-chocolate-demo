package com.kk.chocolate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.kk.chocolate.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private static final String TAG = "MainActivity";

    private static final String ADMOB_INTENTIONALLY_BAD_ID = "bad_admob_rewarded_ad_unit_id";
    private AdmobAds admobAds;
    private ChocolateAds chocolateAds;
    private RewardedVideoAd rewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        admobAds = new AdmobAds(this, binding.log, binding.inview);
        chocolateAds = new ChocolateAds(this, binding.log, binding.inview);

        //Initialize Admob
        MobileAds.initialize(this, Config.ADMOB_REWARDED_AD_UNIT_ID);
        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.setRewardedVideoAdListener(this);
    }

    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.load_chocolate_interstitial:
                chocolateAds.loadInterstitialAd();
                break;
            case R.id.load_chocolate_rewarded:
                chocolateAds.loadRewardedAd();
                break;
            case R.id.load_chocolate_inview:
                chocolateAds.loadInviewAd();
                break;
            case R.id.load_admob_with_fallback:
                rewardedVideoAd.setRewardedVideoAdListener(this);
                rewardedVideoAd.loadAd(Config.ADMOB_REWARDED_AD_UNIT_ID, new AdRequest.Builder().build());
                break;
            case R.id.load_admob_interstitial:
                admobAds.loadInterstitialAd();
                break;
            case R.id.load_admob_rewarded:
                admobAds.loadRewardedAd();
                break;
//            case R.id.load_admob_inview:
//                admobAds.loadInviewAd();
//                break;
            default:
                break;
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //PRETEND ADMOB DID NOT GET A FILL; CALL CHOCOLATE REWARDED
        Log.d(TAG, "onRewardedVideoAdLoaded");
        chocolateAds.loadRewardedAd();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        Log.d(TAG, "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoStarted() {
        Log.d(TAG, "onRewardedVideoStarted");
    }

    @Override
    public void onRewardedVideoAdClosed() {
        Log.d(TAG, "onRewardedVideoCompleted");
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        Log.d(TAG, "onRewardedVideoCompleted");
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Log.d(TAG, "onRewardedVideoCompleted");
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {
        //ADMOB FAILED TO GET FILL: LOAD CHOCOLATE REWARDED AD
        Log.d(TAG, "onRewardedVideoAdFailedToLoad");
        chocolateAds.loadRewardedAd();
    }

    @Override
    public void onRewardedVideoCompleted() {
        Log.d(TAG, "onRewardedVideoCompleted");
    }
}
