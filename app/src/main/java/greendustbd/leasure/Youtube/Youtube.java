package greendustbd.leasure.Youtube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import greendustbd.leasure.R;
import greendustbd.leasure.SingleActivity;


public class Youtube extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_DIALOG_REQUEST = 1;

    // YouTube player view
    private YouTubePlayerView youTubeView;
    private LinearLayout rlLayout;
    private YouTubePlayer youTubePlayer;
    private ImageButton home,full;

//    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
             setContentView(R.layout.activity_youtube);

//        NativeExpressAdView adView = (NativeExpressAdView)findViewById(R.id.adViewn);
//        adView.loadAd(new AdRequest.Builder().build());

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        ImageButton full = (ImageButton) findViewById(R.id.full);

        full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                youTubePlayer.setFullscreen(true);



            }
        });
                // For back to previous activity
//                ImageButton home = (ImageButton) findViewById(R.id.home);
//
//                home.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(Youtube.this, NoBoringActionBarActivity.class);
//                        Youtube.this.startActivity(intent);
//                        finish();
//
//
//                    }
//                });


                youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

                // Initializing video player with developer key
                youTubeView.initialize(Config.DEVELOPER_KEY, this);

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult errorReason) {


                if (errorReason.isUserRecoverableError()) {
                    errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
                } else {
                    String errorMessage = String.format(
                            getString(R.string.error_player), errorReason.toString());
                    Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer player, boolean wasRestored) {

                youTubePlayer = player;

                if (!wasRestored) {

                    Bundle extras = getIntent().getExtras();
                    String url;
                    if (extras != null) {
                        url = extras.getString("url");

                        // loadVideo() will auto play video
                        // Use cueVideo() method, if you don't want to play it automatically
                        player.loadVideo(url);


                        // For fullscreen
//                player.setFullscreen(true);

//                // Hiding player controls
//                player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                    }
                }


            }

//    private void requestNewInterstitial() {
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
//                .build();
//
//                mInterstitialAd.loadAd(adRequest);
//                    }






    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Youtube.this, SingleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();

    }


            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (requestCode == RECOVERY_DIALOG_REQUEST) {
                    // Retry initialization if user performed a recovery action
                    getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
                }
            }

            private YouTubePlayer.Provider getYouTubePlayerProvider() {
                return (YouTubePlayerView) findViewById(R.id.youtube_view);
            }


        }