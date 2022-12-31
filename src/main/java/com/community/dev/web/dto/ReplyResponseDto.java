package com.community.dev.web.dto;


import com.community.dev.domain.reply.Reply;
import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private String writer;
    private String contents;

    protected ReplyResponseDto() {}

    private ReplyResponseDto(String contents, String writer) {
        this.contents = contents;
        this.writer = writer;
    }

    public static ReplyResponseDto from(Reply entity) {
        return new ReplyResponseDto(entity.getContents(), entity.getWriter());
    }
}
