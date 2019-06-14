package com.kk.chocolate;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.vdopia.ads.lw.LVDOAdRequest;
import com.vdopia.ads.lw.LVDOConstants;

import java.util.ArrayList;
import java.util.List;

public class ChocolatePartners {

    public static final int REWARDED = 1;
    public static final int INTERSTITIAL = 0;
    public static final int INVIEW = 2;
    public static final int PREVIEW = 3;

    private static int numInterstitial = 12;
    private static int numRewarded = 12;
    private static int numInview = 10;
    private static int numPreroll = 2;


    /**
     * INTERSTITIAL
     */
    private static String[] interstitial_partners = new String[numInterstitial];

    static {
        interstitial_partners[0] = LVDOConstants.PARTNER.AMAZON.name();
        interstitial_partners[1] = LVDOConstants.PARTNER.ADCOLONY.name();
        interstitial_partners[2] = LVDOConstants.PARTNER.APPLOVIN.name();
        interstitial_partners[3] = LVDOConstants.PARTNER.CRITEO.name();
        interstitial_partners[4] = LVDOConstants.PARTNER.CHOCOLATE.name();
        interstitial_partners[5] = LVDOConstants.PARTNER.FACEBOOK.name();
        interstitial_partners[6] = LVDOConstants.PARTNER.GOOGLEADMOB.name();
        interstitial_partners[7] = LVDOConstants.PARTNER.INMOBI.name();
        interstitial_partners[8] = LVDOConstants.PARTNER.MOPUB.name();
        interstitial_partners[9] = LVDOConstants.PARTNER.TAPJOY.name();
        interstitial_partners[10] = LVDOConstants.PARTNER.UNITY.name();
        interstitial_partners[11] = LVDOConstants.PARTNER.VUNGLE.name();
    }

    private static boolean[] interstitial_parters_selected = new boolean[numInterstitial];

    static {
        for (int i = 0; i < numInterstitial; i++) {
            interstitial_parters_selected[i] = true;
        }
    }

    public static List<LVDOConstants.PARTNER> setInterstitialPartners(LVDOAdRequest adRequest) {
        List<LVDOConstants.PARTNER> list = new ArrayList<>(numInterstitial);
        for (int i = 0; i < numInterstitial; i++) {
            if (interstitial_parters_selected[i]) {
                list.add(LVDOConstants.PARTNER.valueOf(interstitial_partners[i]));
            }
        }
        adRequest.setPartnerNames(list);
        return list;
    }


    /**
     * REWARDED
     */
    private static String[] rewarded_partners = new String[numRewarded];

    static {
        rewarded_partners[0] = LVDOConstants.PARTNER.AMAZON.name();
        rewarded_partners[1] = LVDOConstants.PARTNER.ADCOLONY.name();
        rewarded_partners[2] = LVDOConstants.PARTNER.APPLOVIN.name();
        rewarded_partners[3] = LVDOConstants.PARTNER.CRITEO.name();
        rewarded_partners[4] = LVDOConstants.PARTNER.CHOCOLATE.name();
        rewarded_partners[5] = LVDOConstants.PARTNER.FACEBOOK.name();
        rewarded_partners[6] = LVDOConstants.PARTNER.GOOGLEADMOB.name();
        rewarded_partners[7] = LVDOConstants.PARTNER.INMOBI.name();
        rewarded_partners[8] = LVDOConstants.PARTNER.MOPUB.name();
        rewarded_partners[9] = LVDOConstants.PARTNER.TAPJOY.name();
        rewarded_partners[10] = LVDOConstants.PARTNER.UNITY.name();
        rewarded_partners[11] = LVDOConstants.PARTNER.VUNGLE.name();
    }

    private static boolean[] rewarded_parters_selected = new boolean[numRewarded];

    static {
        for (int i = 0; i < numRewarded; i++) {
            rewarded_parters_selected[i] = true;
        }
    }

