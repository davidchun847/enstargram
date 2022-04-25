
function updateUser(userId, event) {
	event.preventDefault(); // prevent form tag actions
	
	let data = $("#profileMod").serialize();
	
	console.log(userId)
	
	$.ajax({
		type:"put",
		url:`/api/user/${userId}`,
		data:data,
		contentType:"application/x-www-form-urlencoded; charset=utf-8",
		dataType:"json"
	}).done(res=>{ // http status 200
		console.log("profile modified", res);
		location.href=`/user/${userId}`;
	}).fail(error=>{
		if (error.data == null) {
			alert(error.responseJSON.message)
		} else {
			alert(JSON.stringify(error.responseJSON.data));
		}
	});
}