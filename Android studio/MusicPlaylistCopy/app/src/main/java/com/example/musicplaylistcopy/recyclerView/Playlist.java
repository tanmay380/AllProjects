package com.example.musicplaylistcopy.recyclerView;

public class Playlist {
    String playlistId;
    String name, href;

    public Playlist(String playlistId, String name, String href) {
        this.playlistId = playlistId;
        this.name = name;
        this.href = href;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}

