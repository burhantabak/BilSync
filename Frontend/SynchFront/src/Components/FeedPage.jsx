import React, { useEffect, useState } from 'react'
import TradingPostItem from './TradingPostItem'
import ForumPost from './ForumPost'
import { SearchBar }from '../statics/SearchBar'
export default function FeedPage({postList, filterTrading, filterForum, filterLostnFound}) {
  const [searchBarString,setSearchBarString] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("By Title");
  const [postListData, setPostListData] = useState([]);
  const handleCategoryChange = (category) =>{
    setSelectedCategory(category);
    console.log('Selected Category:', category);

  };
  console.log("PostLissssttttt::::")
  useEffect(()=>{
    console.log("entered postlist assignment")
    setPostListData(postList);
  },[postList])
  console.log(postList);

  return (
    <div className='mx-auto w-3/4 overflow-y-auto px-2 divide-y'>
        <SearchBar setResult={setSearchBarString} onCategoryChange={handleCategoryChange}/>
        {//postList.filter((post)=>{return post.userName.includes(searchBarString)})
        
        postListData.filter((post)=>{
          console.log('Filtering - Post:', post, 'Selected Category:', selectedCategory);

          const searchString = searchBarString;
          let categoryType;
          
          if(selectedCategory === "By Description"){
            categoryType = post.description;
          }
          else if(selectedCategory === "By Username"){
            categoryType = post.name;
          }
          else if(selectedCategory === "By Hashtag"){
            categoryType =  post.hashtagList.map((hashtag)=>{return hashtag}).join(" ");;
            console.log('Hashtag List:', categoryType);
          }
          else{
            categoryType = post.title;
            
          }
          
          return categoryType && categoryType.toLowerCase().includes(searchBarString);})
      
        
        
        .map((post,index)=>{
            console.log(post)
            // if(post.isTrading &&filterTrading){return <TradingPostItem key={index} post={p ost}/>}
            // else if(post.isLostnFound && filterForum){return <ForumPost key={index} post={post} isLostnFound={true}/>}
            // if(filterForum &&filterLostnFound){ return <ForumPost key={index} post={post} isLostnFound={false}/>}
            // <TradingPostItem vote={-1} title="Basys3 Sale" nameUser="Işıl Özgü" price={175}/>
            if(post.postType ===1 || post.postType === 2 || post.postType ===3 ||  post.postType === 5 || post.postType === 6) 
                    { return <TradingPostItem key={post.id} post={post}
            />}
    

            else if(post.postType === 0 || post.postType === 4 ) { return <ForumPost key={post.id} post={post}  />}
        })}
    </div>
  )
}
