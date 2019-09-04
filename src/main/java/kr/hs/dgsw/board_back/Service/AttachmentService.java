package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.Attachment;

import java.util.Optional;

public interface AttachmentService{
    Attachment findById(Long id);
    Attachment add(Attachment attach);
    Attachment modify(Attachment attach);
    boolean deleteById(Long id);
}