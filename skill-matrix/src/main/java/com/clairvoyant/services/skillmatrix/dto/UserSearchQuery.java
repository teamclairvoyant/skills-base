package com.clairvoyant.services.skillmatrix.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSearchQuery {

    private String column;
    private  String value;
}

