package com.example.stackoverflowclone.helper;

import com.example.stackoverflowclone.domain.member.entity.Member;
import com.example.stackoverflowclone.domain.question.dto.QuestionPostDto;
import com.example.stackoverflowclone.domain.question.entity.Question;
import com.example.stackoverflowclone.domain.tag.entity.Tag;
import org.springframework.http.HttpMethod;

import java.util.*;

public class StubData {
    private static Map<HttpMethod, Object> stubRequestBody;

    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, QuestionPostDto.builder()
                        .email("dhfif718@gmail.com")
                        .questionTitle("질문 제목 입니다.")
                        .questionProblemBody("질문 내용 1")
                        .questionTryOrExpectingBody("질문 내용 2")
                        .tag(new ArrayList<>())
                        .build());
    }

    public static class MockTag {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }
        public static List<Tag> getSingleResponseBody(){
            List<Tag> tagList = new ArrayList<>();
            tagList.add(Tag.builder()
                    .tagId(1L)
                    .tagName("java")
                    .tagBody("java 바디 내용입니다.")
                    .tagUrl("")
                    .build());
            tagList.add(Tag.builder()
                    .tagId(2L)
                    .tagName("javascript")
                    .tagBody("javascript 바디 내용입니다.")
                    .tagUrl("")
                    .build());
            return tagList;
        }
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Member getSingleResponseBody(Long memberId) {
            return Member.builder()
                    .memberId(memberId)
                    .username("이재혁")
                    .email("dhfif718@naver.com")
                    .location("Seoul, KOREA")
                    .title("자기 소개 타이틀 입니다")
                    .aboutMe("자기 소개 칸 입니다")
                    .image("/home/opt/img.png")
                    .websiteLink("웹사이트 링크 칸입니다.")
                    .twitterLink("트위터 링크 칸입니다.")
                    .githubLink("깃허브 링크 칸입니다.")
                    .fullname("풀네임 칸입니다.")
                    .build();
        }
    }

    public static class MockQuestion {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Question getSingleResponseBody(Long questionId){
            return Question.builder()
                    .questionId(questionId)
                    .questionTitle("질문 제목 입니다.")
                    .questionProblemBody("질문 내용1 입니다.")
                    .questionTryOrExpectingBody("질문 내용2 입니다.")
                    .questionViewCount(200)
                    .questionVoteCount(350)
                    .member(new Member())
                    .questionTagList(new ArrayList<>())
                    .answers(new ArrayList<>())
                    .build();
        }
    }
}
