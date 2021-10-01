//sub_nav hover

const navbar = document.querySelector(".navbar-nav");

const handleMouseOver = (e) => {
    // console.log(e.target);
    e.preventDefault();
    if (e.target.nodeName === "I") {
        let parent = e.target.parentNode;
        parent.childNodes[1].classList.add('sub_hover');
        parent.childNodes[3].classList.add('sub_hover');
    } else if (e.target.nodeName === "A") {
        let parent = e.target;
        parent.childNodes[1].classList.add('sub_hover');
        parent.childNodes[3].classList.add('sub_hover');
    }

}

const handleMouseLeave = (e) => {
    e.preventDefault();
    // console.log(e.target);
    if (e.target.nodeName === "I") {
        let parent = e.target.parentNode;
        parent.childNodes[1].classList.remove('sub_hover');
        parent.childNodes[3].classList.remove('sub_hover');
    } else if (e.target.nodeName === "A") {
        let parent = e.target;
        parent.childNodes[1].classList.remove('sub_hover');
        parent.childNodes[3].classList.remove('sub_hover');
    }
}

navbar.addEventListener('mouseover', handleMouseOver);
navbar.addEventListener('mouseout', handleMouseLeave);












