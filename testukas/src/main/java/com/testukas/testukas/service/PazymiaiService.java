package com.testukas.testukas.service;

import com.testukas.testukas.dto.StudentasDTO;
import com.testukas.testukas.entity.Studentas;
import com.testukas.testukas.repository.PazymiaiRepository;
import com.testukas.testukas.repository.StudentasRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PazymiaiService {

    @Autowired
    private PazymiaiRepository pazymiaiRepository;

    @Autowired
    private StudentasRepository studentasRepository;

    @Autowired
    private StudentasService studentasService;




//    public List<Message> fetchMessages(Long id) {
//        List<MessageEntity> messages = (List<MessageEntity>) messageRepository.findAll();
//
//        List<Message> messageList = new ArrayList<>();
//
//
//
//        for(MessageEntity message: messages) {
//            Message newMessage = new Message();
//            if(message.getAuthorId() == id){
//                newMessage.setMessage(message.getMessage());
//                newMessage.setReceiverId(message.getReceiverId());
//                newMessage.setAuthorId(message.getAuthorId());
//                messageList.add(newMessage);
//            }
//
//        }
//
//        return messageList;
//
//    }

    public List<StudentasDTO> fetchUserMessages() {


//        List<MessageEntity> messages = (List<MessageEntity>) messageRepository.findById(idt).get();

        List<Studentas> messages = (List<Studentas>) studentasRepository.findAll();



        return messages.stream()
                .map(StudentasDTO::new)
                .collect(Collectors.toList());

    }

}
