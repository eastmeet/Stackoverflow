package com.example.stackoverflowclone.domain.tag.service;

import com.example.stackoverflowclone.domain.tag.entity.Tag;
import com.example.stackoverflowclone.domain.tag.repository.TagRepository;
import com.example.stackoverflowclone.global.exception.BusinessLogicException;
import com.example.stackoverflowclone.global.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import com.example.stackoverflowclone.domain.question.dto.QuestionPostDto;
import com.example.stackoverflowclone.domain.question_tag.entity.QuestionTag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;


    public Page<Tag> findTags(int page, int size) {
        return tagRepository.findAll(PageRequest.of(page, size, Sort.by("tagId").descending()));
    }


    public List<Tag> findTags(QuestionPostDto questionPostDto){
        return questionPostDto.getTag().stream()
                .map(tag -> {
                    return findTag(tag.getTagName());
                })
                .collect(Collectors.toList());
    }

    public List<Tag> findTags(List<QuestionTag> questionTagList){

        return questionTagList.stream()
                .map(questionTag -> {
                    Optional<Tag> findTag = tagRepository.findById(questionTag.getTag().getTagId());
                    return findTag.orElseThrow(() ->
                            new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));

                })
                .collect(Collectors.toList());
    }

    public Tag findTag(String tagName){
        Optional<Tag> findTagName = tagRepository.findByTagName(tagName);
        return findTagName.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.TAG_NOT_FOUND));
    }
}
