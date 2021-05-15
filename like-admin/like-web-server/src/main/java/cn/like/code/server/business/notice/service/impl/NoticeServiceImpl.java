package cn.like.code.server.business.notice.service.impl;

import cn.like.code.server.business.notice.convert.NoticeConvert;
import cn.like.code.server.business.notice.entity.NoticeEntity;
import cn.like.code.server.business.notice.mapper.NoticeMapper;
import cn.like.code.server.business.notice.pojo.dto.NoticeDTO;
import cn.like.code.server.business.notice.service.NoticeService;
import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通知公告表 服务实现类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:05
 */
@Service(value = "noticeService")
public class NoticeServiceImpl extends BaseStandardServiceImpl<NoticeMapper, NoticeEntity, NoticeDTO> implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    protected BaseConvert<NoticeEntity, NoticeDTO> convert() {
        return NoticeConvert.INSTANCE;
    }
}

