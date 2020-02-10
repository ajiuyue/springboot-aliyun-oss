package top.snailclimb.common;

import lombok.Builder;
import lombok.Data;

/**
 * @author shuang.kou
 */
@Data
@Builder
public class CommonResult {
    /**
     * 0 成功
     */
    private Integer code;
    private String msg;
    private Object data;
}
