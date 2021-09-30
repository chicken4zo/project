package kr.or.bit.dto;

import java.util.Date;

public class DailyBoard {
    private int idx;
    private String title;
    private String content;
    private int hit;
    private Date writeDate;
    private String fileName;
    private String filePath;
    private int refer;
    private int depth;
    private int step;
    private String id;
    private String address;

    public DailyBoard(int idx, String title, String content, int hit, Date writeDate, String fileName, String filePath, int refer, int depth, int step, String id, String address) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.writeDate = writeDate;
        this.fileName = fileName;
        this.filePath = filePath;
        this.refer = refer;
        this.depth = depth;
        this.step = step;
        this.id = id;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DailyBoard{" +
                "idx=" + idx +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", hit=" + hit +
                ", writeDate=" + writeDate +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", refer=" + refer +
                ", depth=" + depth +
                ", step=" + step +
                ", id='" + id + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public DailyBoard() {
    }

    public DailyBoard(int idx, String title, String content, int hit, Date writeDate, String fileName, String filePath, int refer, int depth, int step, String id) {
        this.idx = idx;
        this.title = title;
        this.content = content;
        this.hit = hit;
        this.writeDate = writeDate;
        this.fileName = fileName;
        this.filePath = filePath;
        this.refer = refer;
        this.depth = depth;
        this.step = step;
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

    public int getRefer() {
        return refer;
    }

    public void setRefer(int refer) {
        this.refer = refer;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
