package com.global.chatbox.mapStruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.global.chatbox.mapStruct.dto.MessageDto;
import com.global.chatbox.model.Message;

@Mapper(componentModel  ="spring")
public interface MessageMapper {
    @Mapping(target ="login",source ="message.user.login" )
    @Mapping(target = "message",source = "message.text")
    MessageDto toDto(Message message);
}
