package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.model.Designation;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import com.clairvoyant.services.skillmatrix.repository.UserDesignationRepository;
import com.clairvoyant.services.skillmatrix.service.UserDesignationService;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDesignationServiceImpl implements UserDesignationService {

    @Autowired
    private UserDesignationRepository designationRepository;

    @Override
    public List<UserDesignationMapping> addOrUpdateUserDesignation(UserDesignationDto userDesignationDto) {
        ArrayList<String> designationId = new ArrayList<>(Arrays.asList(userDesignationDto.getDesignationId()));
        List<UserDesignationMapping> userDesignationMapping =  designationRepository.findByUserId(userDesignationDto.getUserId());

        List<UserDesignationMapping> userDesignationMappings = new ArrayList<>();
        if (userDesignationMapping.isEmpty()) {
            //Insert Designation for the first time
            newDesignationMapping(userDesignationDto.getUserId(), designationId, userDesignationMappings);
        } else {
            List<String> dbDesignationIds = userDesignationMapping.stream()
                    .map(userDesignation -> userDesignation.getDesignation().getId()).collect(Collectors.toList());
            List<String> dbActiveDesignationIds = userDesignationMapping.stream().filter(UserDesignationMapping::isActive)
                    .map(userDesignation -> userDesignation.getDesignation().getId()).collect(Collectors.toList());

            //Insert new Designation for the User -- create
            List<String> newDesignationIds = new ArrayList<>(Sets.difference(Sets.newHashSet(designationId), Sets.newHashSet(dbDesignationIds)));
            newDesignationMapping(userDesignationDto.getUserId(), newDesignationIds, userDesignationMappings);

            //removing newly inserted skillIds from request so that newly inserted ids will not come while updating in the below
            designationId.removeIf(newDesignationIds::contains);


            //update to inactive for existing mappings -- delete
            List<String> deletedDesignationIds = new ArrayList<>(Sets.difference(Sets.newHashSet(dbActiveDesignationIds), Sets.newHashSet(designationId)));
            for (String designation : deletedDesignationIds) {
                UserDesignationMapping mapping =
                        userDesignationMapping.stream().filter(udm -> designation.equals(udm.getDesignation().getId())).findFirst().get();
                mapping.setActive(false);
                userDesignationMappings.add(mapping);
            }

            //update existing inactive mapping to true -- update
            List<String> updateDesignationIds = new ArrayList<>(Sets.difference(Sets.newHashSet(designationId), Sets.newHashSet(dbActiveDesignationIds)));
            for (String designation : updateDesignationIds) {
                UserDesignationMapping mapping =
                        userDesignationMapping.stream().filter(udm -> designation.equals(udm.getDesignation().getId())).findFirst().get();
                mapping.setActive(true);
                userDesignationMappings.add(mapping);
            }

        }
        if (Objects.nonNull(userDesignationMappings) && userDesignationMappings.size() > 0) {
            return designationRepository.saveAll(userDesignationMappings);
        }
        return null;
    }

    private void newDesignationMapping(String userId, List<String> designationId, List<UserDesignationMapping> userDesignationMappings) {
        for (String di : designationId) {
            UserDesignationMapping userDesignationMapping = new UserDesignationMapping();
            User user = new User();
            user.setId(userId);
            userDesignationMapping.setUser(user);
            Designation designation = new Designation();
            designation.setId(di);
            userDesignationMapping.setDesignation(designation);
            userDesignationMapping.setActive(true);
            userDesignationMappings.add(userDesignationMapping);
        }
    }

    @Override
    public List<User> allUsers(String designationId) {

        List<UserDesignationMapping> userDesignationMappings = designationRepository.findByDesignationIdAndIsActive(designationId, false);

        return userDesignationMappings.stream().map(userDesignationMapping -> userDesignationMapping.getUser()).collect(Collectors.toList());
    }

    @Override
    public List<UserDesignationMapping> findAllUserDesignationMappings() {
        return designationRepository.findByIsActive(true);
    }

    @Override
    public List<UserDesignationMapping> findUserDesignationMappingByUserId(String userId) {
        return designationRepository.findByUserId(userId);
    }

    @Override
    public UserDesignationMapping findUserDesignationMappingByUserIdAndIsActive(String id, boolean b) {
        return designationRepository.findByUserIdAndIsActive(id,true);
    }


}

