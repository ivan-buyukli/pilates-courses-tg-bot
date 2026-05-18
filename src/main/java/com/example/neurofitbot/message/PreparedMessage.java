package com.example.neurofitbot.message;

import com.example.neurofitbot.common.ButtonType;
import com.example.neurofitbot.common.MediaType;
import com.example.neurofitbot.common.MessageCode;
import jakarta.persistence.*;

@Entity
@Table(name = "prepared_messages")
public class PreparedMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MessageCode code;

    private String title;

    @Column(columnDefinition = "text")
    private String textBefore;

    @Enumerated(EnumType.STRING)
    private MediaType mediaType = MediaType.NONE;

    @Column(columnDefinition = "text")
    private String telegramFileId;

    @Column(columnDefinition = "text")
    private String caption;

    @Column(columnDefinition = "text")
    private String textAfter;

    private String buttonText;

    @Enumerated(EnumType.STRING)
    private ButtonType buttonType = ButtonType.CALLBACK;

    @Column(columnDefinition = "text")
    private String buttonValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MessageCode getCode() {
        return code;
    }

    public void setCode(MessageCode code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextBefore() {
        return textBefore;
    }

    public void setTextBefore(String textBefore) {
        this.textBefore = textBefore;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getTelegramFileId() {
        return telegramFileId;
    }

    public void setTelegramFileId(String telegramFileId) {
        this.telegramFileId = telegramFileId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTextAfter() {
        return textAfter;
    }

    public void setTextAfter(String textAfter) {
        this.textAfter = textAfter;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public ButtonType getButtonType() {
        return buttonType;
    }

    public void setButtonType(ButtonType buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonValue() {
        return buttonValue;
    }

    public void setButtonValue(String buttonValue) {
        this.buttonValue = buttonValue;
    }
}
