package kr.or.bit.dto;

import java.util.Date;

public class RestaurantComment {
    private int no;
    private String content;
    private Date writeDate;
    private String id;
    private int idx;

    public RestaurantComment() {
    }

    public RestaurantComment(int no, String content, Date writeDate, String id, int idx) {
        this.no = no;
        this.content = content;
        this.writeDate = writeDate;
        this.id = id;
        this.idx = idx;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public String toString() {
        return "RestaurantComment{" +
                "no=" + no +
                ", content='" + content + '\'' +
                ", writeDate=" + writeDate +
                ", id='" + id + '\'' +
                ", idx=" + idx +
                '}';
    }
}
