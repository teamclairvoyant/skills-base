//package com.clairvoyant.clarise.model;
//
//import com.clairvoyant.clarise.model.superclass.Persistable;
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Data
//@Table(name="user_category_mapping")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
//public class UserCategoryMapping extends Persistable {
//
//    @ManyToOne(targetEntity=User.class,fetch = FetchType.LAZY, optional = false )
//    private User user;
//
//    @ManyToOne(targetEntity=Category.class,fetch = FetchType.LAZY, optional = false )
//    private Category category;
//
//}
