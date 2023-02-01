package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.model.Designation;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import com.clairvoyant.services.skillmatrix.repository.UserDesignationRepository;
import com.clairvoyant.services.skillmatrix.service.UserDesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDesignationServiceImpl implements UserDesignationService {

    @Autowired
    private UserDesignationRepository designationRepository;

    @Override
    public void addOrUpdateUserDesignation(UserDesignationDto userDesignationDto) {

        UserDesignationMapping userDesignationMapping= designationRepository.findByUserId(userDesignationDto.getUserId());


        if(Objects.isNull(userDesignationMapping))
        {
            UserDesignationMapping newMapping=new UserDesignationMapping();
            User user=new User();
            Designation designation=new Designation();

            user.setId(userDesignationDto.getUserId());
            designation.setId(userDesignationDto.getDesignationId());

            newMapping.setUser(user);
            newMapping.setDesignation(designation);
            newMapping.setActive(true);
            designationRepository.save(newMapping);
        }


    }

    @Override
    public List<User> allUsers(String designationId) {

        List<UserDesignationMapping> userDesignationMappings = designationRepository.findByDesignationIdAndIsActive(designationId,false);

        return userDesignationMappings.stream().map(userDesignationMapping -> userDesignationMapping.getUser()).collect(Collectors.toList());
    }

    @Override
    public List<UserDesignationMapping> findAllUserDesignationMappings(){
        return designationRepository.findAll();
    }

    @Override
    public UserDesignationMapping findUserDesignationMappingByUserId(String userId) {
        return designationRepository.findByUserId(userId);
    }


}

