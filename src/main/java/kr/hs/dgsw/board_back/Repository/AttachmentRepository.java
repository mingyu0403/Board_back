package kr.hs.dgsw.board_back.Repository;

import kr.hs.dgsw.board_back.Domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
