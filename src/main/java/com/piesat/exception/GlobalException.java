package com.piesat.exception;

import lombok.Data;

/**
 * <p>全局异常信息</p>
 * @author XK
 *
 */

@Data
public class GlobalException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
}

