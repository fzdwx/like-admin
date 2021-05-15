package cn.like.code.server.business.notice.convert;

import cn.like.code.server.business.notice.entity.NoticeEntity;
import cn.like.code.server.business.notice.pojo.dto.NoticeDTO;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 通知公告表 转换类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-15 07:37:05
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeConvert extends BaseConvert<NoticeEntity, NoticeDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

}
