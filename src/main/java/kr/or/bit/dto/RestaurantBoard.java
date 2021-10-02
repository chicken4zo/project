package kr.or.bit.dto;

import java.util.Date;

public class RestaurantBoard {
    private int idx;
    private String title;
    private String content;
    private int hit;
    private Date writeDate;
    private String fileName;
    private String filePath;
    private String id;

    public RestaurantBoard() {
    }

    public RestaurantBoard(int idx, String title, String content, int hit, Date writeDate, String fileName, String filePath, String id) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.writeDate = writeDate;
        this.fileName = fileName;
        this.filePath = filePath;
        this.id = id;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RestaurantBoard{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", writeDate=" + writeDate +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
