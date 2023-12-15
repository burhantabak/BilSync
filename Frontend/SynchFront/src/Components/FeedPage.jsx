import React, { useState } from 'react'
import TradingPostItem from './TradingPostItem'
import ForumPost from './ForumPost'
import { SearchBar }from '../statics/SearchBar'
export default function FeedPage({postList, filterTrading, filterForum, filterLostnFound}) {
  const [searchBarString,setSearchBarString] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("By Title");
  const handleCategoryChange = (category) =>{
    setSelectedCategory(category);
    console.log('Selected Category:', category);

  };
  console.log("PostLissssttttt::::" + postList)
  return (
    <div className='mx-auto w-3/4 overflow-y-auto px-2 divide-y'>
        <SearchBar setResult={setSearchBarString} onCategoryChange={handleCategoryChange}/>
        <ForumPost vote={1} title="First Forum Post" nameUser="Tuna Saygın"/>
        {//postList.filter((post)=>{return post.userName.includes(searchBarString)})
        
        postList.filter((post)=>{
          console.log('Filtering - Post:', post, 'Selected Category:', selectedCategory);

          const searchString = searchBarString;
          let categoryType;
          
          if(selectedCategory === "By Description"){
            categoryType = post.title;
          }
          else if(selectedCategory === "By Username"){
            categoryType = post.userName;
          }
          else if(selectedCategory === "By Label"){
            categoryType =  post.label;
          }
          else{
            categoryType = post.title;
            
          }
          
          return categoryType && categoryType.toLowerCase().includes(searchBarString);})
      
        
        
        .map((post,index)=>{
            console.log(post)
            // if(post.isTrading &&filterTrading){return <TradingPostItem key={index} post={post}/>}
            // else if(post.isLostnFound && filterForum){return <ForumPost key={index} post={post} isLostnFound={true}/>}
            // if(filterForum &&filterLostnFound){ return <ForumPost key={index} post={post} isLostnFound={false}/>}
            // <TradingPostItem vote={-1} title="Basys3 Sale" nameUser="Işıl Özgü" price={175}/>
            if(post.postType ===6 || post.postType ===5 ) { return <TradingPostItem key={post.id} post={post}/>}
            
        })}
    </div>
  )
}
