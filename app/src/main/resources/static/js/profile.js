

function toggleSubscribe(userToId, obj) {
	console.log("userToId");
	console.log(userToId);
	if ($(obj).text() === "Unsubscribe") {
		$.ajax({
			type: "DELETE",
			url: `/api/subscribe/${userToId}`,
			dataType: "json"
		}).done(res => {
			$(obj).text("Subscribe");
			$(obj).toggleClass("blue");
		}).fail(error => {
			console.log("unsubscribe failed", error);
		});
	} else {
		$.ajax({
			type: "POST",
			url: `/api/subscribe/${userToId}`,
			dataType: "json"
		}).done(res => {
			$(obj).text("Unsubscribe");
			$(obj).toggleClass("blue");
		}).fail(error => {
			console.log("subscribe failed", error);
		});
	}
}

function openSubscribingModal(pageUserId) {
  $(".modal-subscribe").css("display", "flex");
  $.ajax({
    url: `/api/user/${pageUserId}/subscribe`,
    dataType: "json"
  }).done(res=>{
    console.log(res.data);
    res.data.forEach((user)=>{
      let item = getSubscribeModalItem(user);
      $("#subscribeModalList").append(item);
    });
  }).fail(error=>{
    console.log("subscribe load err", error)
  });
}

function getSubscribeModalItem(user) {
	let item = `<div class="subscribe__item" id="subscribeModalItem-${user.userId}">`;
	item += `<div class="subscribe__img">`;
	item += `<img src="/img/profile/${user.profileImgUrl}" alt=""  onerror="this.src='/images/person.jpeg'"/>`;
	item += `</div>`;
	item += `<div class="subscribe__text">`;
	item += `<h2>${user.username}</h2>`;
	item += `</div>`;
	item += `<div class="subscribe__btn">`;
	if (!user.userSame) {
		if (user.subscribeState) {
			item += `<button class="cta blue" onclick="toggleSubscribe(${user.userId}, this)">Unsubscribe</button>`;
		} else {
			item += `<button class="cta" onclick="toggleSubscribe(${user.userId}, this)">Subscribe</button>`;
		}
	}
	item += `</div>`;
	item += `</div>`;

	return item;
}


function profileImageUpload(pageUserId, principalId) {

  if (pageUserId != principalId) {
    alert("pageUserId != principalId")
    return;
    
  }

  $("#userProfileImageInput").click();

  $("#userProfileImageInput").on("change", (e) => {
    let f = e.target.files[0];

    if (!f.type.match("image.*")) {
      alert("img does not exist");
      return;
    }
    
    let profileImgForm = $("#userProfileImageForm")[0];
    console.log(profileImgForm)

    let formData = new FormData(profileImgForm)
    
    $.ajax({
      type: "put",
      url: `/api/user/${principalId}/profile-img-url`,
      data: formData,
      contentType: false, // img cannot be sent in x-www-form-urlencoded type 
      processData: false, // if contentType is false, QueryString is automatically set.
      enctype: "multipart/form-data",
      dataType: "json"
    }).done(res=>{
      let reader = new FileReader();
      reader.onload = (e) => {
        $("#userProfileImage").attr("src", e.target.result);
      };
      reader.readAsDataURL(f);
    }).fail(error=>{
      console.log("ajax send profile img error in profileImgForm", error)
    });
  });
}

function popup(obj) {
  $(obj).css("display", "flex");
}

function closePopup(obj) {
  $(obj).css("display", "none");
}

function modalInfo() {
  $(".modal-info").css("display", "none");
}

function modalImage() {
  $(".modal-image").css("display", "none");
}

function modalClose() {
  $(".modal-subscribe").css("display", "none");
  location.reload();
}
