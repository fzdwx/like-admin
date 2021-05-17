package cn.like.code.server.business.notice.convert;

import cn.like.code.server.business.notice.entity.NoticeEntity;
import cn.like.code.server.business.notice.pojo.dto.NoticeDTO;
import cn.like.code.server.business.notice.pojo.query.NoticeQuery;
import com.sika.code.standard.base.convert.BaseConvert;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 通知公告表 转换类
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:53
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NoticeConvert extends BaseConvert<NoticeEntity, NoticeDTO> {
    /**
     * <p>
     * 转化类的单例对象
     * </p>
     */
    NoticeConvert INSTANCE = Mappers.getMapper(NoticeConvert.class);

    NoticeQuery dtoToQuery(NoticeDTO dto);

    List<NoticeDTO> dto2Query(List<NoticeDTO> dtos);

}
