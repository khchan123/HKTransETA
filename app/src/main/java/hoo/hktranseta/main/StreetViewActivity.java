package hoo.hktranseta.main;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import hoo.hktranseta.R;
import hoo.hktranseta.common.Constants;
import hoo.hktranseta.common.activity.TransparentActivity;

public class StreetViewActivity extends TransparentActivity implements OnStreetViewPanoramaReadyCallback {

    private static final String TAG = "StreetViewActivity";

    private String mTitle;
    private String mSubtitle;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_view);

        // Setup ActionBar
        setupActionBar();

        // Obtain the SupportStreetViewPanoramaFragment and get notified when street view is ready to be used.
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment =
                (SupportStreetViewPanoramaFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.street_view);

        // Get Activity based data
        mTitle = getIntent().getStringExtra(Constants.Extra.ACTIONBAR_TITLE);
        mSubtitle = getIntent().getStringExtra(Constants.Extra.ACTIONBAR_SUBTITLE);
        mLatLng = new LatLng(getIntent().getDoubleExtra(Constants.Extra.LATITUDE, 0),
                getIntent().getDoubleExtra(Constants.Extra.LONGITUDE, 0));

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(mTitle);
        actionBar.setSubtitle(mSubtitle);

        supportStreetViewPanoramaFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        streetViewPanorama.setPosition(mLatLng);
    }
}
