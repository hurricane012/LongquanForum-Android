/**
  * Copyright 2017 jb51.net 
  */
package discuz.com.net.service.model.bean.my_reply;
import org.codehaus.jackson.annotate.JsonProperty;
/**
 * Auto-generated: 2017-08-11 9:55:33
 *
 * @author jb51.net (i@jb51.net)
 * @website http://tools.jb51.net/code/json2javabean
 */
public class Lists {
    @JsonProperty("pic_path")
    private String pic_path;
    @JsonProperty("board_id")
    private int board_id;
    @JsonProperty("board_name")
    private String board_name;
    @JsonProperty("topic_id")
    private int topic_id;
    @JsonProperty("type_id")
    private int type_id;
    @JsonProperty("sort_id")
    private int sort_id;
    private String title;
    private String subject;
    @JsonProperty("user_id")
    private int user_id;
    @JsonProperty("last_reply_date")
    private String last_reply_date;
    @JsonProperty("user_nick_name")
    private String user_nick_name;
    private int hits;
    private int replies;
    private int top;
    private int status;
    private int essence;
    private int hot;
    @JsonProperty("userAvatar")
    private String userAvatar;
    private int special;

    public String getPic_path() {
        return pic_path;
    }

    public void setPic_path(String pic_path) {
        this.pic_path = pic_path;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getSort_id() {
        return sort_id;
    }

    public void setSort_id(int sort_id) {
        this.sort_id = sort_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLast_reply_date() {
        return last_reply_date;
    }

    public void setLast_reply_date(String last_reply_date) {
        this.last_reply_date = last_reply_date;
    }

    public String getUser_nick_name() {
        return user_nick_name;
    }

    public void setUser_nick_name(String user_nick_name) {
        this.user_nick_name = user_nick_name;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getReplies() {
        return replies;
    }

    public void setReplies(int replies) {
        this.replies = replies;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getEssence() {
        return essence;
    }

    public void setEssence(int essence) {
        this.essence = essence;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }
}