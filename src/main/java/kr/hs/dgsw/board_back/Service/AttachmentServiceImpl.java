package kr.hs.dgsw.board_back.Service;

import kr.hs.dgsw.board_back.Domain.Attachment;
import kr.hs.dgsw.board_back.Repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    AttachmentRepository attachmentRepository;

    @Override
    public Attachment findById(Long id) {
        return this.attachmentRepository.findById(id).orElse(null);
    }

    @Override
    public Attachment add(Attachment attach) {
        return this.attachmentRepository.save(attach);
    }

    @Override
    public Attachment modify(Attachment attach) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        Attachment found = this.findById(id);
        if(found != null){
            attachmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
