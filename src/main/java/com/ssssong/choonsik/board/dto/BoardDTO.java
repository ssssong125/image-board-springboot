package com.ssssong.choonsik.board.dto;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class BoardDTO {
    private Long boardCode;
    private String boardTitle;
    private String memberId;
    private MultipartFile boardImage;
    private String imgUrl;
    private Date regDate;
    private char boardStatus;

    public BoardDTO() {
    }

    public BoardDTO(Long boardCode, String boardTitle, String memberId, MultipartFile boardImage, String imgUrl, Date regDate, char boardStatus) {
        this.boardCode = boardCode;
        this.boardTitle = boardTitle;
        this.memberId = memberId;
        this.boardImage = boardImage;
        this.imgUrl = imgUrl;
        this.regDate = regDate;
        this.boardStatus = boardStatus;
    }

    public MultipartFile getBoardImage() {
        return boardImage;
    }

    public void setBoardImage(MultipartFile boardImage) {
        this.boardImage = boardImage;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(Long boardCode) {
        this.boardCode = boardCode;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBoardImageUrl() {
        return imgUrl;
    }

    public void setBoardImageUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public char getBoardStatus() {
        return boardStatus;
    }

    public void setBoardStatus(char boardStatus) {
        this.boardStatus = boardStatus;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "boardCode=" + boardCode +
                ", boardTitle='" + boardTitle + '\'' +
                ", memberId='" + memberId + '\'' +
                ", boardImage=" + boardImage +
                ", imgUrl='" + imgUrl + '\'' +
                ", regDate=" + regDate +
                ", boardStatus=" + boardStatus +
                '}';
    }
}
