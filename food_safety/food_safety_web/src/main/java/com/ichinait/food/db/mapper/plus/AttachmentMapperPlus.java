package com.ichinait.food.db.mapper.plus;

import com.ichinait.food.db.entity.Attachment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttachmentMapperPlus {

	List<Attachment> selectAttachmentsByIds(List<String> ids);

}
