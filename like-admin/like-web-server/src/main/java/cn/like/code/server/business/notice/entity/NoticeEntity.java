package cn.like.code.server.business.notice.entity;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author like @email:980650920@qq.com
 * @since 2021-05-17 22:07:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_notice")
public class NoticeEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;


}
