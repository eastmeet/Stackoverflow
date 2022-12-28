import Header from "../Components/Header/Header"
import LeftNav from "../Components/LeftNav/LeftNav"
import styled from "styled-components/macro"
import Tag from "../Components/Tag/Tag"
import Footer from "../Components/Footer/Footer"
import BREAKPOINT from "../breakpoint";
import Pagination from "../Components/Pagination/Pagination"

import axios from "axios"
import { useQuery } from "@tanstack/react-query"
import { useState,  useEffect } from "react"
import { useSearchParams } from "react-router-dom"


const Container = styled.div`
  display: flex;
  width: 100%;
  height: max-content;
  max-width: 1260px;
  margin: 0 auto;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen;
`

const MainbarContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin: 0 20px;
  margin-bottom: 50px;
`

const MainbarTitleContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-top: 10px;
`
const MainbarTitle = styled.h1`
  font-weight: 400;
  font-size: 28px;
  @media screen and (max-width: ${BREAKPOINT.BREAKPOINTMOBILE}px) {
    font-size: 24px;
  }
`
const MainbarTitleDetail = styled.p`
  margin: 0;
  margin-bottom: 10px;
  font-size: 16px;
  @media screen and (max-width: ${BREAKPOINT.BREAKPOINTMOBILE}px) {
    font-size: 14px;
  }
`

const MainbarTagsContainer = styled.ul`
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  row-gap: 10px;
  column-gap: 10px;
  margin: 0;
  margin-top: 50px;
  list-style: none;
  padding: 0;
  width: 100%;
  justify-content: space-between;

  @media screen and (max-width: ${BREAKPOINT.BREAKPOINTTAGSTHREE}px) {
    grid-template-columns: repeat(3, minmax(0, 1fr));
  }
  @media screen and (max-width: ${BREAKPOINT.BREAKPOINTRIGHTSIDEBAR}px) {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
  @media screen and (max-width: ${BREAKPOINT.BREAKPOINTMOBILE}px) {
    grid-template-columns: repeat(1, minmax(0, 1fr));
    margin-top: 30px;
  }
`
const PaginationContainer = styled.div`
  align-self: flex-end;
`



export default function Tags() {
  const [tagsData, setTagsData] = useState('');
  const [pageInfo, setPageInfo] = useState('');
  const [searchParams, setSearchParams] = useSearchParams();
  const page = searchParams.get('page');

  useEffect(() => {
    window.scrollTo({left : 0, top: 0, behavior: "smooth"});
  }, [tagsData]);
  
  const fetchTags = () => {
    console.log(page);
    if(!!page) {
      return axios.get(`${process.env.REACT_APP_SERVER_URI}tags?page=${page}`);
    } else {
      console.log('with query', page);
      return axios.get(`${process.env.REACT_APP_SERVER_URI}tags`);
    }
  }

  const fetchTagsOnSuccess = (response) => {
    setTagsData(response.data.data);
    setPageInfo(response.data.pageInfo);
  }


  const {isLoading, refetch} = useQuery({queryKey: ['fetchTags', page], queryFn: fetchTags, keepPreviousData: true, onSuccess: fetchTagsOnSuccess});



  return (
    <>
    <Header/>
    <Container>
      <LeftNav/>
      <MainbarContainer>
        <MainbarTitleContainer>
          <MainbarTitle>Tags</MainbarTitle>
          <MainbarTitleDetail>A tag is a keyword or label that categorizes your question with other, similar questions. <br/>Using the right tags makes it easier for others to find and answer your question.</MainbarTitleDetail>
        </MainbarTitleContainer>
        <MainbarTagsContainer>
          {isLoading 
          ? <div>Loading...</div> 
          : tagsData && tagsData.map((tag) => {
            return <Tag data={tag} key={tag.tagId}/>
          })
        }
        </MainbarTagsContainer>
        <PaginationContainer>
          <Pagination pageinfo={pageInfo} setPage={setSearchParams} refetch={refetch}/>
        </PaginationContainer>
      </MainbarContainer>
    </Container>
    <Footer/>
    </>
  )
}