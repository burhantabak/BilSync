import React from 'react'
import TradingPostItem from './TradingPostItem'
import ForumPost from './ForumPost'
import SearchBar from '../statics/SearchBar'
export default function FeedPage({postList, filterTrading, filterForum, filterLostnFound}) {
  return (
    <div className='mx-auto w-3/4 overflow-y-auto px-2 divide-y'>
        <SearchBar/>
        <ForumPost vote={1} title="First Forum Post" nameUser="Tuna Saygın"/>
        {postList.map((post,index)=>{
            console.log(post)
            if(post.isTrading &&filterTrading){return <TradingPostItem key={index} post={post}/>}
            else if(post.isLostnFound && filterForum){return <ForumPost key={index} post={post} isLostnFound={true}/>}
            if(filterForum &&filterLostnFound){ return <ForumPost key={index} post={post} isLostnFound={false}/>}
            // <TradingPostItem vote={-1} title="Basys3 Sale" nameUser="Işıl Özgü" price={175}/>
        })}
    </div>
  )
}
