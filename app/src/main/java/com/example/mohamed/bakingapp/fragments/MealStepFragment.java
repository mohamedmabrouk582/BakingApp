package com.example.mohamed.bakingapp.fragments;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed.bakingapp.R;
import com.example.mohamed.bakingapp.model.MealStep;
import com.example.mohamed.bakingapp.presenter.step.StepViewPresenter;
import com.example.mohamed.bakingapp.view.StepView;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

/**
 * Created by mohamed on 14/10/2017.
 */

public class MealStepFragment extends Fragment  implements StepView ,ExoPlayer.EventListener{
    private StepViewPresenter stepViewPresenter;
    private MealStep mealStep;
    private static String STEPS="STEPS";
    private TextView mealDes;
    public static boolean play=false;
    private SimpleExoPlayerView mSimpleExoPlayerView;
    private SimpleExoPlayer player;
    public static long position=0;
    private ImageView mImageView;
    public static MealStepFragment newFragment(MealStep list){
        Bundle bundle=new Bundle();
        bundle.putParcelable(STEPS, list);
        MealStepFragment fragment=new MealStepFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.meal_step_view_fragment,container,false);
        mealDes= (TextView) view.findViewById(R.id.step_descrepation);
        mealStep=getArguments().getParcelable(STEPS);
        mImageView= (ImageView) view.findViewById(R.id.step_image);
        stepViewPresenter=new StepViewPresenter(this,mealStep);
        mSimpleExoPlayerView= (SimpleExoPlayerView) view.findViewById(R.id.player);
        mSimpleExoPlayerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.meal));
        mSimpleExoPlayerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
          String videoUrl=mealStep.getVideoURL();

        if (!videoUrl.equals("")) {
            initializePlayer(Uri.parse(videoUrl));
//            play=player.getPlayWhenReady();
//            Toast.makeText(getActivity(), play+"", Toast.LENGTH_SHORT).show();
            mSimpleExoPlayerView.setVisibility(View.VISIBLE);
            restExoPlayerAfterRotation(0, false);

            if ((savedInstanceState != null) && savedInstanceState.containsKey("pos")) {
                position = savedInstanceState.getLong("pos");
                play=savedInstanceState.getBoolean("play");
                player.seekTo(position);
                player.setPlayWhenReady(play);
            }

        } else {
           mSimpleExoPlayerView.setVisibility(View.GONE);
        }

        if (savedInstanceState != null) {
            if (player != null) {
                player.seekTo(position);
                player.setPlayWhenReady(play);
            }
        }
        //----------------landscape--------------------------------------
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        if (width > height) {
            mealDes.setVisibility(View.GONE);
            mImageView.setVisibility(View.GONE);
            mSimpleExoPlayerView.setMinimumHeight(height);
            mSimpleExoPlayerView.setMinimumWidth(height);
            restExoPlayerAfterRotation(position, play);
        }

        return view;
    }

    private void restExoPlayerAfterRotation(long position, boolean playWhenReady) {
        this.position = position;
        if (player != null) {
            player.seekTo(position);
            player.setPlayWhenReady(play);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mealStep.getThumbnailURL()==""){
           mImageView.setVisibility(View.GONE);
        }else {
            mImageView.setVisibility(View.VISIBLE);
            Picasso.with(getActivity()).load(Uri.parse(mealStep.getThumbnailURL()))
                    .placeholder(R.drawable.meal).error(R.drawable.bakinglogo).into(mImageView);
        }
        stepViewPresenter.LoadStepData();
        //player.setAutoPlay(true);
    }
    private void initializePlayer(Uri mediaUri) {
        if (player == null) {
            //create an instance of the Exoplayer
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            player = ExoPlayerFactory.newSimpleInstance(this.getContext(), trackSelector, loadControl);
            mSimpleExoPlayerView.setPlayer(player);
            //prepare the MediaSource
            String userAgent = Util.getUserAgent(this.getContext(), "RecipeStep");
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri,
                    new DefaultDataSourceFactory(this.getContext(), userAgent)
                    , new DefaultExtractorsFactory(), null, null);
            player.prepare(mediaSource);
           // player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (player != null)
       outState.putLong("pos", player.getCurrentPosition());
      outState.putBoolean("play",play);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (player != null) {
            player.stop();
            player.release();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            player.stop();
            player.release();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
            player.release();
        }

    }

    @Override
    public void showStep(MealStep step) {
        mealDes.setText(step.getDescription());

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == PlaybackStateCompat.STATE_PLAYING || playbackState == PlaybackStateCompat.STATE_PAUSED) {
            position = player.getCurrentPosition();
            play=player.getPlayWhenReady();
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }
}
