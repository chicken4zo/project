const file = document.querySelector("#file");
const fileName = document.querySelector(".product-photo").getAttribute("id");

$('#file').on("change", fileChange);

const url = window.location.href.split("/");
const fileNames = document.querySelectorAll('.product-photo');
$.each(fileNames, function (index, obj) {
    console.log(obj.id);
    console.log(fileNames);
    console.log(this);
    $(this).css({
        "background": "url('/" + url[3] + "/assets/upload/" + obj.id + "')",
        'background-repeat': 'no-repeat',
        'background-position': 'center center',
        'background-size': 'cover'
    });
});

function fileChange(e) {
    const files = e.target.files;
    const filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function (f) {
        const reader = new FileReader();
        reader.onload = function (e) {
            $('.product-photo').css({
                "background": "url(" + e.target.result + ")",
                'background-repeat': 'no-repeat',
                'background-position': 'center center',
                'background-size': 'cover'
            });
        }
        reader.readAsDataURL(f);
    })
}

$('.files').on("change", filesChange);

function filesChange(e) {
    const files = e.target.files;
    const filesArr = Array.prototype.slice.call(files);
    const productPhoto = $(this).parent('.product-photo');

    filesArr.forEach(function (f) {
        const reader = new FileReader();
        reader.onload = function (e) {
            productPhoto.css({
                "background": "url(" + e.target.result + ")",
                'background-repeat': 'no-repeat',
                'background-position': 'center center',
                'background-size': 'cover'
            });
        }
        reader.readAsDataURL(f);
    });
}

$('#delete').click(function (e) {
    e.preventDefault();
    $('.originalfile').val("");
    fileDelete(e);
})

if (fileName !== null) {
    $('.product-photo').css({
        "background": "url(${pageContext.request.contextPath}/assets/upload/" + fileName + ")",
        'background-repeat': 'no-repeat',
        'background-position': 'center center',
        'background-size': 'cover'
    })
} else {
    $('.product-photo').css({
        "background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",
        'background-repeat': 'no-repeat',
        'background-position': 'center center',
        'background-size': '20%'
    })
}

function fileDelete(e) {
    $(this).css({
        "background": "url(${pageContext.request.contextPath}/assets/images/upload.png)",
        'background-repeat': 'no-repeat',
        'background-position': 'center center',
        'background-size': '20%'
    });
}