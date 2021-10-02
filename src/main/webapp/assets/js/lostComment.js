commentList();

function commentList() {
	$.ajax({
		url: "lostCommentList.comment",
		type: 'GET',
		dataType: "json",
		data: {
			idx: $('#idx').val()
		},
		success: function (data) {

			$('.commentList').empty();
			if (data.length === 0) {
				$('.commentList').append('<tr align="left"><td>등록된 댓글이 없습니다</td></tr>');

			} else {
				$.each(data, function (index, obj) {
					if (obj.id === $('#writerId').val()) {
						$('.commentList').append('글쓴이');
					}
					$('.commentList').append('<div>['
						+ obj.id + '] : ' + obj.content + obj.writedate
						+ '<form method="POST" name="deleteComment">'
						+ '<input type="hidden" name="no" value="' + obj.no + '" class="comment_no">'
						+ '<input type="hidden" name="idx" value="' + $('#idx').val() + '" class="comment_idx">'
						+ '<input type="button" value="삭제" class="deleteBtn">'
						+ '</form></div>');
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

	// if (writerId.value === "" || content.value === "") {
	// 	alert("리플 내용, 작성자를 모두 입력해야합니다.");
	// 	return false;
	// }

	$.ajax({
		url: "lostCommentWrite.comment",
		type: 'POST',
		data: {
			"commentId": $('#commentId').val(),
			"content": $('#commentContent').val(),
			"idx": $('#idx').val()
		},
		success: function (data) {
			commentList();
			$('#commentId').val("");
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
		url: "lostCommentDelete.comment",
		type: 'POST',
		datatype: "text",
		data: {
			"no": frm.no.value,
			"idx_fk": frm.idx.value
		},
		success: function (data) {
			commentList();
		},
		error: function () {
			alert('댓글 삭제 실패');
		}
	});
}


