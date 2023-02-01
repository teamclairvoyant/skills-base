package com.clairvoyant.services.skillmatrix.graphql.resolver;

import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Skill;
import com.clairvoyant.services.skillmatrix.model.SkillCategory;
import com.clairvoyant.services.skillmatrix.repository.CategoryRepository;
import com.clairvoyant.services.skillmatrix.repository.SkillRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkillCategoryFieldResolver implements GraphQLResolver<SkillCategory> {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SkillRepository skillRepository;

    public Category category(SkillCategory skillCategory) {
        Optional<Category> opt= categoryRepository.findById(skillCategory.getCategory().getId());
        Category category=null;
        if(opt.isPresent())
        {
            category=opt.get();
        }
        else {
            categoryRepository.save(skillCategory.getCategory());
            category = skillCategory.getCategory();

        }

        return category;
    }

    public Skill skill(SkillCategory skillCategory) {
        Optional<Skill> opt=skillRepository.findById(skillCategory.getSkill().getId());
        Skill skill=null;
        if(opt.isPresent())
        {
            skill=opt.get();
        }
        else {
            skillRepository.save(skillCategory.getSkill());
            skill = skillCategory.getSkill();

        }

        return skill;
    }

}
