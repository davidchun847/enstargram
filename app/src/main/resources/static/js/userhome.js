
let principalId = $("#principalId").val()


let page=0;

function getNews() {
  $.ajax({
    url: `/api/post?page=${page}`,
    dataType: "json",
  }).done(res=>{
    let posts = res.data.content;
    posts.forEach((post) => {
      let newsItem = getNewsItem(post);
      $("#storyList").append(newsItem);
    });
  }).fail(error=>{
    console.log("get news error", error)
  });
}

getNews();

function getNewsItem(post) {
  let item = `<div class="story-list__item">
	<div class="sl__item__header">
		<div>
			<img class="profile-image" src="/img/profile/${post.user.profileImgUrl}"
				onerror="this.src='/images/person.jpeg'" />
		</div>
		<div>${post.user.name}</div>
	</div>

	<div class="sl__item__img">
		<img src="/img/post/${post.imgUrl}" />
	</div>

	<div class="sl__item__contents">
		<div class="sl__item__contents__icon">`;
  if (post.likeState) {
    item += `<button onclick="toggleLike(${post.id}, this)">
							<i class="fas fa-heart active" id="storyLikeIcon-${post.id}"></i>
						</button>`;
  } else {
    item += `<button onclick="toggleLike(${post.id}, this)">
							<i class="far fa-heart" id="storyLikeIcon-${post.id}"></i>
						</button>`;
  }
  item +=
	`</div>
	<span class="like"><b id="storyLikeCount-${post.id}">${post.numLikes}</b>likes</span>
	<div class="sl__item__contents__content">
		<p>${post.text}</p>
	</div>
	<div id="storyCommentList-${post.id}">`;
	  post.comments.forEach((comment)=>{
	    item += `<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
	      <p>
		    <b>${comment.user.username} :</b> ${comment.content}
	      </p>`;
	    if (principalId == comment.user.id) {
	      item += `
	        <button onclick="deleteComment(${comment.id})">
		      <i class="fas fa-times"></i>
		    </button>`;
	    }
	  item += `
	    </div>`;
	  });
  item += `
	</div>
	  <div class="sl__item__input">
	    <input type="text" placeholder="aa" id="storyCommentInput-${post.id}" />
	    <button type="button" onClick="leaveComment(${post.id})">Submit</button>
	  </div>
	</div>
  </div>`;

  return item;  
}

$(window).scroll(() => {
  let checkNum = $(window).scrollTop() - ($(document).height() - $(window).height());
  if (checkNum < 1 && checkNum > -1) {
    page++;
    getNews();
  }
});

function toggleLike(postId) {
	let likeIcon = $(`#storyLikeIcon-${postId}`);
	if (likeIcon.hasClass("far")) { // toggle to like
		$.ajax({
			type: "POST",
			url: `/api/post/${postId}/likes`,
			dataType: "json"
		}).done(res => {
			let numLikesStr = $(`#storyLikeCount-${postId}`).text();
			let numLikes = Number(numLikesStr) + 1;
			$(`#storyLikeCount-${postId}`).text(numLikes);

			likeIcon.addClass("fas");
			likeIcon.addClass("active");
			likeIcon.removeClass("far");
		}).fail(error=>{
			console.log("error: toggle to like",error)
		});
	} else { // toggle to dislike
		$.ajax({
			type: "DELETE",
			url: `/api/post/${postId}/likes`,
			dataType: "json"
		}).done(res => {
			let numLikesStr = $(`#storyLikeCount-${postId}`).text();
			let numLikes = Number(numLikesStr) - 1;
			$(`#storyLikeCount-${postId}`).text(numLikes);

			likeIcon.removeClass("fas");
			likeIcon.removeClass("active");
			likeIcon.addClass("far");
		}).fail(error=>{
			console.log("error: toggle to dislike",error)
		});
	}
}

function leaveComment(postId) {

	let commentInput = $(`#storyCommentInput-${postId}`);
	let commentList = $(`#storyCommentList-${postId}`);

	let data = {
	  postId: postId,
	  content: commentInput.val()
	}

	if (data.content === "") {
		alert("Please fill out your comment!");
		return;
	}

	$.ajax({
	  type: "post",
	  url: "/api/comment",
	  data: JSON.stringify(data),
	  contentType: "application/json; charset=utf-8",
	  dataType: "json"
	}).done(res=>{
	  // console.log("leave comment success", res);
	  let comment=res.data;
	  let content = `
	   <div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}"> 
	     <p>
	       <b>${comment.user.username} :</b>
	       ${comment.content}
	     </p>
	     <button onclick="deleteComment(${comment.id})"><i class="fas fa-times"></i></button>
	   </div>
	  `;
	  commentList.prepend(content);
	}).fail(error=>{
	  console.log("leave comment error", error);
	});

	commentInput.val(""); // flush input field
}

function deleteComment(commentId) {
  $.ajax({
    type: "delete",
    url: `/api/comment/${commentId}`,
    dataType: "json"
  }).done(res=>{
    console.log("comment del success", res);
    $(`#storyCommentItem-${commentId}`).remove()
  }).fail(error=>{
    console.log("delete command error", error);
  });
}







