package com.clairvoyant.services.skillmatrix.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchResponseDto {

    private long totalCount;

    private List<UserResponseDto> userResponseDto;

    private int totalPages;

    private int currentPage;
}
