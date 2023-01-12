package com.clairvoyant.clarise.graphql;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.model.SkillCategory;
import com.clairvoyant.clarise.repository.CategoryRepository;
import com.clairvoyant.clarise.repository.SkillRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SkillCategoryFieldResolver implements GraphQLResolver<SkillCategory> {

    private CategoryRepository categoryRepository;

    private SkillRepository skillRepository;

    public SkillCategoryFieldResolver(
            CategoryRepository categoryRepository, SkillRepository skillRepository) {
        this.categoryRepository = categoryRepository;
        this.skillRepository = skillRepository;
    }

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
