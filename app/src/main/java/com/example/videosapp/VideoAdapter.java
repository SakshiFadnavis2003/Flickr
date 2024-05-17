package com.example.videosapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.List;
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    // List of video URLs
    private List<String> videoUrls;
    // List of video resource IDs
    private List<Integer> videoRawIds;

    // Constructor
    public VideoAdapter(List<?> videos) {
        if (videos != null && !videos.isEmpty()) {
            if (videos.get(0) instanceof String) {
                this.videoUrls = (List<String>) videos;
            } else if (videos.get(0) instanceof Integer) {
                this.videoRawIds = (List<Integer>) videos;
            }
        }
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        // Bind based on the chosen approach
        if (videoUrls != null) {
            holder.bind(videoUrls.get(position));
        } else if (videoRawIds != null) {
            holder.bind(videoRawIds.get(position));
        }
    }

    @Override
    public int getItemCount() {
        // Return the size based on the chosen approach
        if (videoUrls != null) {
            return videoUrls.size();
        } else if (videoRawIds != null) {
            return videoRawIds.size();
        }
        return 0;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
        private PlayerView playerView;
        private SimpleExoPlayer player;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            player = new SimpleExoPlayer.Builder(itemView.getContext()).build();
            playerView.setPlayer(player);

            player.setAudioAttributes(AudioAttributes.DEFAULT, true);
        }



        // Method to bind a video URL
        public void bind(String videoUrl) {
            MediaItem mediaItem = MediaItem.fromUri(videoUrl);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
        }

        // Method to bind a video resource ID
        public void bind(int videoRawId) {
            String uri = "android.resource://" + itemView.getContext().getPackageName() + "/" + videoRawId;
            MediaItem mediaItem = MediaItem.fromUri(uri);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();
        }
    }
}