    public static List<LVDOConstants.PARTNER> setRewardedPartners(LVDOAdRequest adRequest) {
        List<LVDOConstants.PARTNER> list = new ArrayList<>(numRewarded);
        for (int i = 0; i < numRewarded; i++) {
            if (rewarded_parters_selected[i]) {
                list.add(LVDOConstants.PARTNER.valueOf(rewarded_partners[i]));
            }
        }
        adRequest.setPartnerNames(list);
        return list;
    }

    /**
     * NATIVE INVIEW
     */
    private static String[] inview_partners = new String[numInview];

    static {
        inview_partners[0] = LVDOConstants.PARTNER.AMAZON.name();
        inview_partners[1] = LVDOConstants.PARTNER.ADCOLONY.name();
        inview_partners[2] = LVDOConstants.PARTNER.APPLOVIN.name();
        inview_partners[3] = LVDOConstants.PARTNER.CRITEO.name();
        inview_partners[4] = LVDOConstants.PARTNER.CHOCOLATE.name();
        inview_partners[5] = LVDOConstants.PARTNER.FACEBOOK.name();
        inview_partners[6] = LVDOConstants.PARTNER.GOOGLEADMOB.name();
        inview_partners[7] = LVDOConstants.PARTNER.INMOBI.name();
        inview_partners[8] = LVDOConstants.PARTNER.MOPUB.name();
        inview_partners[9] = LVDOConstants.PARTNER.YAHOO.name();
    }

    private static boolean[] inview_parters_selected = new boolean[numInview];

    static {
        for (int i = 0; i < numInview; i++) {
            inview_parters_selected[i] = true;
        }
    }

    public static List<LVDOConstants.PARTNER> setInviewPartners(LVDOAdRequest adRequest) {
        List<LVDOConstants.PARTNER> list = new ArrayList<>(numInview);
        for (int i = 0; i < numInview; i++) {
            if (inview_parters_selected[i]) {
                list.add(LVDOConstants.PARTNER.valueOf(inview_partners[i]));
            }
        }
        adRequest.setPartnerNames(list);
        return list;
    }

    /**
     * PRE-ROLL
     */
    private static String[] preroll_partners = new String[numPreroll];

    static {
        preroll_partners[0] = LVDOConstants.PARTNER.CHOCOLATE.name();
        preroll_partners[1] = LVDOConstants.PARTNER.GOOGLE.name();
    }

    private static boolean[] preroll_parters_selected = new boolean[numPreroll];

    static {
        for (int i = 0; i < numPreroll; i++) {
            preroll_parters_selected[i] = true;
        }
    }

    public static List<LVDOConstants.PARTNER> setPrerollPartners(LVDOAdRequest adRequest) {
        List<LVDOConstants.PARTNER> list = new ArrayList<>(numPreroll);
        for (int i = 0; i < numPreroll; i++) {
            if (preroll_parters_selected[i]) {
                list.add(LVDOConstants.PARTNER.valueOf(preroll_partners[i]));
            }
        }
        adRequest.setPartnerNames(list);
        return list;
    }

    /**
     * @param adUnitType 0:interstitial, 1:rewarded, 2:inview, 3:preroll
     * @param context
     * @param listener
     */
    public static void choosePartners(int adUnitType, Context context, DialogInterface.OnClickListener listener) {
        String[] partners;
        boolean[] selected;
        String title;
        if (adUnitType == INTERSTITIAL) {
            partners = interstitial_partners;
            selected = interstitial_parters_selected;
            title = "Interstitial";
        } else if (adUnitType == REWARDED) {
            partners = rewarded_partners;
            selected = rewarded_parters_selected;
            title = "Rewarded";
        } else if (adUnitType == INVIEW) {
            partners = inview_partners;
            selected = inview_parters_selected;
            title = "Native Inview";
        } else {
            partners = preroll_partners;
            selected = preroll_parters_selected;
            title = "Pre-Roll";
        }
        //w/out the listener, even though it does nothing, things don't work so well
        //so we need to pass a listener
        new AlertDialog.Builder(context).setMultiChoiceItems(partners, selected, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        })
            .setPositiveButton("OK", listener)
            .setNegativeButton("CANCEL", dummyOnClick)
            .setTitle(title)
            .show();
    }

    private static DialogInterface.OnClickListener dummyOnClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    };

}
