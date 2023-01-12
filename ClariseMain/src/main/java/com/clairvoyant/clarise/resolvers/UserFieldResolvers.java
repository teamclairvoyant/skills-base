//package com.clairvoyant.clarise.resolvers;
//
//
//import com.clairvoyant.clarise.model.Designation;
//import com.clairvoyant.clarise.model.User;
//import com.clairvoyant.clarise.repository.DesignationRepository;
//import graphql.kickstart.tools.GraphQLResolver;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//public class UserFieldResolvers implements GraphQLResolver<User> {
//
//    private final Logger LOGGER= LoggerFactory.getLogger(UserFieldResolvers.class);
//    private DesignationRepository designationRepository;
//
//    public UserFieldResolvers(DesignationRepository designationRepository) {
//        this.designationRepository = designationRepository;
//    }
//
//    public Designation designation(User user)
//    {
//        Optional<Designation> opt=designationRepository.findById(user.getDesignation().getId());
//        Designation designation=null;
//        if(opt.isPresent())
//        {
//            designation=opt.get();
//        }
//        else {
//            designationRepository.save(user.getDesignation());
//            designation = user.getDesignation();
//        }
//
//        return designation;
//    }
//
//
//}
