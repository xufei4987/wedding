package com.youxu.wedding.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespMsg implements Serializable {

    private static final long serialVersionUID = 4095722369366949690L;

    private boolean success;

    private String code;

    private String msg;
}
