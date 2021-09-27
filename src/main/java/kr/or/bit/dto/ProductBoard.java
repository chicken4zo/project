package kr.or.bit.dto;

import java.util.Date;

public class ProductBoard {
    private int idx;
    private String title;
    private String content;
    private int hit;
    private Date writeDate;
    private String fileName1;
    private String filePath1;
    private String fileName2;
    private String filePath2;
    private String fileName3;
    private String filePath3;
    private String id;

    public ProductBoard() {
    }

    public ProductBoard(int idx, String title, String content, int hit, Date writeDate, String fileName1, String filePath1, String fileName2, String filePath2, String fileName3, String filePath3, String id) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.writeDate = writeDate;
        this.fileName1 = fileName1;
        this.filePath1 = filePath1;
        this.fileName2 = fileName2;
        this.filePath2 = filePath2;
        this.fileName3 = fileName3;
        this.filePath3 = filePath3;
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

    public String getFileName1() {
        return fileName1;
    }

    public void setFileName1(String fileName1) {
        this.fileName1 = fileName1;
    }

    public String getFilePath1() {
        return filePath1;
    }

    public void setFilePath1(String filePath1) {
        this.filePath1 = filePath1;
    }

    public String getFileName2() {
        return fileName2;
    }

    public void setFileName2(String fileName2) {
        this.fileName2 = fileName2;
    }

    public String getFilePath2() {
        return filePath2;
    }

    public void setFilePath2(String filePath2) {
        this.filePath2 = filePath2;
    }

    public String getFileName3() {
        return fileName3;
    }

    public void setFileName3(String fileName3) {
        this.fileName3 = fileName3;
    }

    public String getFilePath3() {
        return filePath3;
    }

    public void setFilePath3(String filePath3) {
        this.filePath3 = filePath3;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductBoard{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", writeDate=" + writeDate +
                ", fileName1='" + fileName1 + '\'' +
                ", filePath1='" + filePath1 + '\'' +
                ", fileName2='" + fileName2 + '\'' +
                ", filePath2='" + filePath2 + '\'' +
                ", fileName3='" + fileName3 + '\'' +
                ", filePath3='" + filePath3 + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
