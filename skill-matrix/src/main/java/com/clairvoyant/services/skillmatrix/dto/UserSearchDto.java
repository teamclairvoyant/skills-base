package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSearchDto {

    private int page;
    private int size;
    List<UserSearchQuery> queries;

}

