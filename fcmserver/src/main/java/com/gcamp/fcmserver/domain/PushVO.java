package com.gcamp.fcmserver.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PushVO {
    private String device_token;
    private String title;
    private String body;
    private String device_os;
}
