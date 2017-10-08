package utils;

import channel.ChannelResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.image.ImageView;

public class ChannelInfo {
    private ChannelResponse response;

    public String channelTitle;
    public String date;
    public String subscribers;
    public String videos;
    public String views;
    public String comments;

    public ChannelInfo(String id, String apiKey) throws UnirestException {
        response = getChannelInfo(id, apiKey);
        setChannelTitle();
        setSubscribers();
        setDate();
        setVideos();
        setComments();
        setViews();
    }

    private void setChannelTitle(){
        try {
            channelTitle = response.items.get(0).snippet.title;
        } catch (IndexOutOfBoundsException ex){
            channelTitle = "";
        }
    }

    public void setDate() {
        try {
            date = response.items.get(0).snippet.publishedAt.substring(0,10);
        } catch (IndexOutOfBoundsException ex){
            date = "";
        }
    }

    public void setSubscribers() {
        try {
            subscribers = response.items.get(0).statistics.subscriberCount;
        } catch (IndexOutOfBoundsException ex) {
            subscribers = "";
        }
    }

    public void setVideos() {
        try {
            videos = response.items.get(0).statistics.videoCount;
        } catch (IndexOutOfBoundsException ex) {
            videos = "";
        }
    }

    public void setViews(){
        try {
            views = response.items.get(0).statistics.viewCount;
        } catch (IndexOutOfBoundsException ex) {
            views = "";
        }
    }


    public void setComments(){
        try {
            comments = response.items.get(0).statistics.commentCount;
        } catch (IndexOutOfBoundsException ex){
            comments = "";
        }
    }

    public ImageView getImage(){
        try {
            return ImageUtils.crop(response.items.get(0).snippet.thumbnails.medium.url);
        } catch (IndexOutOfBoundsException ex){
            return ImageUtils.crop(ImageUtils.default_image);
        }
    }

    public String getDescription(){
        try {
            return response.items.get(0).snippet.description;
        } catch (IndexOutOfBoundsException ex){
            return "";
        }
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public String getDate() {
        return date;
    }

    public String getSubscribers() {
        return subscribers;
    }

    public String getVideos() {
        return videos;
    }

    public String getViews() {
        return views;
    }

    public Long getComments() {
        if (comments.equals(""))
            return -1l;
        else
            return Long.parseLong(comments);
    }

    private ChannelResponse getChannelInfo(String channelId, String apiKey) throws UnirestException {
        HttpResponse<ChannelResponse> response = Unirest.get("https://www.googleapis.com/youtube/v3/channels")
                .queryString("key", apiKey)
                .queryString("part", "snippet,contentDetails,statistics")
                .queryString("id", channelId)
                .asObject(ChannelResponse.class);
        return response.getBody();
    }
}
