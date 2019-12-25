package com.jeeno.oauth2client.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <p>
 * 创建时间为 22:29 2019-07-25
 * 项目名称 phoenix-waf-publisher
 * </p>
 *
 * @author 石少东
 * @version 2.0.0
 * @since 2.0.0
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO<T> implements Serializable {

    private static final long serialVersionUID = -2095916884810199532L;
    private EnumStatus status;
    private String message;
    private T data;
    public enum EnumStatus {

        /**
         * 成功
         */
        SUCCESS,

        /**
         * 失败
         */
        FAILURE;
    }

}
