package uk.ac.napier.der.util;

import android.widget.ArrayAdapter;

import uk.ac.napier.der.model.Video;

/**
 * Created by Dan on 01/03/2018.
 *
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.VideoView;

import java.util.List;

import uk.ac.napier.der.R;
import uk.ac.napier.der.model.Video;


/*the code below build the array used to store the video url*/
public class VideoAdapter extends ArrayAdapter<Video> {

    private Context mContext;
    private List<Video> mVideos;

    public VideoAdapter(@NonNull Context context, @NonNull List<Video> objects) {
        super(context, R.layout.video_row, objects);

        mContext = context;
        mVideos = objects;
    }
    /*the code below calls the layout video_row to populate the page with thumbnails of the video*/

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.video_row, null);
            holder = new ViewHolder();

            holder.videoView = (VideoView) convertView
                    .findViewById(R.id.videoView);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        //plays the video and audio when clicked
        try {
            Video video = mVideos.get(position);
            //play video using android api, when video view is clicked.
            String url = video.getVideoUrl(); // your URL here
            Uri videoUri = Uri.parse(url);
            holder.videoView.setVideoURI(videoUri);
            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    holder.videoView.start();
                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    public static class ViewHolder {
        VideoView videoView;

    }
}