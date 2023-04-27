import React, { useState,useContext } from 'react'
import NewsItem from './NewsItem'

import PropTypes from 'prop-types';

import { AppContext } from '../context';

// const jsonData=sampleOutput.totalResults;
// console.log('jsonData : ',jsonData)
export default function News(props) {
  const {catagoryData } = useContext(AppContext);
  const [catagory,setCatagory] = catagoryData;

  News.defaultProps = {
    country: 'in',
    pageSize: 8,
    webu: 'https://newsapi.org/v2/top-headlines',
    apiKey: "0a0f58b168184719845611dd68ee279b"
  }

  News.propTypes = {
    country: PropTypes.string,
    pageSize: PropTypes.number,
    webu: PropTypes.string,
    apiKey: PropTypes.string
  }

  const [articles, setArticle] = useState([])

  const [page, setPage] = useState(1)

  const [totalResult, setTotalResult] = useState()

  React.useEffect(() => {
    console.log('catagory:......',catagory)
    let tempWeb = `${props.webu}?country=${props.country}&category=${catagory}&apiKey=${props.apiKey}`;
    let url = `${props.webu}?country=${props.country}&apiKey=${props.apiKey}&page=${page}&pageSize=${props.pageSize}`;
    console.log("url:", url)
    fetch(url).then(response => response.json())
      .then(data => {setArticle(data.articles); console.log('data:: ',data); setTotalResult(data.totalResult)})
    console.log("articles : ", articles)
    console.log("totalResult : ",totalResult)

    // let data = await fetch(url);
    // let parsedData = await data.json()
    // setArticle(parsedData.articles)
    // console.log("articles : ", articles)
    // setTotalResult(articles.totalResults)
    // console.log("totalResult : ",totalResult)


  }, []);




  const handlePrevClick = () => {

    let url = `${props.webu}?country=${props.country}&category=${catagory}&apiKey=${props.apiKey}&page=${page - 1}&pageSize=${props.pageSize}`;
    fetch(url).then(response => response.json())
      .then(data => setArticle(data.articles))

 
    setPage(page - 1)

  }


  const handleNextClick = () => {
    let url = `${props.webu}?country=${props.country}&category=${catagory}&apiKey=${props.apiKey}&page=${page + 1}&pageSize=${props.pageSize}`;
    fetch(url).then(response => response.json())
      .then(data => {setArticle(data.articles); setTotalResults(data.totalResults)})

   
    console.log("articles : ", articles)
    setPage(page + 1)
  // console.log("page:",page);
  // console('totalResults : ',totalResults)
  // console.log('props.pageSize : ',props.pageSize)
  // console.log("Math", Math.ceil(totalResults / props.pageSize))
  }



  return (
    <div className='container my-4' >
     <div className='container bg-dark text-white ' style={{textAlign:'center', textShadow:'2px 2px #ff0000',borderRadius:'10px', boxShadow:'10px 10px #a1b3d3'}}>
     <h1>TOP MONKEYNEWS</h1>
     </div>

      <div className="row" onChange={() => { <Link to={`/${props.country}`}>Click here</Link> }}>
        {
          articles.map((element) => {
            return (
              <div className="col-md-3" key={element.url}>
                <NewsItem title={element.title} description={element.description} imageUrl={element.urlToImage} newsUrl={element.url} />
              </div>
            )
          })
        }
      </div>
      <div className="container d-flex justify-content-between my-5">
        <button type="button" disabled={page <= 1} className="btn btn-info" onClick={handlePrevClick}>previous</button>
        <button type="button" disabled={page  > Math.ceil(totalResult / props.pageSize)} className="btn btn-info" onClick={handleNextClick}>Next</button>
      </div>
    </div>
  )
}

