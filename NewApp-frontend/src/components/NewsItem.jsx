import React from 'react'



export function NewsItem(props) {

  return (
    <div><div className="card my-5" style={{width:'280px',height:'500px'}}>
      <img src={props.imageUrl ? props.imageUrl : ""} className="card-img-top" alt="..." />
      <div className="card-body">
        <h5 className="card-title">{props.title ? props.title : ""}</h5>
        <p className="card-text">{props.description ? props.description : ""}
        </p>

      </div>
    </div>
      <a href={props.newsUrl} target="_blank" className="btn btn-sm btn-primary">Read More</a></div>
  )
  // }
}

export default NewsItem

