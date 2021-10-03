let boardName = $('#boardName').val();


commentList();


function commentList() {
	$.ajax({
		url: boardName + "CommentList.comment",
		type: 'GET',
		dataType: "json",
		data: {
			idx: $('#idx').val()
		},
		success: function (data) {

			$('.commentList').empty();


			const loginId = $('#loginId').val();
			const writerId = $('#writerId').val();
			const commentId = $('#commentId').val();
			console.log("loginId" + loginId);
			console.log("writerId" + writerId);
			console.log("commentId" + commentId);

			if (data.length === 0) {
				$('.commentList').append('<tr align="left"><td>등록된 댓글이 없습니다</td></tr>');
			} else {
				$.each(data, function (index, obj) {

					if (obj.id === writerId && loginId === obj.id) {
						$('.commentList').append('<div class="commentBox" id="me"><span>' + obj.id + '</span>' + '<span class="writer">작성자</span>' + '<p>' + obj.content + '</p><h4>' + obj.writedate + '</h4>'
							+ '<form method="POST" name="deleteComment">'
							+ '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
							+ '<input type="hidden" name="idx" value="' + $('#idx').val() + '" class="comment_idx">'
							+ '<input type="button" value="삭제" class="deleteBtn">'
							+ '</form></div>');
					} else if (obj.id === writerId) {
						$('.commentList').append('<div class="commentBox"><span>' + obj.id + '</span>' + '<span class="writer">작성자</span>' + '<p>' + obj.content + '</p><h4>' + obj.writedate + '</h4>'
							+ '<form method="POST" name="deleteComment">'
							+ '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
							+ '<input type="hidden" name="idx" value="' + $('#idx').val() + '" class="comment_idx">'
							+ '</form></div>');
					} else if (loginId === obj.id) {
						$('.commentList').append('<div class="commentBox" id="me"><span>' + obj.id + '</span><p>' + obj.content + '</p><h4>' + obj.writedate + '</h4>'
							+ '<form method="POST" name="deleteComment">'
							+ '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
							+ '<input type="hidden" name="idx" value="' + $('#idx').val() + '" class="comment_idx">'
							+ '<input type="button" value="삭제" class="deleteBtn">'
							+ '</form></div>');
					} else {
						$('.commentList').append('<div class="commentBox"><span>' + obj.id + '</span><p>' + obj.content + '</p><h4>' + obj.writedate + '</h4>'
							+ '<form method="POST" name="deleteComment">'
							+ '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
							+ '<input type="hidden" name="idx" value="' + $('#idx').val() + '" class="comment_idx">'
							+ '</form></div>');
					}


					$('.deleteBtn').click(function () {
						deleteComment(this.form);
					});
				});
			}
		},
		error: function () {
			alert('댓글 로드 실패');
		}
	});


}

$('#commentWriteBtn').click(function () {
	const idx = document.getElementById("idx");
	const writerId = document.getElementById("writerId");
	const content = document.getElementById("commentContent");

	console.log($('#commentId').val());

	if ($('#commentContent').val() === "" || $('#commentId').val() === "") {
		alert("내용을 입력해주세요");
		$('#commentContent').val("");
		return false;
	}

	// if (writerId.value === "" || content.value === "") {
	// 	alert("리플 내용, 작성자를 모두 입력해야합니다.");
	// 	return false;
	// }

	$.ajax({
		url: boardName + "CommentWrite.comment",
		type: 'POST',
		data: {
			"commentId": $('#commentId').val(),
			"content": $('#commentContent').val(),
			"idx": $('#idx').val()
		},
		success: function (data) {
			commentList();
			$('#commentContent').val("");
		},
		error: function () {
			alert('댓글 등록 실패');
		}
	})
});

function deleteComment(frm) {
	console.log("click!");
	$.ajax({
		url: boardName + "CommentDelete.comment",
		type: 'POST',
		datatype: "text",
		data: {
			"no": frm.no.value,
			"idx_fk": frm.idx.value
		},
		success: function (data) {
			commentList();
			console.log(data.id);
		},
		error: function () {
			alert('댓글 삭제 실패');
		}
	});
}




