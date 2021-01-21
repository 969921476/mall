package com.mall.product.vo;

import lombok.Data;

/**
 * @author： cjn
 * @create： 2021-01-21 16:50
 * @Description：
 * @Modified By:
 */
@Data
public class AttrResponseVo extends AttrVo {
    /**
     * 分类名称
     */
    private String catelogName;
    /**
     * 所属分主名称
     */
    private String groupName;

    /**
     * 返回分类所有id
     */
    private Long[] catelogPath;
}
